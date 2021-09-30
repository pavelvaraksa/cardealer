package by.varaksa.cardealer.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    private static final Logger logger = LogManager.getLogger();

    public static boolean isStringValidate(String regexp, String stringFromUi) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(stringFromUi);

        if (matcher.matches()) {
            logger.info("Entered string " + stringFromUi + " was correct");
            return true;
        }

        logger.error("Entered string " + stringFromUi + " wasn't correct");
        return false;
    }
}
