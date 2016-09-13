package ca.antonious.habittracker;

import org.junit.Assert;
import org.junit.Test;

import ca.antonious.habittracker.utils.StringUtils;

/**
 * Created by George on 2016-09-12.
 */
public class StringUtilsTest {
    @Test
    public void test_isStringNullOrEmpty_ifStringIsNull_thenReturnsTrue() {
        boolean expectedOutput = true;
        boolean actualOutput = StringUtils.isStringNullOrEmpty(null);

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void test_isStringNullOrEmpty_ifStringIsEmpty_thenReturnsTrue() {
        boolean expectedOutput = true;
        boolean actualOutput = StringUtils.isStringNullOrEmpty("");

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void test_isStringNullOrEmpty_ifStringIsNotEmpty_thenReturnsFalse() {
        boolean expectedOutput = false;
        boolean actualOutput = StringUtils.isStringNullOrEmpty("hi");

        Assert.assertEquals(expectedOutput, actualOutput);
    }
}
