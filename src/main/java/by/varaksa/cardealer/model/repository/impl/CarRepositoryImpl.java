package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.connection.ConnectionPool;
import by.varaksa.cardealer.model.entity.*;
import by.varaksa.cardealer.model.repository.CarRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code CarRepositoryImpl} designed for communication between repository
 * and database for actions related to car
 *
 * @author Pavel Varaksa
 */
public class CarRepositoryImpl implements CarRepository {
    private static final Logger logger = LogManager.getLogger();

    private static final String ID = "id";
    private static final String MODEL = "model";
    private static final String ISSUE_COUNTRY = "issue_country";
    private static final String GUARANTEE_PERIOD = "guarantee_period";
    private static final String PRICE = "price";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String DEALER_ID = "dealer_id";
    private static final String NAME = "name";
    private static final String FUEL_TYPE = "fuel_type";
    private static final String VOLUME = "volume";
    private static final String TRANSMISSION_TYPE = "transmission_type";
    private static final String GEARS_COUNT = "gears_count";
    private static final String COLOR = "color";
    private static final String BODY_TYPE = "body_type";

    private Car parseResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(ID));
        car.setModel(Model.valueOf(resultSet.getString(MODEL)));
        car.setIssueCountry(Country.valueOf(resultSet.getString(ISSUE_COUNTRY)));
        car.setGuaranteePeriod(resultSet.getInt(GUARANTEE_PERIOD));
        car.setPrice(resultSet.getInt(PRICE));
        car.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        car.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        car.setDealerId(resultSet.getLong(DEALER_ID));
        return car;
    }

    private Car parseResultSetForOrder(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(ID));
        car.setModel(Model.valueOf(resultSet.getString(MODEL)));
        car.setIssueCountry(Country.valueOf(resultSet.getString(ISSUE_COUNTRY)));
        car.setPrice(resultSet.getInt(PRICE));
        car.setName(resultSet.getString(NAME));
        car.setFuelType(FuelType.valueOf(resultSet.getString(FUEL_TYPE)));
        car.setVolume(resultSet.getDouble(VOLUME));
        car.setTransmissionType(TransmissionType.valueOf(resultSet.getString(TRANSMISSION_TYPE)));
        car.setGearsCount(resultSet.getInt(GEARS_COUNT));
        car.setColor(Color.valueOf(resultSet.getString(COLOR)));
        car.setBodyType(BodyType.valueOf(resultSet.getString(BODY_TYPE)));
        return car;
    }

    private static final String SAVE_CAR = "insert into cars (model, issue_country, " +
            "guarantee_period, price, created, changed, dealer_id) " +
            "values (?,?,?,?,?,?,?)";
    private static final String FIND_ALL_CARS = "select * from cars";
    private static final String FIND_CAR_BY_ID = "select * from cars where id = ?";
    private static final String UPDATE_CAR_BY_ID = "update cars " +
            "set " +
            "model = ?,  " +
            "issue_country = ?,  " +
            "guarantee_period = ?,  " +
            "price = ?,  " +
            "changed = ?,  " +
            "dealer_id = ?  " +
            "where id = ?";
    private static final String DELETE_CAR_BY_ID = "delete from cars where id = ?";
    private static final String FIND_ALL_CARS_FOR_ORDER = "select cars.id, model, issue_country, price, name, fuel_type, volume, " +
            "transmission_type, gears_count, color, body_type from cars " +
            "join dealers on cars.dealer_id = dealers.id " +
            "join engines on engines.car_id = cars.id " +
            "join transmissions on transmissions.car_id = cars.id " +
            "join bodies on bodies.car_id = cars.id";

    @Override
    public Car save(Car car) {
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_CAR)) {

            statement.setString(1, String.valueOf(car.getModel()));
            statement.setString(2, String.valueOf(car.getIssueCountry()));
            statement.setInt(3, car.getGuaranteePeriod());
            statement.setInt(4, car.getPrice());
            statement.setTimestamp(5, creationTimestamp);
            statement.setTimestamp(6, creationTimestamp);
            statement.setLong(7, car.getDealerId());
            statement.executeUpdate();

            return car;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CARS);

            while (resultSet.next()) {
                list.add(parseResultSet(resultSet));
            }

            return list;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Car> findAllForOrder() {
        List<Car> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CARS_FOR_ORDER);

            while (resultSet.next()) {
                list.add(parseResultSetForOrder(resultSet));
            }

            return list;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Car find(Long id) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CAR_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Car with id " + id + " wasn't found");
            }

        } catch (SQLException | RepositoryException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Car update(Car car) {
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_BY_ID)) {

            statement.setString(1, String.valueOf(car.getModel()));
            statement.setString(2, String.valueOf(car.getIssueCountry()));
            statement.setInt(3, car.getGuaranteePeriod());
            statement.setInt(4, car.getPrice());
            statement.setTimestamp(5, updateTimestamp);
            statement.setLong(6, car.getDealerId());
            statement.setLong(7, car.getId());
            statement.executeUpdate();

            return find(car.getId());
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Car delete(Long id) {
        Car car = new Car();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CAR_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            return car;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
