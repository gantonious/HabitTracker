package ca.antonious.habittracker.habittests;

import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;
import ca.antonious.habittracker.utils.DateUtils;

import static org.junit.Assert.*;

/**
 * Created by George on 2016-09-13.
 */
public class HabitTests {
    @Test
    public void test_getCompletionsDescription_ifZeroCompletions_thenReturnsNeverCompleted() {
        Habit habit = new Habit();

        String expectedResult = "Never completed";
        String actualResult = habit.getCompletionsDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getCompletionsDescription_ifCompletedOnce_thenReturnsCompletedOnce() {
        Habit habit = new Habit();
        habit.getCompletions().add(new HabitCompletion(new Date()));

        String expectedResult = "Completed once";
        String actualResult = habit.getCompletionsDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getCompletionsDescription_ifCompletedMoreThanOnce_thenReturnsCompletedNTimes() {
        Habit habit = new Habit();
        habit.getCompletions().add(new HabitCompletion(new Date()));
        habit.getCompletions().add(new HabitCompletion(new Date()));

        String expectedResult = "Completed 2 times";
        String actualResult = habit.getCompletionsDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getStartingDateDescription_returnsPrettyString() {
        Habit habit = new Habit();
        habit.setStartDate(new Date(1));

        String expectedResult = "Started on Dec 31, 1969";
        String actualResult = habit.getStartingDateDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_hadBeenCompletedOnDay_ifHasBeenCompletedOnDaySpecified_thenReturnsTrue() {
        HabitCompletion habitCompletion = new HabitCompletion(DateUtils.createDate(2016, 1, 1));

        Habit habit = new Habit();
        habit.addCompletion(habitCompletion);

        boolean expectedResult = true;
        boolean actualResult = habit.hasBeenCompletedOnDay(DateUtils.createDate(2016, 1, 1));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_hadBeenCompletedOnDay_ifHasNotBeenCompletedOnDaySpecified_thenReturnsFalse() {
        Habit habit = new Habit();

        boolean expectedResult = false;
        boolean actualResult = habit.hasBeenCompletedOnDay(new Date());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_shouldBeCompleted_ifDayOfTheWeekNotInDaysToComplete_thenReturnsFalse() {
        Habit habit = new Habit();
        habit.setDaysToComplete(Arrays.asList(Calendar.MONDAY, Calendar.THURSDAY));

        Date dayOnTuesday = DateUtils.createDate(2016, 10, 4);

        boolean expectedResult = false;
        boolean actualResult = habit.shouldBeCompletedOnDay(dayOnTuesday);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_shouldBeCompleted_ifDayOfTheWeekIsInDaysToComplete_thenReturnsTrue() {
        Habit habit = new Habit();
        habit.setDaysToComplete(Arrays.asList(Calendar.MONDAY, Calendar.THURSDAY));

        Date dayOnTuesday = DateUtils.createDate(2016, 10, 3);

        boolean expectedResult = true;
        boolean actualResult = habit.shouldBeCompletedOnDay(dayOnTuesday);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getMissedDaysDescription_ifNoDaysMissed_thenReturnsNeverMissed() {
        Date lowerBound = DateUtils.createDate(2016, 9, 18);
        Date upperBound = DateUtils.createDate(2016, 9, 25);

        HabitCompletion sundayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 18));
        HabitCompletion mondayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 19));
        HabitCompletion tuesdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 20));
        HabitCompletion wednesdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 21));
        HabitCompletion thursdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 22));
        HabitCompletion fridayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 23));
        HabitCompletion saturdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 24));

        Habit habit = new Habit();
        habit.setStartDate(lowerBound);
        habit.setDaysToComplete(Arrays.asList(Calendar.SUNDAY,
                                              Calendar.MONDAY,
                                              Calendar.TUESDAY,
                                              Calendar.WEDNESDAY,
                                              Calendar.THURSDAY,
                                              Calendar.FRIDAY,
                                              Calendar.SATURDAY));

        habit.setCompletions(Arrays.asList(sundayCompletion,
                                           mondayCompletion,
                                           tuesdayCompletion,
                                           wednesdayCompletion,
                                           thursdayCompletion,
                                           fridayCompletion,
                                           saturdayCompletion));

        String expectedResult = "Never missed";
        String actualResult = habit.getMissedDaysDescription(upperBound);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getMissedDaysDescription_ifRequestedForSameDayAsCreationDate_thenReturnsNeverMissed() {
        Date lowerBound = DateUtils.createDate(2016, 9, 18);
        Date upperBound = DateUtils.createDate(2016, 9, 18, 17, 32, 7);

        Habit habit = new Habit();
        habit.setStartDate(lowerBound);
        habit.setDaysToComplete(Arrays.asList(Calendar.SUNDAY,
                                              Calendar.MONDAY,
                                              Calendar.TUESDAY,
                                              Calendar.WEDNESDAY,
                                              Calendar.THURSDAY,
                                              Calendar.FRIDAY,
                                              Calendar.SATURDAY));

        String expectedResult = "Never missed";
        String actualResult = habit.getMissedDaysDescription(upperBound);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getMissedDaysDescription_ifCompletedOnTheStartingDay_whenCalledTheNextDay_thenReturnsNeverMissed() {
        Date lowerBound = DateUtils.createDate(2016, 9, 18, 18, 29, 0);
        Date upperBound = DateUtils.createDate(2016, 9, 19, 17, 32, 7);

        Habit habit = new Habit();
        habit.setStartDate(lowerBound);
        habit.setDaysToComplete(Arrays.asList(Calendar.SUNDAY,
                                              Calendar.MONDAY,
                                              Calendar.TUESDAY,
                                              Calendar.WEDNESDAY,
                                              Calendar.THURSDAY,
                                              Calendar.FRIDAY,
                                              Calendar.SATURDAY));

        habit.setCompletions(Arrays.asList(new HabitCompletion(DateUtils.createDate(2016, 9 ,18, 19, 0, 0))));

        String expectedResult = "Never missed";
        String actualResult = habit.getMissedDaysDescription(upperBound);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getMissedDaysDescription_ifOneDayMissed_thenReturnsMissedOnce() {
        Date lowerBound = DateUtils.createDate(2016, 9, 18);
        Date upperBound = DateUtils.createDate(2016, 9, 25);

        HabitCompletion sundayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 18));
        HabitCompletion mondayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 19));
        HabitCompletion wednesdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 21));
        HabitCompletion thursdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 22));
        HabitCompletion fridayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 23));
        HabitCompletion saturdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 24));

        Habit habit = new Habit();
        habit.setStartDate(lowerBound);
        habit.setDaysToComplete(Arrays.asList(Calendar.SUNDAY,
                                              Calendar.MONDAY,
                                              Calendar.TUESDAY,
                                              Calendar.WEDNESDAY,
                                              Calendar.THURSDAY,
                                              Calendar.FRIDAY,
                                              Calendar.SATURDAY));

        habit.setCompletions(Arrays.asList(sundayCompletion,
                                           mondayCompletion,
                                           wednesdayCompletion,
                                           thursdayCompletion,
                                           fridayCompletion,
                                           saturdayCompletion));

        String expectedResult = "Missed once";
        String actualResult = habit.getMissedDaysDescription(upperBound);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getMissedDaysDescription_ifMissedMoreThanOnce_thenReturnsMissedNTimes() {
        Date lowerBound = DateUtils.createDate(2016, 9, 18);
        Date upperBound = DateUtils.createDate(2016, 9, 25);

        HabitCompletion mondayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 19));
        HabitCompletion thursdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 22));
        HabitCompletion fridayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 23));
        HabitCompletion saturdayCompletion = new HabitCompletion(DateUtils.createDate(2016, 9, 24));

        Habit habit = new Habit();
        habit.setStartDate(lowerBound);
        habit.setDaysToComplete(Arrays.asList(Calendar.SUNDAY,
                                              Calendar.MONDAY,
                                              Calendar.TUESDAY,
                                              Calendar.WEDNESDAY,
                                              Calendar.THURSDAY,
                                              Calendar.FRIDAY,
                                              Calendar.SATURDAY));

        habit.setCompletions(Arrays.asList(mondayCompletion,
                                           thursdayCompletion,
                                           fridayCompletion,
                                           saturdayCompletion));

        String expectedResult = "Missed 3 times";
        String actualResult = habit.getMissedDaysDescription(upperBound);

        assertEquals(expectedResult, actualResult);
    }
}
