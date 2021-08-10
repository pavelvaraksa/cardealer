package by.varaksa.cardealer.model.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarValidator {
    private static final Logger logger = LogManager.getLogger();

    /* Regex for the guarantee period, 1 to 2 characters */
    public static final String GUARANTEE_PERIOD_REGEXP = "[0-9]{1,2}";
    /* Regex for the price, 1 to 6 characters */
    public static final String PRICE_REGEXP = "[0-9]{1,6}";
    /* Regex for the car id, 1 to 3 characters */
    public static final String CAR_ID_REGEXP = "[0-9]{1,3}";

    public static boolean isCarValidate(String regexp, String stringFromUI) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(stringFromUI);

        if (matcher.matches()) {
            logger.info("Entered string was correct");
            return true;
        } else {
            logger.error("Entered string wasn't correct");
            return false;
        }
    }
}
