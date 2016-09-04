package ca.antonious.habittracker;

/**
 * Created by George on 2016-09-04.
 */
public class DeleteHabit {
    private IHabitRepository habitRepository;

    public DeleteHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void delete(String habitId) {
        habitRepository.removeHabit(habitId);
    }
}
