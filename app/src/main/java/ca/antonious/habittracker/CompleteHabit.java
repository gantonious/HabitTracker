package ca.antonious.habittracker;

import java.util.Date;

import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;

/**
 * Created by George on 2016-09-03.
 */
public class CompleteHabit {
    private IHabitRepository habitRepository;

    public CompleteHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void complete(String id) {
        Habit habit = habitRepository.getHabit(id);

        if (habit != null) {
            habitRepository.updateHabit(getHabitUpdatedWithCompletion(habit));
        }
    }

    private Habit getHabitUpdatedWithCompletion(Habit habit) {
        habit.getCompletions().add(generateCompletion());
        return habit;
    }

    private HabitCompletion generateCompletion() {
        return new HabitCompletion(new Date());
    }
}
