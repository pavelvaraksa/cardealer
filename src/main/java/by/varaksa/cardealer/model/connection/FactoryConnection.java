package by.varaksa.cardealer.model.connection;

import by.varaksa.cardealer.model.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

import static by.varaksa.cardealer.model.util.DatabasePropertiesReader.*;

class FactoryConnection {
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String url;
    private static final String login;
    private static final String password;

    static {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            url = reader.getProperty(DATABASE_URL);
            login = reader.getProperty(DATABASE_LOGIN);
            password = reader.getProperty(DATABASE_PASSWORD);
            logger.info("JDBC driver was loaded");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    static ProxyConnection createConnection() {
        ProxyConnection connection = null;
        try {
            connection = new ProxyConnection(DriverManager.getConnection(url, login, password));
            logger.info("Connection was created");
        } catch (SQLException exception) {
            logger.error("Connection wasn't created." + exception);
        }
        return connection;
    }
}