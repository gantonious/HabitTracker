package ca.antonious.habittracker.habitlist;

import java.util.List;

import ca.antonious.habittracker.interactions.HabitInteractionsFactory;
import ca.antonious.habittracker.IController;
import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObserver;

/**
 * Created by George on 2016-09-03.
 */
public class HabitListController implements IController<IHabitListView> {
    private IHabitListView habitListView;
    private IHabitRepository habitRepository;
    private HabitInteractionsFactory habitInteractionsFactory;

    public HabitListController(IHabitRepository habitRepository, HabitInteractionsFactory habitInteractionsFactory) {
        this.habitRepository = habitRepository;
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void markHabitAsCompleted(String habitId) {
        habitInteractionsFactory.completeHabit().complete(habitId);
    }

    public void attachView(IHabitListView habitListView) {
        this.habitListView = habitListView;
        habitRepository.addObserver(habitListObserver);
    }

    public void detachView() {
        habitRepository.removeObserver(habitListObserver);
        this.habitListView = null;
    }

    private IObserver<List<Habit>> habitListObserver = new IObserver<List<Habit>>() {
        @Override
        public void onNext(List<Habit> next) {
            dispatchDisplayHabits(next);
        }
    };


    private void dispatchDisplayHabits(List<Habit> habits) {
        if (habitListView != null) {
            habitListView.displayHabits(habits);
        }
    }
}
