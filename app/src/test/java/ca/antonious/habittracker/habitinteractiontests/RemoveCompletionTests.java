package ca.antonious.habittracker.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.interactions.RemoveCompletion;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;

import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class RemoveCompletionTests {
    private IHabitRepository habitRepository;
    private RemoveCompletion removeCompletion;

    private Habit habitWithoutCompletion;
    private Habit habitWithCompletion;

    @Before
    public void set_up() {
        habitWithoutCompletion = new Habit();
        habitWithoutCompletion.setId("Habit-ID");
        habitWithoutCompletion.setName("AwesomeHabit");
        habitWithoutCompletion.setStartDate(new Date(1));
        habitWithoutCompletion.setDaysToComplete(Arrays.asList(Calendar.SUNDAY));

        HabitCompletion habitCompletion = new HabitCompletion(new Date(1));
        habitCompletion.setId("Completion-ID");

        habitWithCompletion = new Habit();
        habitWithCompletion.setId("Habit-ID");
        habitWithCompletion.setName("AwesomeHabit");
        habitWithCompletion.setStartDate(new Date(1));
        habitWithCompletion.setDaysToComplete(Arrays.asList(Calendar.SUNDAY));
        habitWithCompletion.setCompletions(Arrays.asList(habitCompletion));

        habitRepository = mock(IHabitRepository.class);
        removeCompletion = new RemoveCompletion(habitRepository);
    }

    @Test
    public void test_remove_ifHabitDoesNotExist_thenNoRepositoryUpdatesOccur() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(null);
        removeCompletion.remove("Habit-ID", "Completion-ID");

        verify(habitRepository, never()).updateHabit(Mockito.any(Habit.class));
    }

    @Test
    public void test_remove_ifHabitExistsButCompletionDoesNot_thenNoRepositoryUpdatesOccur() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(habitWithoutCompletion);
        removeCompletion.remove("Habit-ID", "Completion-ID");

        verify(habitRepository, never()).updateHabit(Mockito.any(Habit.class));
    }

    @Test
    public void test_remove_ifHabitAndCompletionExists_thenHabitIsUpdated() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(habitWithCompletion);
        removeCompletion.remove("Habit-ID", "Completion-ID");

        verify(habitRepository).updateHabit(habitWithoutCompletion);
    }

}
