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
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String ORDER_NAME = "order_name";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String USER_ID = "user_id";

    private UserOrder parseResultSet(ResultSet resultSet) throws SQLException {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(resultSet.getLong(ID));
        userOrder.setOrderName(resultSet.getString(ORDER_NAME));
        userOrder.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        userOrder.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        userOrder.setUserId(resultSet.getLong(USER_ID));
        return userOrder;
    }

    private static final String SAVE_USER_ORDER = "insert into user_orders (order_name, created, changed, user_id) " +
            "values (?,?,?,?)";
    private static final String FIND_ALL_USER_ORDERS = "select * from user_orders";
    private static final String FIND_USER_ORDER_BY_ID = "select * from user_orders where id = ?";
    private static final String UPDATE_USER_ORDER_BY_ID = "update user_orders " +
            "set " +
            "order_name = ?,  " +
            "changed = ?,  " +
            "user_id = ?  " +
            "where id = ?";
    private static final String DELETE_USER_ORDER_BY_ID = "delete from user_orders where id = ?";

    @Override
    public UserOrder save(UserOrder userOrder) throws RepositoryException {
        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(SAVE_USER_ORDER);

            statement.setString(1, userOrder.getOrderName());
            statement.setTimestamp(2, creationTimestamp);
            statement.setTimestamp(3, creationTimestamp);
            statement.setLong(4, userOrder.getUserId());
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
            resultSet = statement.executeQuery(FIND_ALL_USER_ORDERS);

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
    public UserOrder find(Long id) throws RepositoryException {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(FIND_USER_ORDER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

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
        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(UPDATE_USER_ORDER_BY_ID);

            statement.setString(1, userOrder.getOrderName());
            statement.setTimestamp(2, updateTimestamp);
            statement.setLong(3, userOrder.getUserId());
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
        Connection connection;
        PreparedStatement statement;
        UserOrder userOrder = new UserOrder();

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(DELETE_USER_ORDER_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();

            return userOrder;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver was loaded from user order repository class");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded from user order repository class." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
