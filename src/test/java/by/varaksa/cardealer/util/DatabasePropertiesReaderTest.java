package by.varaksa.cardealer.util;

import by.varaksa.cardealer.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabasePropertiesReaderTest {
    final String ACTUAL_BUNDLE_NAME = "database";
    final String EXPECTED_BUNDLE_NAME_POSITIVE = "database";
    final String EXPECTED_BUNDLE_NAME_NEGATIVE = "database111";
    final String ACTUAL_DATABASE_DRIVER_NAME = "driver.name";
    final String EXPECTED_DATABASE_DRIVER_NAME_POSITIVE = "driver.name";
    final String EXPECTED_DATABASE_DRIVER_NAME_NEGATIVE = "driver.name111";
    final String ACTUAL_DATABASE_URL = "url";
    final String EXPECTED_DATABASE_URL_POSITIVE = "url";
    final String EXPECTED_DATABASE_URL_NEGATIVE = "url111";
    final String ACTUAL_DATABASE_LOGIN = "login";
    final String EXPECTED_DATABASE_LOGIN_POSITIVE = "login";
    final String EXPECTED_DATABASE_LOGIN_NEGATIVE = "login111";
    final String ACTUAL_DATABASE_PASSWORD = "password";
    final String EXPECTED_DATABASE_PASSWORD_POSITIVE = "password";
    final String EXPECTED_DATABASE_PASSWORD_NEGATIVE = "password111";

    @Test
    public void testGetInstancePositive() {
        Assert.assertEquals(ACTUAL_BUNDLE_NAME, EXPECTED_BUNDLE_NAME_POSITIVE);
    }

    @Test
    public void testGetInstanceNegative() {
        Assert.assertEquals(ACTUAL_BUNDLE_NAME, EXPECTED_BUNDLE_NAME_NEGATIVE);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetInstanceThrowsException() {
        Assert.assertEquals(ACTUAL_BUNDLE_NAME, EXPECTED_BUNDLE_NAME_POSITIVE);
    }

    @Test
    public void testGetPropertyDriverNamePositive() {
        Assert.assertEquals(ACTUAL_DATABASE_DRIVER_NAME, EXPECTED_DATABASE_DRIVER_NAME_POSITIVE);
    }

    @Test
    public void testGetPropertyDriverNameNegative() {
        Assert.assertEquals(ACTUAL_DATABASE_DRIVER_NAME, EXPECTED_DATABASE_DRIVER_NAME_NEGATIVE);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetPropertyDriverNameThrowsException() {
        Assert.assertEquals(ACTUAL_DATABASE_DRIVER_NAME, EXPECTED_DATABASE_DRIVER_NAME_POSITIVE);
    }

    @Test
    public void testGetPropertyUrlPositive() {
        Assert.assertEquals(ACTUAL_DATABASE_URL, EXPECTED_DATABASE_URL_POSITIVE);
    }

    @Test
    public void testGetPropertyUrlNegative() {
        Assert.assertEquals(ACTUAL_DATABASE_URL, EXPECTED_DATABASE_URL_NEGATIVE);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetPropertyUrlThrowsException() {
        Assert.assertEquals(ACTUAL_DATABASE_URL, EXPECTED_DATABASE_URL_POSITIVE);
    }

    @Test
    public void testGetPropertyLoginPositive() {
        Assert.assertEquals(ACTUAL_DATABASE_LOGIN, EXPECTED_DATABASE_LOGIN_POSITIVE);
    }

    @Test
    public void testGetPropertyLoginNegative() {
        Assert.assertEquals(ACTUAL_DATABASE_LOGIN, EXPECTED_DATABASE_LOGIN_NEGATIVE);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetPropertyLoginThrowsException() {
        Assert.assertEquals(ACTUAL_DATABASE_LOGIN, EXPECTED_DATABASE_LOGIN_POSITIVE);
    }

    @Test
    public void testGetPropertyPasswordPositive() {
        Assert.assertEquals(ACTUAL_DATABASE_PASSWORD, EXPECTED_DATABASE_PASSWORD_POSITIVE);
    }

    @Test
    public void testGetPropertyPasswordNegative() {
        Assert.assertEquals(ACTUAL_DATABASE_PASSWORD, EXPECTED_DATABASE_PASSWORD_NEGATIVE);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetPropertyPasswordThrowsException() {
        Assert.assertEquals(ACTUAL_DATABASE_PASSWORD, EXPECTED_DATABASE_PASSWORD_POSITIVE);
    }
}