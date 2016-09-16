package ca.antonious.habittracker.interactions;

import java.util.Date;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;
import ca.antonious.habittracker.time.IClock;

/**
 * Created by George on 2016-09-03.
 */
public class CompleteHabit {
    private IClock clock;
    private IHabitRepository habitRepository;

    public CompleteHabit(IHabitRepository habitRepository, IClock clock) {
        this.habitRepository = habitRepository;
        this.clock = clock;
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
        return new HabitCompletion(clock.getCurrentDate());
    }
}
