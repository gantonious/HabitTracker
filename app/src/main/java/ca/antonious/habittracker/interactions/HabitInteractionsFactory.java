package ca.antonious.habittracker.interactions;

import ca.antonious.habittracker.habitstorage.IHabitRepository;

/**
 * Created by George on 2016-09-04.
 */
public class HabitInteractionsFactory {
    private IHabitRepository habitRepository;

    public HabitInteractionsFactory(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public RemoveCompletion removeCompletion() {
        return new RemoveCompletion(habitRepository);
    }

    public CompleteHabit completeHabit() {
        return new CompleteHabit(habitRepository);
    }

    public AddHabit addHabit() {
        return new AddHabit(habitRepository);
    }

    public DeleteHabit deleteHabit() {
        return new DeleteHabit(habitRepository);
    }
}
