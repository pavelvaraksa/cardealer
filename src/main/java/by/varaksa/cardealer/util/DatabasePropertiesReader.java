package by.varaksa.cardealer.util;

import java.util.ResourceBundle;

/**
 * Class {@code DatabasePropertiesReader} designed for reading
 * database.properties file
 *
 * @author Pavel Varaksa
 *
 */
public class DatabasePropertiesReader {
    private static DatabasePropertiesReader instance;
    private ResourceBundle resourceBundle;

    public static final String BUNDLE_NAME = "database";
    public static final String DATABASE_DRIVER_NAME = "driver.name";
    public static final String DATABASE_URL = "url";
    public static final String DATABASE_LOGIN = "login";
    public static final String DATABASE_PASSWORD = "password";

    public static DatabasePropertiesReader getInstance() {

        if (instance == null) {
            instance = new DatabasePropertiesReader();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }

        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
