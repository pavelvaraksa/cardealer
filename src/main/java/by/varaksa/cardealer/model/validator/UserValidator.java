package by.varaksa.cardealer.model.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();

    /* Regex for the first name, 2 to 20 characters including hyphen */
    public static final String FIRST_NAME_REGEXP = "[a-zA-Z[а-яА-Я]\\-]{2,20}";
    /* Regex for the last name, 2 to 30 characters including hyphen */
    public static final String LAST_NAME_REGEXP = "[a-zA-Z[а-яА-Я]\\-]{2,30}";
    /* Regex for the login, 5 to 30 characters including numbers, point, underscore and hyphen */
    public static final String LOGIN_REGEXP = "[a-zA-Z0-9[а-яА-Я]\\._\\-]{5,30}";
    /* Regex for the password, 5 to 55 characters including numbers, point, underscore and hyphen */
    public static final String PASSWORD_REGEXP = "[a-zA-Z0-9[а-яА-Я]\\._\\-]{5,55}";
    /* Regex for the email, 5 to 55 characters including numbers, point, underscore, hyphen and symbol @ */
    public static final String EMAIL_REGEXP = "[a-zA-Z0-9[а-яА-Я]\\._\\-+@]{5,55}";

    public static boolean userValidate(String regexp, String stringFromUI) {
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
