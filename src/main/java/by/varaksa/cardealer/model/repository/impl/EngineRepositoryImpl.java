package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.model.connection.PoolConnection;
import by.varaksa.cardealer.model.entity.Engine;
import by.varaksa.cardealer.model.entity.FuelType;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.repository.EngineRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EngineRepositoryImpl implements EngineRepository {
    private static final Logger logger = LogManager.getLogger();

    private static final String ID = "id";
    private static final String FUEL_TYPE = "fuel_type";
    private static final String VOLUME = "volume";
    private static final String CYLINDERS_COUNT = "cylinders_count";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    private Engine parseResultSet(ResultSet resultSet) throws SQLException {
        Engine engine = new Engine();
        engine.setId(resultSet.getLong(ID));
        engine.setFuelType(FuelType.valueOf(resultSet.getString(FUEL_TYPE)));
        engine.setVolume(resultSet.getDouble(VOLUME));
        engine.setCylindersCount(resultSet.getInt(CYLINDERS_COUNT));
        engine.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        engine.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        engine.setCarId(resultSet.getLong(CAR_ID));
        return engine;
    }

    private static final String SAVE_ENGINE = "insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id) " +
            "values (?,?,?,?,?,?)";
    private static final String FIND_ALL_ENGINES = "select * from engines";
    private static final String FIND_ENGINE_BY_ID = "select * from engines where id = ?";
    private static final String UPDATE_ENGINE_BY_ID = "update engines " +
            "set " +
            "fuel_type = ?,  " +
            "volume = ?,  " +
            "cylinders_count = ?,  " +
            "changed = ?,  " +
            "car_id = ?  " +
            "where id = ?";
    private static final String DELETE_ENGINE_BY_ID = "delete from engines where id = ?";

    @Override
    public Engine save(Engine engine) throws RepositoryException {
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_ENGINE)) {

            statement.setString(1, String.valueOf(engine.getFuelType()));
            statement.setDouble(2, engine.getVolume());
            statement.setInt(3, engine.getCylindersCount());
            statement.setTimestamp(4, creationTimestamp);
            statement.setTimestamp(5, creationTimestamp);
            statement.setLong(6, engine.getCarId());
            statement.executeUpdate();

            return engine;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Engine> findAll() {
        List<Engine> result = new ArrayList<>();

        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ENGINES);

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
    public Engine find(Long id) throws RepositoryException {
        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ENGINE_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
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
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ENGINE_BY_ID)) {

            statement.setString(1, String.valueOf(engine.getFuelType()));
            statement.setDouble(2, engine.getVolume());
            statement.setInt(3, engine.getCylindersCount());
            statement.setTimestamp(4, updateTimestamp);
            statement.setLong(5, engine.getCarId());
            statement.setLong(6, engine.getId());
            statement.executeUpdate();

            return engine;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Engine delete(Long id) {
        Engine engine = new Engine();

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ENGINE_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            return engine;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
