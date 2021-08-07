package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.model.connection.PoolConnection;
import by.varaksa.cardealer.model.entity.Car;
import by.varaksa.cardealer.model.entity.Country;
import by.varaksa.cardealer.model.entity.Model;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.repository.CarRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    private static final Logger logger = LogManager.getLogger();

    private static final String ID = "id";
    private static final String MODEL = "model";
    private static final String ISSUE_COUNTRY = "issue_country";
    private static final String GUARANTEE_PERIOD = "guarantee_period";
    private static final String PRICE = "price";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String USER_ORDER_ID = "user_order_id";

    private Car parseResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(ID));
        car.setModel(Model.valueOf(resultSet.getString(MODEL)));
        car.setIssueCountry(Country.valueOf(resultSet.getString(ISSUE_COUNTRY)));
        car.setGuaranteePeriod(resultSet.getInt(GUARANTEE_PERIOD));
        car.setPrice(resultSet.getInt(PRICE));
        car.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        car.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        car.setUserOrderId(resultSet.getLong(USER_ORDER_ID));
        return car;
    }

    private static final String SAVE_CAR = "insert into cars (model, issue_country, " +
            "guarantee_period, price, created, changed) " +
            "values (?,?,?,?,?,?)";
    private static final String FIND_ALL_CARS = "select * from cars";
    private static final String FIND_CAR_BY_ID = "select * from cars where id = ?";
    private static final String UPDATE_CAR_BY_ID = "update cars " +
            "set " +
            "model = ?,  " +
            "issue_country = ?,  " +
            "guarantee_period = ?,  " +
            "price = ?,  " +
            "changed = ?,  " +
            "user_order_id = ?  " +
            "where id = ?";
    private static final String DELETE_CAR_BY_ID = "delete from cars where id = ?";

    @Override
    public Car save(Car car) {
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_CAR)) {

            statement.setString(1, String.valueOf(car.getModel()));
            statement.setString(2, String.valueOf(car.getIssueCountry()));
            statement.setInt(3, car.getGuaranteePeriod());
            statement.setInt(4, car.getPrice());
            statement.setTimestamp(5, creationTimestamp);
            statement.setTimestamp(6, creationTimestamp);
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
        List<Car> result = new ArrayList<>();

        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CARS);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            return result;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Car find(Long id) {
        try (Connection connection = PoolConnection.getInstance().getConnection();
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

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_BY_ID)) {

            statement.setString(1, String.valueOf(car.getModel()));
            statement.setString(2, String.valueOf(car.getIssueCountry()));
            statement.setInt(3, car.getGuaranteePeriod());
            statement.setInt(4, car.getPrice());
            statement.setTimestamp(5, updateTimestamp);
            statement.setLong(6, car.getUserOrderId());
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

        try (Connection connection = PoolConnection.getInstance().getConnection();
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
