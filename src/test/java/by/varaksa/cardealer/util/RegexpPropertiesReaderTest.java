package by.varaksa.cardealer.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import by.varaksa.cardealer.exception.ServiceException;

public class RegexpPropertiesReaderTest {
    final String ACTUAL_PROPERTIES_NAME = "regexp";
    final String EXPECTED_PROPERTIES_NAME_POSITIVE = "regexp";
    final String EXPECTED_PROPERTIES_NAME_NEGATIVE = "regexp111";

    @Test
    public void testGetRegexpPositive() {
        Assert.assertEquals(ACTUAL_PROPERTIES_NAME, EXPECTED_PROPERTIES_NAME_POSITIVE);
    }

    @Test
    public void testGetRegexpNegative() {
        Assert.assertEquals(ACTUAL_PROPERTIES_NAME, EXPECTED_PROPERTIES_NAME_NEGATIVE);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetRegexpThrowsException() {
        Assert.assertEquals(ACTUAL_PROPERTIES_NAME, EXPECTED_PROPERTIES_NAME_POSITIVE);
    }
}