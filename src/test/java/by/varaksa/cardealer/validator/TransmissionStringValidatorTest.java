package by.varaksa.cardealer.validator;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransmissionStringValidatorTest {
    final String REGEXP_GEARS_COUNT = RegexpPropertiesReader.getRegexp("gears.count.regexp");
    final String REGEXP_WEIGHT = RegexpPropertiesReader.getRegexp("weight.regexp");

    @Test
    public void testTransmissionStringValidatePositive_first() {
        String gearsCount = String.valueOf(5);

        Pattern pattern = Pattern.compile(REGEXP_GEARS_COUNT);
        Matcher matcher = pattern.matcher(gearsCount);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testTransmissionStringValidatePositive_second() {
        String weight = String.valueOf(80);

        Pattern pattern = Pattern.compile(REGEXP_WEIGHT);
        Matcher matcher = pattern.matcher(weight);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testTransmissionStringValidateNegative_first() {
        String gearsCount = String.valueOf(15);

        Pattern pattern = Pattern.compile(REGEXP_GEARS_COUNT);
        Matcher matcher = pattern.matcher(gearsCount);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testTransmissionStringValidateNegative_second() {
        String weight = String.valueOf(1000);

        Pattern pattern = Pattern.compile(REGEXP_WEIGHT);
        Matcher matcher = pattern.matcher(weight);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testTransmissionStringValidateThrowsException_first() {
        String gearsCount = String.valueOf(6);

        Pattern pattern = Pattern.compile(REGEXP_GEARS_COUNT);
        Matcher matcher = pattern.matcher(gearsCount);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testTransmissionStringValidateThrowsException_second() {
        String weight = String.valueOf(95);

        Pattern pattern = Pattern.compile(REGEXP_WEIGHT);
        Matcher matcher = pattern.matcher(weight);

        Assert.assertTrue(matcher.matches());
    }
}
