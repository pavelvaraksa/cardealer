package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Engine;
import by.varaksa.cardealer.entity.EngineType;
import by.varaksa.cardealer.entity.FuelType;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.EngineRepository;
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

public class EngineRepositoryImpl implements EngineRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String ENGINE_TYPE = "engine_type";
    private static final String FUEL_TYPE = "fuel_type";
    private static final String VOLUME = "volume";
    private static final String CYLINDERS_COUNT = "cylinders_count";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    private Engine parseResultSet(ResultSet resultSet) throws SQLException {
        Engine engine = new Engine();
        engine.setId(resultSet.getLong(ID));
        engine.setEngineType(EngineType.valueOf(resultSet.getString(ENGINE_TYPE)));
        engine.setFuelType(FuelType.valueOf(resultSet.getString(FUEL_TYPE)));
        engine.setVolume(resultSet.getDouble(VOLUME));
        engine.setCylindersCount(resultSet.getInt(CYLINDERS_COUNT));
        engine.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        engine.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        engine.setCarId(resultSet.getLong(CAR_ID));
        return engine;
    }

    @Override
    public Engine save(Engine engine) throws RepositoryException {
        final String saveEngine = "insert into engines (engine_type, fuel_type, volume, cylinders_count, created, changed, car_id) " +
                "values (?,?,?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(saveEngine);

            statement.setString(1, String.valueOf(engine.getEngineType()));
            statement.setString(2, String.valueOf(engine.getFuelType()));
            statement.setDouble(3, engine.getVolume());
            statement.setInt(4, engine.getCylindersCount());
            statement.setTimestamp(5, creationTimestamp);
            statement.setTimestamp(6, creationTimestamp);
            statement.setLong(7, engine.getCarId());
            statement.executeUpdate();

            logger.info("Engine with id " + engine.getId() + " was saved");
            return engine;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Engine> findAll() {
        final String findAllEngines = "select * from engines";

        List<Engine> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllEngines);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All engines: " + result);
            return result;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Engine find(Long id) throws RepositoryException {
        final String findEngineById = "select * from engines where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findEngineById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("Engine with id " + id + " was found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Engine with id " + id + " wasn't found");
            }

        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Engine update(Engine engine) {
        final String updateEngineById = "update engines " +
                "set " +
                "engine_type = ?,  " +
                "fuel_type = ?,  " +
                "volume = ?,  " +
                "cylinders_count = ?,  " +
                "changed = ?,  " +
                "car_id = ?  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(updateEngineById);

            statement.setString(1, String.valueOf(engine.getEngineType()));
            statement.setString(2, String.valueOf(engine.getFuelType()));
            statement.setDouble(3, engine.getVolume());
            statement.setInt(4, engine.getCylindersCount());
            statement.setTimestamp(5, updateTimestamp);
            statement.setLong(6, engine.getCarId());
            statement.setLong(7, engine.getId());
            statement.executeUpdate();

            logger.info("Engine with id " + engine.getId() + " was updated");
            return engine;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Engine delete(Long id) {
        final String deleteEngineById = "delete from engines where id = ?";

        Connection connection;
        PreparedStatement statement;
        Engine engine = new Engine();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(deleteEngineById);
            statement.setLong(1, id);
            statement.executeUpdate();

            return engine;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver be loaded");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver can't be loaded." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
