package by.varaksa.cardealer.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Creating connection pool with {@link ConnectionProxy} objects
 *
 * @author Pavel Varaksa
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static ConnectionPool instance;
    private static final int DEFAULT_POOL_SIZE = 5;
    /**
     * {@link AtomicBoolean} for {@link ConnectionPool#getInstance()} singleton
     * implementation
     */
    private static final AtomicBoolean isConnectionPoolCreated = new AtomicBoolean(false);
    /**
     * {@link BlockingQueue} with free connections to database
     */
    private final BlockingQueue<ConnectionProxy> freeConnection;
    /**
     * {@link BlockingQueue} with busy connections to database
     */
    private final BlockingQueue<ConnectionProxy> busyConnection;

    private ConnectionPool() {
        busyConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        freeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionFactory.createConnection();
                ConnectionProxy connectionProxy = new ConnectionProxy(connection);
                freeConnection.put(connectionProxy);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        if (freeConnection.isEmpty()) {
            String errorMessage = "Connection pool wasn't created";
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        } else {
            logger.info("Connection pool was created");
        }
    }

    /**
     * Returns {@code ConnectionPool} instance
     *
     * @return new or existing {@link ConnectionPool} object
     */
    public static ConnectionPool getInstance() {
        while (instance == null) {

            if (isConnectionPoolCreated.compareAndSet(false, true)) {
                instance = new ConnectionPool();
            }
        }

        return instance;
    }

    /**
     * @return {@link ConnectionProxy} object from {@link #freeConnection}
     */
    public ConnectionProxy getConnection() {
        ConnectionProxy connection = null;
        try {
            connection = freeConnection.take();
            busyConnection.put(connection);
        } catch (InterruptedException exception) {
            logger.error("Current thread was interrupted." + exception);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Releases connection from occupied connections
     *
     * @param connection {@code Connection} connection to release
     */
    void releaseConnection(Connection connection) {
        if (!(connection instanceof ConnectionProxy)) {
            logger.error("Current connection wasn't instance of proxy connection");
        } else {
            if (busyConnection.remove(connection)) {
                try {
                    freeConnection.put((ConnectionProxy) connection);
                } catch (InterruptedException exception) {
                    logger.error("Current thread was interrupted." + exception);
                    Thread.currentThread().interrupt();
                }
            } else {
                logger.error("Current connection wasn't refer to connection pool");
            }
        }
    }

    /**
     * Closes connections of connection pool
     */
    public void destroyConnectionPool() {
        while (!freeConnection.isEmpty()) {

            try {
                freeConnection.take().reallyClose();
            } catch (SQLException exception) {
                logger.error("Connection wasn't closed");
            } catch (InterruptedException exception) {
                logger.error("Connection was interrupted." + exception);
                Thread.currentThread().interrupt();
            }
        }

        while (!busyConnection.isEmpty()) {
            try {
                ConnectionProxy connection = busyConnection.take();
                connection.reallyClose();
            } catch (SQLException e) {
                logger.error("Connection wasn't closed");
            } catch (InterruptedException exception) {
                logger.error("Connection was interrupted." + exception);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
        logger.info("Connection pool was destroyed");
    }

    /**
     * Deregister drivers
     */
    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exception) {
                logger.error("Driver registration error." + exception);
            }
        }
    }
}