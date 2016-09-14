package ca.antonious.habittracker.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.interactions.RemoveCompletion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class RemoveCompletionTests {
    private IHabitRepository habitRepository;
    private RemoveCompletion removeCompletion;

    @Before
    public void set_up() {
        habitRepository = mock(IHabitRepository.class);
        removeCompletion = new RemoveCompletion(habitRepository);
    }

    @Test
    public void test_remove_ifHabitDoesNotExist_thenNoRepositoryUpdatesOccur() {
        fail();
    }

    @Test
    public void test_remove_ifHabitExistsButCompletionDoesNot_thenNoRepositoryUpdatesOccur() {
        fail();
    }

    @Test
    public void test_remove_ifHabitAndCompletionExists_thenHabitIsUpdated() {
        fail();
    }

}
