package ca.antonious.habittracker.interactions;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.time.IClock;

/**
 * Created by George on 2016-09-04.
 *
 * An interaction is a class that is concerned with handling a specific type of
 * model interaction (eg. completing a habit)
 *
 * This factory provides ways to construct all interactions and injects the required
 * dependencies into them so that users do not need to manually resolve dependencies.
 */
public class HabitInteractionsFactory {
    private IClock clock;
    private IHabitRepository habitRepository;

    public HabitInteractionsFactory(IHabitRepository habitRepository, IClock clock) {
        this.habitRepository = habitRepository;
        this.clock = clock;
    }

    public RemoveCompletion removeCompletion() {
        return new RemoveCompletion(habitRepository);
    }

    public CompleteHabit completeHabit() {
        return new CompleteHabit(habitRepository, clock);
    }

    public AddHabit addHabit() {
        return new AddHabit(habitRepository);
    }

    public DeleteHabit deleteHabit() {
        return new DeleteHabit(habitRepository);
    }
}
