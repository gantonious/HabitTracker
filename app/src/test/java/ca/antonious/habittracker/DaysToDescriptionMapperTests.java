package ca.antonious.habittracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by George on 2016-09-13.
 */
public class DaysToDescriptionMapperTests {
    private DaysToDescriptionMapper daysToDescriptionMapper;

    @Before
    public void set_up() {
        daysToDescriptionMapper = new DaysToDescriptionMapper();
    }

    @Test
    public void test_map_ifOnlyWeekdays_returnsWeekdays() {
        List<Integer> daysOfTheWeekInput = Arrays.asList(Calendar.MONDAY,
                                                         Calendar.TUESDAY,
                                                         Calendar.WEDNESDAY,
                                                         Calendar.THURSDAY,
                                                         Calendar.FRIDAY);

        String expectedValue = "Weekdays";
        String actualValue = daysToDescriptionMapper.map(daysOfTheWeekInput);

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void test_map_ifOnlyWeekends_returnsWeekends() {
        List<Integer> daysOfTheWeekInput = Arrays.asList(Calendar.SUNDAY, Calendar.SATURDAY);

        String expectedValue = "Weekends";
        String actualValue = daysToDescriptionMapper.map(daysOfTheWeekInput);

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void test_map_ifEveryday_returnsEveryday() {
        List<Integer> daysOfTheWeekInput = Arrays.asList(Calendar.SUNDAY,
                                                         Calendar.MONDAY,
                                                         Calendar.TUESDAY,
                                                         Calendar.WEDNESDAY,
                                                         Calendar.THURSDAY,
                                                         Calendar.FRIDAY,
                                                         Calendar.SATURDAY);

        String expectedValue = "Everyday";
        String actualValue = daysToDescriptionMapper.map(daysOfTheWeekInput);

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void test_map_ifMondayAndWednesday_returnsCommaSeparatedMonWed() {
        List<Integer> daysOfTheWeekInput = Arrays.asList(Calendar.MONDAY, Calendar.WEDNESDAY);

        String expectedValue = "Mon, Wed";
        String actualValue = daysToDescriptionMapper.map(daysOfTheWeekInput);

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void test_map_ifWednesdayAndMonday_returnsCommaSeparatedMonWed() {
        List<Integer> daysOfTheWeekInput = Arrays.asList(Calendar.WEDNESDAY, Calendar.MONDAY);

        String expectedValue = "Mon, Wed";
        String actualValue = daysToDescriptionMapper.map(daysOfTheWeekInput);

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void test_map_ifNoDays_returnsNever() {
        String expectedValue = "Never";
        String actualValue = daysToDescriptionMapper.map(new ArrayList<Integer>());

        Assert.assertEquals(expectedValue, actualValue);
    }
}
