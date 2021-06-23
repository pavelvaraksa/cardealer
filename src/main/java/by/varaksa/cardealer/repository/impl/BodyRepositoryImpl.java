package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.entity.BodyType;
import by.varaksa.cardealer.entity.Color;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_DRIVER_NAME;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_URL;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class BodyRepositoryImpl implements BodyRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String COLOR = "color";
    private static final String BODY_TYPE = "body_type";
    private static final String VIN = "vin";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    private Body parseResultSet(ResultSet resultSet) throws SQLException {
        Body body = new Body();
        body.setId(resultSet.getLong(ID));
        body.setColor(Color.valueOf(resultSet.getString(COLOR)));
        body.setBodyType(BodyType.valueOf(resultSet.getString(BODY_TYPE)));
        body.setVin(resultSet.getString(VIN));
        body.setCreated(resultSet.getDate(CREATED));
        body.setChanged(resultSet.getDate(CHANGED));
        body.setCarId(resultSet.getLong(CAR_ID));
        return body;
    }

    @Override
    public List<Body> findAll() {
        final String findAllBodies = "select * from bodies";

        List<Body> result = new ArrayList<>();
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
            resultSet = statement.executeQuery(findAllBodies);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All bodies: " + findAllBodies);
            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Body find(Long id) throws RepositoryException {
        final String findBodyById = "select * from bodies where id = ?";

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
            statement = connection.prepareStatement(findBodyById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("Body with id " + id + " is found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Body with id " + id + " is not found");
            }

        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Body save(Body body) throws RepositoryException {
        final String saveBody = "insert into bodies (color, body_type, vin, created, changed, car_id) " +
                "values (?,?,?,?,?,?)";

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
            statement = connection.prepareStatement(saveBody);

            statement.setString(1, String.valueOf(body.getColor()));
            statement.setString(2, String.valueOf(body.getBodyType()));
            statement.setString(3, body.getVin());
            statement.setDate(4, (Date) body.getCreated());
            statement.setDate(5, (Date) body.getChanged());
            statement.setLong(6, body.getCarId());
            statement.executeUpdate();

            logger.info("Body " + body + " is saved");
            return body;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Body update(Long id) {
        final String updateBodyById = "update bodies " +
                "set " +
                "color = ?,  " +
                "body_type = ?,  " +
                "vin = ?,  " +
                "created = ?,  " +
                "changed = ?,  " +
                "car_id = ?  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;
        Body body = new Body();

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
            statement = connection.prepareStatement(updateBodyById);

            statement.setString(1, String.valueOf(body.getColor()));
            statement.setString(2, String.valueOf(body.getBodyType()));
            statement.setString(3, body.getVin());
            statement.setDate(4, (Date) body.getCreated());
            statement.setDate(5, (Date) body.getChanged());
            statement.setLong(6, body.getCarId());
            statement.executeUpdate();

            logger.info("Body with id " + id + " is updated");
            return body;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Body delete(Long id) {
        final String deleteBodyById = "delete from bodies where id = ?";

        Connection connection;
        PreparedStatement statement;
        Body body = new Body();

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
            statement = connection.prepareStatement(deleteBodyById);
            statement.executeUpdate();

            logger.info("Body with id " + id + " is deleted");
            return body;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
