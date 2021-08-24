package by.varaksa.cardealer.model.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealerValidator {
    private static final Logger logger = LogManager.getLogger();

    /* Regex for the name, 1 to 20 characters including hyphen */
    public static final String DEALER_NAME_REGEXP = "dealer_name.regexp";
    /* Regex for the address, 1 to 55 characters including numbers, point, underscore and hyphen */
    public static final String DEALER_ADDRESS_REGEXP = "dealer_address.regexp";

    public static boolean isDealerValidate(String regexp, String stringFromUI) {
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
