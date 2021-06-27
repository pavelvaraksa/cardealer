package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.*;
import by.varaksa.cardealer.entity.Car;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.CarRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class CarRepositoryImpl implements CarRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String ISSUE_COUNTRY = "issue_country";
    private static final String GUARANTEE_PERIOD = "guarantee_period";
    private static final String PRICE = "price";
    private static final String IMAGE = "image";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String USER_ORDER_ID = "user_order_id";

    private Car parseResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(ID));
        car.setBrand(Brand.valueOf(resultSet.getString(BRAND)));
        car.setModel(resultSet.getString(MODEL));
        car.setIssueCountry(Country.valueOf(resultSet.getString(ISSUE_COUNTRY)));
        car.setGuaranteePeriod(resultSet.getInt(GUARANTEE_PERIOD));
        car.setPrice(resultSet.getDouble(PRICE));
        car.setImage(resultSet.getBlob(IMAGE));
        car.setCreated(resultSet.getTimestamp(CREATED));
        car.setChanged(resultSet.getTimestamp(CHANGED));
        car.setUserOrderId(resultSet.getLong(USER_ORDER_ID));
        return car;
    }

    @Override
    public Car save(Car car) throws RepositoryException {
        final String saveCar = "insert into cars (brand, model, issue_country, guarantee_period, price, image, created, changed, user_order_id) " +
                "values (?,?,?,?,?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(saveCar);

            statement.setString(1, String.valueOf(car.getBrand()));
            statement.setString(2, car.getModel());
            statement.setString(3, String.valueOf(car.getIssueCountry()));
            statement.setInt(4, car.getGuaranteePeriod());
            statement.setDouble(5, car.getPrice());
            statement.setBlob(6, car.getImage());
            statement.setTimestamp(7, car.getCreated());
            statement.setTimestamp(8, car.getChanged());
            statement.setLong(9, car.getUserOrderId());
            statement.executeUpdate();

            logger.info("Car with id " + car.getId() + " was saved");
            return car;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Car> findAll() {
        final String findAllCars = "select * from cars";

        List<Car> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllCars);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All cars: " + result);
            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Car find(Long id) throws RepositoryException {
        final String findCarById = "select * from cars where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findCarById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("Car with id " + id + " was found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Car with id " + id + " wasn't found");
            }

        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Car update(Car car) {
        final String updateCarById = "update cars " +
                "set " +
                "brand = ?,  " +
                "model = ?,  " +
                "issue_country = ?,  " +
                "guarantee_period = ?,  " +
                "price = ?,  " +
                "image = ?,  " +
                "created = ?,  " +
                "changed = ?,  " +
                "user_order_id = ?  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(updateCarById);

            statement.setString(1, String.valueOf(car.getBrand()));
            statement.setString(2, car.getModel());
            statement.setString(3, String.valueOf(car.getIssueCountry()));
            statement.setInt(4, car.getGuaranteePeriod());
            statement.setDouble(5, car.getPrice());
            statement.setBlob(6, car.getImage());
            statement.setTimestamp(7, car.getCreated());
            statement.setTimestamp(8, car.getChanged());
            statement.setLong(9, car.getUserOrderId());
            statement.setLong(10, car.getId());
            statement.executeUpdate();

            logger.info("Car with id " + car.getId() + " was updated");
            return car;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Long delete(Car car) {
        final String deleteCarById = "delete from cars where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(deleteCarById);
            statement.setLong(1, car.getId());

            int deletedRows = statement.executeUpdate();
            logger.info("Car with id " + car.getId() + " was deleted");
            return (long) deletedRows;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
