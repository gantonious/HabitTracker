package ca.antonious.habittracker.habitdetails;

import ca.antonious.habittracker.CompleteHabit;
import ca.antonious.habittracker.DeleteHabit;
import ca.antonious.habittracker.IController;
import ca.antonious.habittracker.IHabitRepository;
import ca.antonious.habittracker.RemoveCompletion;
import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-04.
 */
public class HabitDetailsController implements IController<IHabitDetailsView> {
    private Habit habit;
    private IHabitDetailsView habitDetailsView;
    private IHabitRepository habitRepository;

    public HabitDetailsController(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void loadHabit(String habitId) {
        Habit habit = habitRepository.getHabit(habitId);

        if (habit != null) {
            this.habit = habit;
            disptachDisplayHabit(habit);
        }
    }

    public void deleteHabit() {
        if (habit != null) {
            new DeleteHabit(habitRepository).delete(habit.getId());
        }
    }

    public void markHabitAsComplete() {
        if (habit != null) {
            new CompleteHabit(habitRepository).complete(habit.getId());
        }
    }

    public void removeHabitCompletion(String completionId) {
        if (habit != null) {
            new RemoveCompletion(habitRepository).removeCompletionFromHabit(habit, completionId);
        }
    }

    @Override
    public void attachView(IHabitDetailsView view) {
        this.habitDetailsView = view;
    }

    @Override
    public void detachView() {
        this.habitDetailsView = null;
    }

    private void disptachDisplayHabit(Habit habit) {
        if (habitDetailsView != null) {
            habitDetailsView.displayHabit(habit);
        }
    }
}
