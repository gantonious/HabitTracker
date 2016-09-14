package ca.antonious.habittracker.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.interactions.AddHabit;
import ca.antonious.habittracker.models.Habit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class AddHabitTests {
    private IHabitRepository habitRepository;
    private AddHabit addHabit;

    @Before
    public void set_up() {
        habitRepository = mock(IHabitRepository.class);
        addHabit = new AddHabit(habitRepository);
    }

    @Test
    public void test_add_thenAddIsCalledInRepository() {
        String newHabitName = "New Habit";
        Date newHabitStartDate = new Date(0);
        List<Integer> newHabitDaysToComplete = Arrays.asList(Calendar.MONDAY, Calendar.THURSDAY);

        addHabit.add(newHabitName, newHabitStartDate, newHabitDaysToComplete);

        ArgumentCaptor<Habit> habitCaptor = ArgumentCaptor.forClass(Habit.class);
        verify(habitRepository).addHabit(habitCaptor.capture());

        Habit createdHabit = habitCaptor.getValue();

        assertEquals(newHabitName, createdHabit.getName());
        assertEquals(newHabitStartDate, createdHabit.getStartDate());
        assertEquals(newHabitDaysToComplete, createdHabit.getDaysToComplete());
        assertEquals(new ArrayList<>(), createdHabit.getCompletions());
    }

}
