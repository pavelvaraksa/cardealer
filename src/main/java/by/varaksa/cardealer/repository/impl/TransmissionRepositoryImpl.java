package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Transmission;
import by.varaksa.cardealer.entity.TransmissionType;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.TransmissionRepository;
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

public class TransmissionRepositoryImpl implements TransmissionRepository {
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String TRANSMISSION_TYPE = "transmission_type";
    private static final String GEARS_COUNT = "gears_count";
    private static final String WEIGHT = "volume";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    private Transmission parseResultSet(ResultSet resultSet) throws SQLException {
        Transmission transmission = new Transmission();
        transmission.setId(resultSet.getLong(ID));
        transmission.setTransmissionType(TransmissionType.valueOf(resultSet.getString(TRANSMISSION_TYPE)));
        transmission.setGearsCount(resultSet.getInt(GEARS_COUNT));
        transmission.setWeight(resultSet.getDouble(WEIGHT));
        transmission.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        transmission.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        transmission.setCarId(resultSet.getLong(CAR_ID));
        return transmission;
    }

    private static final String SAVE_TRANSMISSION = "insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id) " +
            "values (?,?,?,?,?,?)";
    private static final String FIND_ALL_TRANSMISSIONS = "select * from transmissions";
    private static final String FIND_TRANSMISSION_BY_ID = "select * from transmissions where id = ?";
    private static final String UPDATE_TRANSMISSION_BY_ID = "update transmissions " +
            "set " +
            "transmission_type = ?,  " +
            "gears_count = ?,  " +
            "weight = ?,  " +
            "changed = ?,  " +
            "car_id = ?  " +
            "where id = ?";
    private static final String DELETE_TRANSMISSION_BY_ID = "delete from transmissions where id = ?";

    @Override
    public Transmission save(Transmission transmission) throws RepositoryException {
        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(SAVE_TRANSMISSION);

            statement.setString(1, String.valueOf(transmission.getTransmissionType()));
            statement.setInt(2, transmission.getGearsCount());
            statement.setDouble(3, transmission.getWeight());
            statement.setTimestamp(4, creationTimestamp);
            statement.setTimestamp(5, creationTimestamp);
            statement.setLong(6, transmission.getCarId());
            statement.executeUpdate();

            return transmission;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Transmission> findAll() {
        List<Transmission> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_TRANSMISSIONS);

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
    public Transmission find(Long id) throws RepositoryException {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(FIND_TRANSMISSION_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Transmission with id " + id + " wasn't found");
            }

        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Transmission update(Transmission transmission) {
        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(UPDATE_TRANSMISSION_BY_ID);

            statement.setString(1, String.valueOf(transmission.getTransmissionType()));
            statement.setInt(2, transmission.getGearsCount());
            statement.setDouble(3, transmission.getWeight());
            statement.setTimestamp(4, updateTimestamp);
            statement.setLong(5, transmission.getCarId());
            statement.setLong(6, transmission.getId());
            statement.executeUpdate();

            return transmission;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Transmission delete(Long id) {
        Connection connection;
        PreparedStatement statement;
        Transmission transmission = new Transmission();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(DELETE_TRANSMISSION_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();

            return transmission;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver was loaded from transmission repository class");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded from transmission repository class." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
