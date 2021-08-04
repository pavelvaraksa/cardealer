package by.varaksa.cardealer.connection;

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

public class PoolConnection {
    private static final Logger logger = LogManager.getLogger();
    private static PoolConnection instance;
    private static final int DEFAULT_POOL_SIZE = 5;
    private static final AtomicBoolean isConnectionPoolCreated = new AtomicBoolean(false);
    private final BlockingQueue<ProxyConnection> freeConnection;
    private final BlockingQueue<ProxyConnection> busyConnection;

    private PoolConnection() {
        busyConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        freeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = FactoryConnection.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.put(proxyConnection);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        if (freeConnection.isEmpty()) {
            String errorMessage = ("Connection pool wasn't created");
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        } else {
            logger.info("Connection pool was created");
        }
    }

    public static PoolConnection getInstance() {
        while (instance == null) {

            if (isConnectionPoolCreated.compareAndSet(false, true)) {
                instance = new PoolConnection();
            }
        }

        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnection.take();
            busyConnection.put(connection);
        } catch (InterruptedException exception) {
            logger.error("Current thread was interrupted." + exception);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    void releaseConnection(Connection connection) {

        if (!(connection instanceof ProxyConnection)) {
            logger.error("Current connection wasn't instance of proxy connection");
        } else {

            if (busyConnection.contains(connection) || freeConnection.contains(connection)) {
                busyConnection.remove(connection);
                try {
                    freeConnection.put((ProxyConnection) connection);
                } catch (InterruptedException exception) {
                    logger.error("Current thread was interrupted." + exception);
                    Thread.currentThread().interrupt();
                }
            } else {
                logger.error("Current connection wasn't refer to connection pool");
            }
        }
    }

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
                ProxyConnection connection = busyConnection.take();
                connection.reallyClose();
            } catch (SQLException e) {
                logger.error("Connection wasn't closed");
            } catch (InterruptedException exception) {
                logger.error("Connection was interrupted." + exception);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
        logger.info("Connection pool was destroyed interrupted");
    }

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