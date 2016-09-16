package ca.antonious.habittracker.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.interactions.CompleteHabit;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;
import ca.antonious.habittracker.time.IClock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class CompleteHabitTests {
    private IClock clock;
    private IHabitRepository habitRepository;
    private CompleteHabit completeHabit;

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

        clock = mock(IClock.class);
        habitRepository = mock(IHabitRepository.class);
        completeHabit = new CompleteHabit(habitRepository, clock);
    }

    @Test
    public void test_complete_ifHabitDoesNotExist_thenNoRepositoryUpdatesOccurs() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(null);

        completeHabit.complete("Habit-ID");

        verify(habitRepository, never()).updateHabit(any(Habit.class));
    }

    @Test
    public void test_complete_ifHabitExists_thenUpdateIsCalledWithCompletionAdded() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(habitWithoutCompletion);
        when(clock.getCurrentDate()).thenReturn(new Date(1));

        completeHabit.complete("Habit-ID");

        ArgumentCaptor<Habit> habitCaptor = ArgumentCaptor.forClass(Habit.class);
        verify(habitRepository).updateHabit(habitCaptor.capture());

        Habit habit = habitCaptor.getValue();

        HabitCompletion expectedCompletion = habitWithCompletion.getCompletions().get(0);
        HabitCompletion actualCompletion = habit.getCompletions().get(0);

        assertEquals(habit.getName(), habitWithCompletion.getName());
        assertEquals(habit.getStartDate(), habitWithCompletion.getStartDate());

        assertEquals(expectedCompletion.getCompletionTime(), actualCompletion.getCompletionTime());
    }
}
