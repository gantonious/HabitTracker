package ca.antonious.habittracker.utiltests;

import org.junit.Test;

import ca.antonious.habittracker.utils.Utils;

import static org.junit.Assert.*;

/**
 * Created by George on 2016-09-13.
 */
public class UtilsTests {

    @Test
    public void test_equals_ifLeftSideIsNullAndRightIsNonNull_thenReturnsFalse() {
        String lhs = null;
        String rhs = "hello";

        boolean result = Utils.equals(lhs, rhs);

        assertFalse(result);
    }

    @Test
    public void test_equals_ifLeftSideIsNonNullAndRightIsNull_thenReturnsFalse() {
        String lhs = "hello";
        String rhs = null;

        boolean result = Utils.equals(lhs, rhs);

        assertFalse(result);
    }

    @Test
    public void test_equals_ifBothSidesAreNonEqual_thenReturnsFalse() {
        String lhs = "hello";
        String rhs = "bye";

        boolean result = Utils.equals(lhs, rhs);

        assertFalse(result);
    }

    @Test
    public void test_equals_ifBothSidesAreNull_thenReturnsTrue() {
        String lhs = null;
        String rhs = null;

        boolean result = Utils.equals(lhs, rhs);

        assertTrue(result);
    }

    @Test
    public void test_equals_ifBothSidesEqual_thenReturnsTrue() {
        String lhs = "hello";
        String rhs = "hello";

        boolean result = Utils.equals(lhs, rhs);

        assertTrue(result);
    }
}
