package ca.antonious.habittracker.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.interactions.DeleteHabit;

import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class DeleteHabitTests {
    private IHabitRepository habitRepository;
    private DeleteHabit deleteHabit;

    @Before
    public void set_up() {
        habitRepository = mock(IHabitRepository.class);
        deleteHabit = new DeleteHabit(habitRepository);
    }

    @Test
    public void test_delete_callsRemoveHabitInRepository() {
        String habitIdToDelete = "habitId";
        deleteHabit.delete(habitIdToDelete);

        verify(habitRepository).removeHabit(habitIdToDelete);
    }
}
