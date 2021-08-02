package by.varaksa.cardealer.connection;

import org.apache.logging.log4j.Level;
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
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection creation error: {}", e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (freeConnection.isEmpty()) {
            logger.log(Level.FATAL, "Can not create ConnectionPool: empty");
            throw new RuntimeException("Can not create ConnectionPool:");
        } else if (freeConnection.size() == DEFAULT_POOL_SIZE) {
            logger.log(Level.INFO, "ConnectionPool successfully created");
        } else if (freeConnection.size() < DEFAULT_POOL_SIZE) {
            logger.log(Level.WARN, "ConnectionPool successfully created, default size: {}, current size: {}",
                    DEFAULT_POOL_SIZE, freeConnection.size());
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

        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Current thread was interrupted {} {}", e.getMessage(), e.getStackTrace());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    boolean releaseConnection(Connection connection) {
        boolean result = true;
        if (!(connection instanceof ProxyConnection)) {
            logger.log(Level.ERROR, "Current connection is not instance of ConnectionProxy : {}", connection);
            result = false;
        } else {

            if (busyConnection.contains(connection) || freeConnection.contains(connection)) {
                busyConnection.remove(connection);
                try {
                    freeConnection.put((ProxyConnection) connection);
                } catch (InterruptedException e) {
                    logger.log(Level.ERROR, "Current thread was interrupted {} {}", e.getMessage(), e.getStackTrace());
                    Thread.currentThread().interrupt();
                }
            } else {
                logger.log(Level.ERROR, "Current connection does not belong to connection pool  : {}", connection);
                result = false;
            }
        }
        return result;
    }

    public void destroyConnectionPool() {
        while (!freeConnection.isEmpty()) {
            try {
                freeConnection.take().reallyClose();
            } catch (SQLException e) {
                logger.log(Level.WARN, "Connection is not closed: {}", e.getMessage());
            } catch (InterruptedException e) {
                logger.log(Level.WARN, "Current thread was interrupted: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        while (!busyConnection.isEmpty()) {
            try {
                ProxyConnection connection = busyConnection.take();
                //TODO connection.rollback();
                connection.reallyClose();
            } catch (SQLException e) {
                logger.log(Level.WARN, "Connection is not closed: {}", e.getMessage());
            } catch (InterruptedException e) {
                logger.log(Level.WARN, "Current thread was interrupted: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
        logger.log(Level.INFO, "Connection pool successfully destroyed");
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error occured while deregistering driver {}: {} {}", driver, e.getMessage(),
                        e.getStackTrace());
            }
        }
    }
}