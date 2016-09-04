package ca.antonious.habittracker.habitlist;

import java.util.List;

import ca.antonious.habittracker.CompleteHabit;
import ca.antonious.habittracker.IController;
import ca.antonious.habittracker.IHabitRepository;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObserver;

/**
 * Created by George on 2016-09-03.
 */
public class HabitListController implements IController<IHabitListView> {
    private IHabitListView habitListView;
    private CompleteHabit completeHabit;

    private IHabitRepository habitRepository;
    private IObserver<List<Habit>> habitListObserver;

    public HabitListController(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
        this.completeHabit = new CompleteHabit(habitRepository);
        this.habitListObserver = generateHabitListObserver();
    }

    public void markHabitAsCompleted(String habitId) {
        completeHabit.complete(habitId);
    }

    public void attachView(IHabitListView habitListView) {
        this.habitListView = habitListView;
        habitRepository.addObserver(habitListObserver);
    }

    public void detachView() {
        habitRepository.removeObserver(habitListObserver);
        this.habitListView = null;
    }

    private IObserver<List<Habit>> generateHabitListObserver() {
        return new IObserver<List<Habit>>() {
            @Override
            public void onNext(List<Habit> next) {
                dispatchDisplayHabits(next);
            }
        };
    }

    private void dispatchDisplayHabits(List<Habit> habits) {
        if (habitListView != null) {
            habitListView.displayHabits(habits);
        }
    }
}
