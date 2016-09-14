package ca.antonious.habittracker.interactions;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;

/**
 * Created by George on 2016-09-04.
 */
public class RemoveCompletion {
    private IHabitRepository habitRepository;

    public RemoveCompletion(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void remove(String habitId, String completionId) {
        Habit habit = habitRepository.getHabit(habitId);

        if (habit != null) {
            removeCompletionFromHabit(habit, completionId);
        }
    }

    private void removeCompletionFromHabit(Habit habit, String completionId) {
        HabitCompletion habitCompletion = habit.getCompletion(completionId);

        if (habitCompletion != null) {
            habit.getCompletions().remove(habitCompletion);
            habitRepository.updateHabit(habit);
        }
    }
}
