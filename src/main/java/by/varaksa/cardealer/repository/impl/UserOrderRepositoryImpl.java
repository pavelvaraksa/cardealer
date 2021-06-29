package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.UserOrder;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.UserOrderRepository;
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

public class UserOrderRepositoryImpl implements UserOrderRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String COUNT = "count";
    private static final String ORDER_NAME = "order_name";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String USER_ID = "user_id";

    private UserOrder parseResultSet(ResultSet resultSet) throws SQLException {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(resultSet.getLong(ID));
        userOrder.setOrderName(resultSet.getString(ORDER_NAME));
        userOrder.setCount((resultSet.getInt(COUNT)));
        userOrder.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        userOrder.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        userOrder.setUserId(resultSet.getLong(USER_ID));
        return userOrder;
    }

    @Override
    public UserOrder save(UserOrder userOrder) throws RepositoryException {
        final String saveUserOrder = "insert into user_orders (order_name, count, created, changed, user_id) " +
                "values (?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(saveUserOrder);

            statement.setString(1, userOrder.getOrderName());
            statement.setInt(2, userOrder.getCount());
            statement.setTimestamp(3, creationTimestamp);
            statement.setTimestamp(4, creationTimestamp);
            statement.setLong(5, userOrder.getUserId());
            statement.executeUpdate();

            logger.info("User order with id " + userOrder.getId() + " was saved");
            return userOrder;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<UserOrder> findAll() {
        final String findAllUserOrders = "select * from user_orders";

        List<UserOrder> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllUserOrders);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All user orders: " + result);
            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserOrder find(Long id) throws RepositoryException {
        final String findUserOrderById = "select * from user_orders where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findUserOrderById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("User order with id " + id + " was found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("User order with id " + id + " wasn't found");
            }

        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public UserOrder update(UserOrder userOrder) {
        final String updateUserOrderById = "update user_orders " +
                "set " +
                "order_name = ?,  " +
                "count = ?,  " +
                "changed = ?,  " +
                "user_id = ?  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(updateUserOrderById);

            statement.setString(1, userOrder.getOrderName());
            statement.setInt(2, userOrder.getCount());
            statement.setTimestamp(3, updateTimestamp);
            statement.setLong(4, userOrder.getUserId());
            statement.setLong(5, userOrder.getId());
            statement.executeUpdate();

            logger.info("User order with id " + userOrder.getId() + " was updated");
            return userOrder;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Long delete(UserOrder userOrder) {
        final String deleteUserOrderById = "delete from user_orders where id = ?";

        Connection connection;
        PreparedStatement statement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(deleteUserOrderById);
            statement.setLong(1, userOrder.getId());

            int deletedRows = statement.executeUpdate();
            logger.info("User order with id " + userOrder.getId() + " was deleted");
            return (long) deletedRows;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver be loaded");
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
