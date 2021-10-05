package test.varaksa.cardealer.validator;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealerStringValidatorTest {
    final String REGEXP_DEALER_NAME = RegexpPropertiesReader.getRegexp("dealer.name.regexp");
    final String REGEXP_DEALER_ADDRESS = RegexpPropertiesReader.getRegexp("dealer.address.regexp");

    @Test
    public void testDealerStringValidatePositive_first() {
        String dealerName = "Audi center Minsk";

        Pattern pattern = Pattern.compile(REGEXP_DEALER_NAME);
        Matcher matcher = pattern.matcher(dealerName);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testDealerStringValidatePositive_second() {
        String dealerAddress = "Nezavisimosti Avenue, 198";

        Pattern pattern = Pattern.compile(REGEXP_DEALER_ADDRESS);
        Matcher matcher = pattern.matcher(dealerAddress);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testDealerStringValidateNegative_first() {
        String dealerName = "";

        Pattern pattern = Pattern.compile(REGEXP_DEALER_NAME);
        Matcher matcher = pattern.matcher(dealerName);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testDealerStringValidateNegative_second() {
        String dealerAddress = "";

        Pattern pattern = Pattern.compile(REGEXP_DEALER_ADDRESS);
        Matcher matcher = pattern.matcher(dealerAddress);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testDealerStringValidateThrowsException_first() {
        String dealerName = "Audi center Minsk";

        Pattern pattern = Pattern.compile(REGEXP_DEALER_NAME);
        Matcher matcher = pattern.matcher(dealerName);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testDealerStringValidateThrowsException_second() {
        String dealerAddress = "Nezavisimosti Avenue, 198";

        Pattern pattern = Pattern.compile(REGEXP_DEALER_ADDRESS);
        Matcher matcher = pattern.matcher(dealerAddress);

        Assert.assertTrue(matcher.matches());
    }
}
