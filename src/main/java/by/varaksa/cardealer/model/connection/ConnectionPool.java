package by.varaksa.cardealer.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Creating connection pool with {@link ProxyConnection} objects
 *
 * @author Pavel Varaksa
 */
public enum ConnectionPool {
    INSTANCE;

    private final Logger logger = LogManager.getLogger();
    private final BlockingQueue<ProxyConnection> freeConnection;
    private final Queue<ProxyConnection> givenAwayConnection;
    private static final int DEFAULT_POOL_SIZE = 5;

    ConnectionPool() {
        freeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        givenAwayConnection = new ArrayDeque<>();

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = ConnectionFactory.createConnection();
            ProxyConnection connectionProxy = new ProxyConnection(connection);
            freeConnection.offer(connectionProxy);
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
     * @return {@link ProxyConnection} object from {@link #freeConnection}
     */
    public Connection getConnection() {
        ProxyConnection connection = null;

        try {
            connection = freeConnection.take();
            givenAwayConnection.offer(connection);
        } catch (InterruptedException exception) {
            logger.error("Current thread was interrupted." + exception);
        }

        return connection;
    }

    /**
     * Releases connection from busy connections
     *
     * @param connection {@code Connection} connection to release
     */
    public void releaseConnection(Connection connection) {

        if (!(connection instanceof ProxyConnection)) {
            logger.error("Current connection wasn't instance of proxy connection");
        } else {
            if (givenAwayConnection.remove(connection)) {
                freeConnection.offer((ProxyConnection) connection);
            }
        }
    }

    /**
     * Closes connections of connection pool
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (SQLException | InterruptedException exception) {
                logger.error("Connection wasn't closed." + exception);
            }
        }
        deregisterDrivers();
    }

    /**
     * Deregister drivers
     */
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exception) {
                logger.error("Driver registration error." + exception);
            }
        });
    }
}
