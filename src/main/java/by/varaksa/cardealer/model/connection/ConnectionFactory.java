package by.varaksa.cardealer.model.connection;

import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String url;
    private static final String login;
    private static final String password;

    /*
     * Static block for database driver registration
     */
    static {
        try {
            Class.forName(reader.getProperty(DatabasePropertiesReader.DATABASE_DRIVER_NAME));
            url = reader.getProperty(DatabasePropertiesReader.DATABASE_URL);
            login = reader.getProperty(DatabasePropertiesReader.DATABASE_LOGIN);
            password = reader.getProperty(DatabasePropertiesReader.DATABASE_PASSWORD);
            logger.info("JDBC driver was loaded");
        } catch (ClassNotFoundException exception) {
            String errorMessage = "JDBC driver wasn't loaded." + exception;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private ConnectionFactory() {
    }

    /**
     * @return new {@link ConnectionProxy} with provided data
     */
    static Connection createConnection() {
        Connection connection = null;
        try {
            connection = new ConnectionProxy(DriverManager.getConnection(url, login, password));
            logger.info("Connection was created");
        } catch (SQLException exception) {
            logger.error("Connection wasn't created." + exception);
        }
        return connection;
    }
}