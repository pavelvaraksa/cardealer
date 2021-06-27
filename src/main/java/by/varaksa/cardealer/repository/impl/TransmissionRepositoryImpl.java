package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Transmission;
import by.varaksa.cardealer.entity.TransmissionType;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.TransmissionRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class TransmissionRepositoryImpl implements TransmissionRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

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
        transmission.setCreated(resultSet.getTimestamp(CREATED));
        transmission.setChanged(resultSet.getTimestamp(CHANGED));
        transmission.setCarId(resultSet.getLong(CAR_ID));
        return transmission;
    }

    @Override
    public Transmission save(Transmission transmission) throws RepositoryException {
        final String saveTransmission = "insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id) " +
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
            statement = connection.prepareStatement(saveTransmission);

            statement.setString(1, String.valueOf(transmission.getTransmissionType()));
            statement.setInt(2, transmission.getGearsCount());
            statement.setDouble(3, transmission.getWeight());
            statement.setTimestamp(4, transmission.getCreated());
            statement.setTimestamp(5, transmission.getChanged());
            statement.setLong(6, transmission.getCarId());
            statement.executeUpdate();

            logger.info("Transmission with id " + transmission.getId() + " was saved");
            return transmission;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Transmission> findAll() {
        final String findAllTransmissions = "select * from transmissions";

        List<Transmission> result = new ArrayList<>();
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
            resultSet = statement.executeQuery(findAllTransmissions);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All transmissions: " + result);
            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Transmission find(Long id) throws RepositoryException {
        final String findTransmissionById = "select * from transmissions where id = ?";

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
            statement = connection.prepareStatement(findTransmissionById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("Transmission with id " + id + " was found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Transmission with id " + id + " wasn't found");
            }

        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Transmission update(Transmission transmission) {
        final String updateTransmissionById = "update transmissions " +
                "set " +
                "transmission_type = ?,  " +
                "gears_count = ?,  " +
                "weight = ?,  " +
                "created = ?,  " +
                "changed = ?,  " +
                "car_id = ?  " +
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
            statement = connection.prepareStatement(updateTransmissionById);

            statement.setString(1, String.valueOf(transmission.getTransmissionType()));
            statement.setInt(2, transmission.getGearsCount());
            statement.setDouble(3, transmission.getWeight());
            statement.setTimestamp(4, transmission.getCreated());
            statement.setTimestamp(5, transmission.getChanged());
            statement.setLong(6, transmission.getCarId());
            statement.setLong(7, transmission.getId());
            statement.executeUpdate();

            logger.info("Transmission with id " + transmission.getId() + " was updated");
            return transmission;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Long delete(Transmission transmission) {
        final String deleteTransmissionById = "delete from transmissions where id = ?";

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
            statement = connection.prepareStatement(deleteTransmissionById);
            statement.setLong(1, transmission.getId());

            int deletedRows = statement.executeUpdate();
            logger.info("Transmission with id " + transmission.getId() + " was deleted");
            return (long) deletedRows;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
