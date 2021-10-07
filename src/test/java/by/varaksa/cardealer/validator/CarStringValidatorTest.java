package by.varaksa.cardealer.validator;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarStringValidatorTest {
    final String REGEXP_GUARANTEE_PERIOD = RegexpPropertiesReader.getRegexp("guarantee.period.regexp");
    final String REGEXP_PRICE = RegexpPropertiesReader.getRegexp("price.regexp");

    @Test
    public void testCarStringValidatePositive_first() {
        String guaranteePeriod = String.valueOf(10);

        Pattern pattern = Pattern.compile(REGEXP_GUARANTEE_PERIOD);
        Matcher matcher = pattern.matcher(guaranteePeriod);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testCarStringValidatePositive_second() {
        String price = String.valueOf(25000);

        Pattern pattern = Pattern.compile(REGEXP_PRICE);
        Matcher matcher = pattern.matcher(price);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testCarStringValidateNegative_first() {
        String guaranteePeriod = "guarantee period";

        Pattern pattern = Pattern.compile(REGEXP_GUARANTEE_PERIOD);
        Matcher matcher = pattern.matcher(guaranteePeriod);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testCarStringValidateNegative_second() {
        String price = "price";

        Pattern pattern = Pattern.compile(REGEXP_PRICE);
        Matcher matcher = pattern.matcher(price);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testCarStringValidateThrowsException_first() {
        String guaranteePeriod = String.valueOf(8);

        Pattern pattern = Pattern.compile(REGEXP_GUARANTEE_PERIOD);
        Matcher matcher = pattern.matcher(guaranteePeriod);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testCarStringValidateThrowsException_second() {
        String price = String.valueOf(19000);

        Pattern pattern = Pattern.compile(REGEXP_PRICE);
        Matcher matcher = pattern.matcher(price);

        Assert.assertTrue(matcher.matches());
    }
}
