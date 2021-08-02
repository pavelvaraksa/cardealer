package by.varaksa.cardealer.connection;

import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;

class FactoryConnection {
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    static {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver was loaded");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private FactoryConnection() {
    }

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
    }
}