package by.varaksa.cardealer.model.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserOrderValidator {
    private static final Logger logger = LogManager.getLogger();

    /* Regex for the order name, 1 to 30 characters including numbers, underscore and hyphen */
    public static final String ORDER_NAME_REGEXP = "[a-zA-Z0-9[а-яА-Я]\\_\\-]{1,30}";
    /* Regex for the user id, 1 to 3 characters */
    public static final String USER_ID_REGEXP = "[0-9]{1,5}";

    public static boolean isUserOrderValidate(String regexp, String stringFromUI) {
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
