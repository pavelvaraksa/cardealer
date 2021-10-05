package test.varaksa.cardealer.validator;

import by.varaksa.cardealer.exception.ServiceException;
import by.varaksa.cardealer.util.RegexpPropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserStringValidatorTest {
    final String REGEXP_FIRSTNAME = RegexpPropertiesReader.getRegexp("firstname.regexp");
    final String REGEXP_LASTNAME = RegexpPropertiesReader.getRegexp("lastname.regexp");
    final String REGEXP_PHONE_NUMBER = RegexpPropertiesReader.getRegexp("phone.number.regexp");
    final String REGEXP_LOGIN = RegexpPropertiesReader.getRegexp("login.regexp");
    final String REGEXP_PASSWORD = RegexpPropertiesReader.getRegexp("password.regexp");
    final String REGEXP_EMAIL = RegexpPropertiesReader.getRegexp("email.regexp");

    @Test
    public void testUserStringValidatePositive_first() {
        String firstname = "Ivan";

        Pattern pattern = Pattern.compile(REGEXP_FIRSTNAME);
        Matcher matcher = pattern.matcher(firstname);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testUserStringValidatePositive_second() {
        String lastname = "Ivanov";

        Pattern pattern = Pattern.compile(REGEXP_LASTNAME);
        Matcher matcher = pattern.matcher(lastname);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testUserStringValidatePositive_third() {
        String phoneNumber = String.valueOf(291112233);

        Pattern pattern = Pattern.compile(REGEXP_PHONE_NUMBER);
        Matcher matcher = pattern.matcher(phoneNumber);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testUserStringValidateNegative_first() {
        String login = "a";

        Pattern pattern = Pattern.compile(REGEXP_LOGIN);
        Matcher matcher = pattern.matcher(login);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testUserStringValidateNegative_second() {
        String password = "a";

        Pattern pattern = Pattern.compile(REGEXP_PASSWORD);
        Matcher matcher = pattern.matcher(password);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testUserStringValidateNegative_third() {
        String email = "ivan";

        Pattern pattern = Pattern.compile(REGEXP_EMAIL);
        Matcher matcher = pattern.matcher(email);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUserStringValidateThrowsException_first() {
        String firstname = "Ivan";

        Pattern pattern = Pattern.compile(REGEXP_FIRSTNAME);
        Matcher matcher = pattern.matcher(firstname);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUserStringValidateThrowsException_second() {
        String lastname = "Ivanov";

        Pattern pattern = Pattern.compile(REGEXP_LASTNAME);
        Matcher matcher = pattern.matcher(lastname);

        Assert.assertTrue(matcher.matches());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUserStringValidateThrowsException_third() {
        String phoneNumber = String.valueOf(291112233);

        Pattern pattern = Pattern.compile(REGEXP_PHONE_NUMBER);
        Matcher matcher = pattern.matcher(phoneNumber);

        Assert.assertTrue(matcher.matches());
    }
}