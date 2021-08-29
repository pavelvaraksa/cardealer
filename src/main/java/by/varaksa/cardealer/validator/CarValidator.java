package by.varaksa.cardealer.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarValidator {
    private static final Logger logger = LogManager.getLogger();

    /* Regex for the guarantee period, 1 to 2 characters */
    public static final String GUARANTEE_PERIOD_REGEXP = "guarantee_period.regexp";
    /* Regex for the price, 1 to 6 characters */
    public static final String PRICE_REGEXP = "price.regexp";
    /* Regex for the car id, 1 to 3 characters */
    public static final String CAR_ID_REGEXP = "car_id.regexp";
    /* Regex for the volume, 1 to 3 characters including point */
    public static final String VOLUME_REGEXP = "volume.regexp";
    /* Regex for the cylinders count, 1 to 2 characters */
    public static final String CYLINDERS_COUNT_REGEXP = "cylinders_count.regexp";
    /* Regex for the gears count, 1 character */
    public static final String GEARS_COUNT_REGEXP = "gears_count.regexp";
    /* Regex for the weight, 1 to 3 characters including point */
    public static final String WEIGHT_REGEXP = "weight.regexp";

    public static boolean isCarValidate(String regexp, String stringFromUI) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(stringFromUI);

        if (matcher.matches()) {
            logger.info("Entered string was correct");
            return true;
        }

        logger.error("Entered string wasn't correct");
        return false;
    }
}
