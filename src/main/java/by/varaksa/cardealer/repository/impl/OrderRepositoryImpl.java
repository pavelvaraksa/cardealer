package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.*;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.OrderRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class OrderRepositoryImpl implements OrderRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String ORDER_NAME = "order_name";

    private Order parseResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(ID));
        order.setOrderName(resultSet.getString(ORDER_NAME));
        return order;
    }

    @Override
    public Order save(Order order) throws RepositoryException {
        final String saveOrder = "insert into orders (order_name) " +
                "values (?)";

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
            statement = connection.prepareStatement(saveOrder);

            statement.setString(1, order.getOrderName());
            statement.executeUpdate();

            logger.info("Order with id " + order.getId() + " was saved");
            return order;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Order> findAll() {
        final String findAllOrders = "select * from orders";

        List<Order> result = new ArrayList<>();
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
            resultSet = statement.executeQuery(findAllOrders);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All orders: " + result);
            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Order find(Long id) throws RepositoryException {
        final String findOrderById = "select * from orders where id = ?";

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
            statement = connection.prepareStatement(findOrderById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("Order with id " + id + " was found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Order with id " + id + " wasn't found");
            }

        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Order update(Order order) {
        final String updateOrderById = "update orders " +
                "set " +
                "order_name = ?  " +
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
            statement = connection.prepareStatement(updateOrderById);

            statement.setString(1, order.getOrderName());
            statement.setLong(2, order.getId());
            statement.executeUpdate();

            logger.info("Order with id " + order.getId() + " was updated");
            return order;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Long delete(Order order) {
        final String deleteOrderById = "delete from orders where id = ?";

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
            statement = connection.prepareStatement(deleteOrderById);
            statement.setLong(1, order.getId());

            int deletedRows = statement.executeUpdate();
            logger.info("Order with id " + order.getId() + " was deleted");
            return (long) deletedRows;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
