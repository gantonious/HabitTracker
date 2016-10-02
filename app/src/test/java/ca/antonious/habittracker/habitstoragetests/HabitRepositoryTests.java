package ca.antonious.habittracker.habitstoragetests;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ca.antonious.habittracker.habitstorage.HabitRepository;
import ca.antonious.habittracker.habitstorage.IHabitService;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObserver;
import ca.antonious.habittracker.observable.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class HabitRepositoryTests {
    private IHabitService habitService;
    private HabitRepository habitRepository;

    private Habit habit1;
    private Habit habit2;
    private Habit habit3;

    private Habit updatedHabit2;

    @Before
    public void set_up() {
        habit1 = new Habit();
        habit1.setId("AwesomeHabit-Id");
        habit1.setName("AwesomeHabit");
        habit1.setStartDate(new Date(1));
        habit1.setDaysToComplete(Arrays.asList(Calendar.SUNDAY));

        habit2 = new Habit();
        habit2.setId("KindOfAwesomeHabit-Id");
        habit2.setName("KindOfAwesomeHabit");
        habit2.setStartDate(new Date(2));
        habit2.setDaysToComplete(Arrays.asList(Calendar.MONDAY, Calendar.WEDNESDAY));

        updatedHabit2 = new Habit();
        updatedHabit2.setId("KindOfAwesomeHabit-Id");
        updatedHabit2.setName("UpdatedKindOfAwesomeHabit");
        updatedHabit2.setStartDate(new Date(2));
        updatedHabit2.setDaysToComplete(Arrays.asList(Calendar.MONDAY, Calendar.WEDNESDAY));

        habit3 = new Habit();
        habit3.setId("CoolHabit-Id");
        habit3.setName("CoolHabit");
        habit3.setStartDate(new Date(3));
        habit3.setDaysToComplete(Arrays.asList(Calendar.SATURDAY, Calendar.WEDNESDAY));

        habitService = mock(IHabitService.class);
        habitRepository = new HabitRepository(habitService);
    }

    @Test
    public void test_getHabit_ifHabitDoesNotExist_thenReturnsNull() {
        when(habitService.getHabits()).thenReturn(new ArrayList<Habit>());

        Habit expectedHabit = null;
        Habit actualHabit = habitRepository.getHabit(habit1.getId());

        assertEquals(expectedHabit, actualHabit);
    }

    @Test
    public void test_getHabit_ifHabitExists_thenReturnsHabit() {
        when(habitService.getHabits()).thenReturn(Arrays.asList(habit1, habit2));

        Habit expectedHabit = habit1;
        Habit actualHabit = habitRepository.getHabit(habit1.getId());

        assertEquals(expectedHabit, actualHabit);
    }

    @Test
    public void test_getHabits_ifHabitsAreEmpty_thenReturnsEmptyList() {
        when(habitService.getHabits()).thenReturn(new ArrayList<Habit>());

        List<Habit> expectedHabits = new ArrayList<>();
        List<Habit> actualHabits = habitRepository.getHabits();

        assertEquals(expectedHabits, actualHabits);
    }

    @Test
    public void test_getHabits_ifHabitsExist_thenReturnSortedHabits() {
        when(habitService.getHabits()).thenReturn(Arrays.asList(habit2, habit1, habit3));

        List<Habit> expectedHabits = Arrays.asList(habit3, habit2, habit1);
        List<Habit> actualHabits = habitRepository.getHabits();

        assertEquals(expectedHabits, actualHabits);
    }

    @Test
    public void test_removeHabit_callsHabitServiceRemoveHabit() {
        when(habitService.getHabits()).thenReturn(Arrays.asList(habit1, habit2, habit3));
        habitRepository.removeHabit(habit1.getId());

        verify(habitService).removeHabit(habit1.getId());
    }

    @Test
    public void test_removeHabit_emitsUpdatedListToObservers() {
        TestObserver<List<Habit>> habitsObserver = new TestObserver<>();
        habitRepository.addObserver(habitsObserver);

        when(habitService.getHabits()).thenReturn(Arrays.asList(habit1, habit2, habit3));
        habitRepository.removeHabit(habit1.getId());

        habitsObserver.assertItemEmitted(Arrays.asList(habit3, habit2));
    }

    @Test
    public void test_addHabit_callsHabitServiceAddHabit() {
        when(habitService.getHabits()).thenReturn(Arrays.asList(habit1, habit2));
        habitRepository.addHabit(habit3);

        verify(habitService).addHabit(habit3);
    }

    @Test
    public void test_addHabit_emitsUpdatedListToObservers() {
        TestObserver<List<Habit>> habitsObserver = new TestObserver<>();
        habitRepository.addObserver(habitsObserver);

        when(habitService.getHabits()).thenReturn(Arrays.asList(habit1, habit2));
        habitRepository.addHabit(habit3);

        habitsObserver.assertItemEmitted(Arrays.asList(habit3, habit2, habit1));
    }

    @Test
    public void test_updateHabit_callsHabitServiceUpdateHabit() {
        when(habitService.getHabits()).thenReturn(Arrays.asList(habit1, habit2));
        habitRepository.updateHabit(updatedHabit2);

        verify(habitService).updateHabit(updatedHabit2);
    }

    @Test
    public void test_updateHabit_emitsUpdatedListToObservers() {
        TestObserver<List<Habit>> habitsObserver = new TestObserver<>();
        habitRepository.addObserver(habitsObserver);

        when(habitService.getHabits()).thenReturn(Arrays.asList(habit1, habit2));
        habitRepository.updateHabit(updatedHabit2);

        habitsObserver.assertItemEmitted(Arrays.asList(updatedHabit2, habit1));
    }
}
