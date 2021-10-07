package by.varaksa.cardealer.util;

import by.varaksa.cardealer.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailPropertiesReaderTest {
    final String ACTUAL_PROPERTIES_NAME = "email";
    final String EXPECTED_PROPERTIES_NAME_POSITIVE = "email";
    final String EXPECTED_PROPERTIES_NAME_NEGATIVE = "email111";

    @Test
    public void testGetEmailPositive() {
        Assert.assertEquals(ACTUAL_PROPERTIES_NAME, EXPECTED_PROPERTIES_NAME_POSITIVE);
    }

    @Test
    public void testGetEmailNegative() {
        Assert.assertEquals(ACTUAL_PROPERTIES_NAME, EXPECTED_PROPERTIES_NAME_NEGATIVE);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetEmailThrowsException() {
        Assert.assertEquals(ACTUAL_PROPERTIES_NAME, EXPECTED_PROPERTIES_NAME_POSITIVE);
    }
}