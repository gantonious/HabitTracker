package ca.antonious.habittracker.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.interactions.CompleteHabit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class CompleteHabitTests {
    private IHabitRepository habitRepository;
    private CompleteHabit completeHabit;

    @Before
    public void set_up() {
        habitRepository = mock(IHabitRepository.class);
        completeHabit = new CompleteHabit(habitRepository);
    }

    @Test
    public void test_complete_ifHabitDoesNotExist_thenNoRepositoryUpdatesOccurs() {
        fail();
    }

    @Test
    public void test_complete_ifHabitExists_thenUpdateIsCalledWithCompletionAdded() {
        fail();
    }
}
