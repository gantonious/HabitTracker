package ca.antonious.habittracker.habittests;

import org.junit.Test;

import java.util.Date;

import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;

import static org.junit.Assert.*;

/**
 * Created by George on 2016-09-13.
 */
public class HabitGetCompletionsDescriptionTests {
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
}
