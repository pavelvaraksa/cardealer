package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.model.entity.Body;
import by.varaksa.cardealer.model.entity.BodyType;
import by.varaksa.cardealer.model.entity.Color;
import by.varaksa.cardealer.model.connection.PoolConnection;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.repository.BodyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BodyRepositoryImpl implements BodyRepository {
    private static final Logger logger = LogManager.getLogger();

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
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_BODY)) {

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

        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BODIES);

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
        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BODY_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

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
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BODY_BY_ID)) {

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
        Body body = new Body();

        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BODY_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            return body;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
