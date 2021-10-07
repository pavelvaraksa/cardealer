package by.varaksa.cardealer.validator;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngineStringValidatorTest {
    final String REGEXP_VOLUME = RegexpPropertiesReader.getRegexp("volume.regexp");
    final String REGEXP_CYLINDERS_COUNT = RegexpPropertiesReader.getRegexp("cylinders.count.regexp");

    @Test
    public void testEngineStringValidatePositive_first() {
        String volume = String.valueOf(3.2);

        Pattern pattern = Pattern.compile(REGEXP_VOLUME);
        Matcher matcher = pattern.matcher(volume);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testEngineStringValidatePositive_second() {
        String cylindersCount = String.valueOf(6);

        Pattern pattern = Pattern.compile(REGEXP_CYLINDERS_COUNT);
        Matcher matcher = pattern.matcher(cylindersCount);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testEngineStringValidateNegative_first() {
        String cylindersCount = String.valueOf(111);

        Pattern pattern = Pattern.compile(REGEXP_CYLINDERS_COUNT);
        Matcher matcher = pattern.matcher(cylindersCount);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testEngineStringValidateNegative_second() {
        String volume = "volume";

        Pattern pattern = Pattern.compile(REGEXP_VOLUME);
        Matcher matcher = pattern.matcher(volume);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testEngineStringValidateThrowsException_first() {
        String volume = String.valueOf(2.0);

        Pattern pattern = Pattern.compile(REGEXP_VOLUME);
        Matcher matcher = pattern.matcher(volume);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testEngineStringValidateThrowsException_second() {
        String cylindersCount = String.valueOf(4);

        Pattern pattern = Pattern.compile(REGEXP_CYLINDERS_COUNT);
        Matcher matcher = pattern.matcher(cylindersCount);

        Assert.assertTrue(matcher.matches());
    }
}
