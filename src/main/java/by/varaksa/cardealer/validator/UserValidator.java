package by.varaksa.cardealer.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();

    /* Regex for the first name, 2 to 20 characters including hyphen */
    public static final String FIRST_NAME_REGEXP = "firstname.regexp";
    /* Regex for the last name, 2 to 30 characters including hyphen */
    public static final String LAST_NAME_REGEXP = "lastname.regexp";
    /* Regex for the login, 5 to 30 characters including numbers, point, underscore and hyphen */
    public static final String LOGIN_REGEXP = "login.regexp";
    /* Regex for the password, 5 to 55 characters including numbers, point, underscore and hyphen */
    public static final String PASSWORD_REGEXP = "password.regexp";
    /* Regex for the email, 5 to 55 characters including numbers, point, underscore, hyphen and symbol @ */
    public static final String EMAIL_REGEXP = "email.regexp";
    /* Regex for the user order id, 1 to 3 characters */
    public static final String USER_ORDER_ID = "user_order_id.regexp";

    public static boolean isUserValidate(String regexp, String stringFromUI) {
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
