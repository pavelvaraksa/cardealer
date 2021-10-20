package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.connection.ConnectionPool;
import by.varaksa.cardealer.model.entity.*;
import by.varaksa.cardealer.model.repository.UserOrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code UserOrderRepositoryImpl} designed for communication between repository
 * and database for actions related to user order
 *
 * @author Pavel Varaksa
 */
public class UserOrderRepositoryImpl implements UserOrderRepository {
    private static final Logger logger = LogManager.getLogger();

    private static final String ID = "id";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String USER_ID = "user_id";
    private static final String CAR_ID = "car_id";
    private static final String MODEL = "model";
    private static final String PRICE = "price";
    private static final String NAME = "name";
    private static final String FUEL_TYPE = "fuel_type";

    private UserOrder parseResultSet(ResultSet resultSet) throws SQLException {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(resultSet.getLong(ID));
        userOrder.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        userOrder.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        userOrder.setUserId(resultSet.getLong(USER_ID));
        userOrder.setCarId(resultSet.getLong(CAR_ID));

        return userOrder;
    }

    private UserOrder parseResultSetForOrder(ResultSet resultSet) throws SQLException {
        UserOrder userOrder = new UserOrder();
        userOrder.setModel(Model.valueOf(resultSet.getString(MODEL)));
        userOrder.setPrice(Integer.valueOf(resultSet.getString(PRICE)));
        userOrder.setName(resultSet.getString(NAME));
        userOrder.setFuelType(FuelType.valueOf(resultSet.getString(FUEL_TYPE)));
        userOrder.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        return userOrder;
    }

    private static final String SAVE_USER_ORDER = "insert into user_orders (created, changed, user_id, car_id) " +
            "values (?,?,?,?)";
    private static final String FIND_ALL_USER_ORDERS = "select * from user_orders";
    private static final String FIND_USER_ORDER_BY_LOGIN = "select model, price, name, fuel_type, user_orders.created from user_orders " +
            "join cars on user_orders.car_id = cars.id " +
            "join dealers on cars.dealer_id = dealers.id " +
            "join engines on engines.car_id = cars.id " +
            "join users on user_orders.user_id = users.id " +
            "where login = ?";
    private static final String FIND_USER_ORDER_BY_ID = "select * from user_orders where id = ?";
    private static final String UPDATE_USER_ORDER_BY_ID = "update user_orders " +
            "set " +
            "changed = ?,  " +
            "user_id = ?,  " +
            "car_id = ?  " +
            "where id = ?";
    private static final String DELETE_USER_ORDER_BY_ID = "delete from user_orders where id = ?";

    @Override
    public UserOrder save(UserOrder userOrder) throws RepositoryException {
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_USER_ORDER)) {

            statement.setTimestamp(1, creationTimestamp);
            statement.setTimestamp(2, creationTimestamp);
            statement.setLong(3, userOrder.getUserId());
            statement.setLong(4, userOrder.getCarId());
            statement.executeUpdate();

            return userOrder;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<UserOrder> findAll() {
        List<UserOrder> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USER_ORDERS);

            while (resultSet.next()) {
                list.add(parseResultSet(resultSet));
            }

            return list;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<UserOrder> findAllForUser(String login) {
        List<UserOrder> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_ORDER_BY_LOGIN)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(parseResultSetForOrder(resultSet));
            }

            return list;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserOrder find(Long id) throws RepositoryException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_ORDER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("User order with id " + id + " wasn't found");
            }

        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserOrder update(UserOrder userOrder) {
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ORDER_BY_ID)) {

            statement.setTimestamp(1, updateTimestamp);
            statement.setLong(2, userOrder.getUserId());
            statement.setLong(3, userOrder.getCarId());
            statement.setLong(4, userOrder.getId());
            statement.executeUpdate();

            return userOrder;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserOrder delete(Long id) {
        UserOrder userOrder = new UserOrder();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_ORDER_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            return userOrder;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
