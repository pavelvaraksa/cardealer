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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_DRIVER_NAME;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_URL;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class BodyRepositoryImpl implements BodyRepository {
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String COLOR = "color";
    private static final String BODY_TYPE = "body_type";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    private static final String SAVE_BODY = "insert into bodies (color, body_type, created, changed, car_id) " +
            "values (?,?,?,?,?)";
    private static final String FIND_ALL_BODIES = "select * from bodies";
    private static final String FIND_BODY_BY_ID = "select * from bodies where id = ?";
    private static final String UPDATE_BODY_BY_ID = "update bodies " +
            "set " +
            "color = ?,  " +
            "body_type = ?,  " +
            "changed = ?,  " +
            "car_id = ?  " +
            "where id = ?";
    private static final String DELETE_BODY_BY_ID = "delete from bodies where id = ?";

    private Body parseResultSet(ResultSet resultSet) throws SQLException {
        Body body = new Body();
        body.setId(resultSet.getLong(ID));
        body.setColor(Color.valueOf(resultSet.getString(COLOR)));
        body.setBodyType(BodyType.valueOf(resultSet.getString(BODY_TYPE)));
        body.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        body.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        body.setCarId(resultSet.getLong(CAR_ID));
        return body;
    }

    @Override
    public Body save(Body body) {
        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(SAVE_BODY);

            statement.setString(1, String.valueOf(body.getColor()));
            statement.setString(2, String.valueOf(body.getBodyType()));
            statement.setTimestamp(3, creationTimestamp);
            statement.setTimestamp(4, creationTimestamp);
            statement.setLong(5, body.getCarId());
            statement.executeUpdate();

            return body;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Body> findAll() {
        List<Body> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_BODIES);

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
    public Body find(Long id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(FIND_BODY_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Body with id " + id + " wasn't found");
            }

        } catch (SQLException | RepositoryException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Body update(Body body) {
        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(UPDATE_BODY_BY_ID);

            statement.setString(1, String.valueOf(body.getColor()));
            statement.setString(2, String.valueOf(body.getBodyType()));
            statement.setTimestamp(3, updateTimestamp);
            statement.setLong(4, body.getCarId());
            statement.setLong(5, body.getId());
            statement.executeUpdate();

            return find(body.getId());
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Body delete(Long id) {
        Connection connection;
        PreparedStatement statement;
        Body body = new Body();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(DELETE_BODY_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();

            return body;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver was loaded from body repository class");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded from body repository class." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
