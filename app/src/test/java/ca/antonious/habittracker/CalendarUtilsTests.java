package ca.antonious.habittracker;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import ca.antonious.habittracker.utils.CalendarUtils;

/**
 * Created by George on 2016-09-12.
 */
public class CalendarUtilsTests {
    @Test
    public void test_getDaysBetween_ifNoDaysOfTheWeekSelected_thenReturnsZero() {
        Calendar start = Calendar.getInstance();
        start.set(2016, 1, 1);

        Calendar end = Calendar.getInstance();
        end.set(2016, 1, 10);

        int expectedDaysBetween = 0;
        int actualDaysBetween = CalendarUtils.getDaysBetween(start, end, new ArrayList<Integer>());

        Assert.assertEquals(expectedDaysBetween, actualDaysBetween);
    }

    @Test
    public void test_getDaysBetween_ifAllDaysAreSelected_thenReturnsTotalDaysBetween() {
        Calendar start = Calendar.getInstance();
        start.set(2016, Calendar.JANUARY, 1);

        Calendar end = Calendar.getInstance();
        end.set(2016, Calendar.JANUARY, 10);

        int expectedDaysBetween = 9;
        int actualDaysBetween = CalendarUtils.getDaysBetween(start, end);

        Assert.assertEquals(expectedDaysBetween, actualDaysBetween);
    }

    @Test
    public void test_getDaysBetween_ifMondayIsSelectedAndOneMondayIsInBetween_thenReturnsOne() {
        Calendar start = Calendar.getInstance();
        start.set(2016, Calendar.JANUARY, 1);

        Calendar end = Calendar.getInstance();
        end.set(2016, Calendar.JANUARY, 10);

        int expectedDaysBetween = 1;
        int actualDaysBetween = CalendarUtils.getDaysBetween(start, end, Arrays.asList(Calendar.MONDAY));

        Assert.assertEquals(expectedDaysBetween, actualDaysBetween);
    }

    @Test
    public void test_getDaysBetween_ifMondayIsSelectedAndOnlyOnStart_thenReturnsOne() {
        Calendar start = Calendar.getInstance();
        start.set(2016, Calendar.JANUARY, 4);

        Calendar end = Calendar.getInstance();
        end.set(2016, Calendar.JANUARY, 7);

        int expectedDaysBetween = 1;
        int actualDaysBetween = CalendarUtils.getDaysBetween(start, end, Arrays.asList(Calendar.MONDAY));

        Assert.assertEquals(expectedDaysBetween, actualDaysBetween);
    }

    @Test
    public void test_getDaysBetween_ifMondayIsSelectedAndOnlyOnEnd_thenReturnsZero() {
        Calendar start = Calendar.getInstance();
        start.set(2016, Calendar.JANUARY, 1);

        Calendar end = Calendar.getInstance();
        end.set(2016, Calendar.JANUARY, 4);

        int expectedDaysBetween = 0;
        int actualDaysBetween = CalendarUtils.getDaysBetween(start, end, Arrays.asList(Calendar.MONDAY));

        Assert.assertEquals(expectedDaysBetween, actualDaysBetween);
    }



}
