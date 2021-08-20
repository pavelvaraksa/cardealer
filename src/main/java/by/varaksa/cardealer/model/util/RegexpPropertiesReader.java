package by.varaksa.cardealer.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class RegexpPropertiesReader {
    private static final Logger logger = LogManager.getLogger();
    private static final ResourceBundle resourceBundle;
    private static final String REGEXP_PROPERTIES_NAME = "regExp";

    static {
        try {
            resourceBundle = ResourceBundle.getBundle(REGEXP_PROPERTIES_NAME);
        } catch (MissingResourceException exception) {
            String errorMessage = "Resource bundle wasn't found for this base name " + exception;
            logger.error(errorMessage);
            throw new ExceptionInInitializerError(errorMessage);
        }
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
