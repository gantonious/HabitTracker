package ca.antonious.habittracker.interactions;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.time.IClock;

/**
 * Created by George on 2016-09-04.
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
