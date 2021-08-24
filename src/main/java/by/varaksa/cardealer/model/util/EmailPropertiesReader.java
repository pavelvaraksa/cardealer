package by.varaksa.cardealer.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class EmailPropertiesReader {
    private static final Logger logger = LogManager.getLogger();
    private static final ResourceBundle resourceBundle;
    private static final String PROPERTIES_NAME = "email";

    static {
        try {
            resourceBundle = ResourceBundle.getBundle(PROPERTIES_NAME);
        } catch (MissingResourceException exception) {
            String errorMessage = "Resource bundle wasn't found." + exception;
            logger.error(errorMessage);
            throw new ExceptionInInitializerError(errorMessage);
        }
    }

    public static String getEmail(String key) {
        return resourceBundle.getString(key);
    }
}
