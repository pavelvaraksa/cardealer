package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.*;
import by.varaksa.cardealer.entity.Car;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.CarRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class CarRepositoryImpl implements CarRepository {
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String BRAND = "brand";
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
        car.setBrand(Brand.valueOf(resultSet.getString(BRAND)));
        car.setModel(Model.valueOf(resultSet.getString(MODEL)));
        car.setIssueCountry(Country.valueOf(resultSet.getString(ISSUE_COUNTRY)));
        car.setGuaranteePeriod(resultSet.getInt(GUARANTEE_PERIOD));
        car.setPrice(resultSet.getInt(PRICE));
        car.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        car.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        car.setUserOrderId(resultSet.getLong(USER_ORDER_ID));
        return car;
    }

    private static final String SAVE_CAR = "insert into cars (brand, model, issue_country, " +
            "guarantee_period, price, created, changed) " +
            "values (?,?,?,?,?,?,?)";
    private static final String FIND_ALL_CARS = "select * from cars";
    private static final String FIND_CAR_BY_ID = "select * from cars where id = ?";
    private static final String UPDATE_CAR_BY_ID = "update cars " +
            "set " +
            "brand = ?,  " +
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
        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(SAVE_CAR);

            statement.setString(1, String.valueOf(car.getBrand()));
            statement.setString(2, String.valueOf(car.getModel()));
            statement.setString(3, String.valueOf(car.getIssueCountry()));
            statement.setInt(4, car.getGuaranteePeriod());
            statement.setInt(5, car.getPrice());
            statement.setTimestamp(6, creationTimestamp);
            statement.setTimestamp(7, creationTimestamp);
            //statement.setLong(9, car.getUserOrderId());
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
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_CARS);

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
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(FIND_CAR_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

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
        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(UPDATE_CAR_BY_ID);

            statement.setString(1, String.valueOf(car.getBrand()));
            statement.setString(2, String.valueOf(car.getModel()));
            statement.setString(3, String.valueOf(car.getIssueCountry()));
            statement.setInt(4, car.getGuaranteePeriod());
            statement.setInt(5, car.getPrice());
            statement.setTimestamp(6, updateTimestamp);
            statement.setLong(7, car.getUserOrderId());
            statement.setLong(8, car.getId());
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
        Connection connection;
        PreparedStatement statement;
        Car car = new Car();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(DELETE_CAR_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();

            return car;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver was loaded from car repository class");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded from car repository class." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
