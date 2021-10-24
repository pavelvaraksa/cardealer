package by.varaksa.cardealer.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation of input data
 *
 * @author Pavel Varaksa
 */
public class StringValidator {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Validates form input
     *
     * @param regexp       {@code String} regular expression
     * @param stringFromUi {@code String} entered string
     * @return {@code true} if result was correct {@code false} if result wasn't correct
     */
    public static boolean isStringValidate(String regexp, String stringFromUi) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(stringFromUi);

        if (matcher.matches()) {
            logger.info("Entered string " + stringFromUi + " was passed validation");
            return true;
        }

        logger.error("Entered string " + stringFromUi + " wasn't passed validation");
        return false;
    }
}
