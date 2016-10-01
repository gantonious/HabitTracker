package ca.antonious.habittracker.todayshabitlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.antonious.habittracker.IController;
import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.interactions.HabitInteractionsFactory;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObserver;

/**
 * Created by George on 2016-09-30.
 *
 * TodaysHabitsController is an implementation of IController that controls
 * an ITodaysHabitsView. It exposes a method to complete a habit, and notifies the view
 * when the habit list has changed
 */

public class TodaysHabitsController implements IController<ITodaysHabitsView> {
    private ITodaysHabitsView todaysHabitsView;
    private IHabitRepository habitRepository;
    private HabitInteractionsFactory habitInteractionsFactory;

    public TodaysHabitsController(IHabitRepository habitRepository, HabitInteractionsFactory habitInteractionsFactory) {
        this.habitRepository = habitRepository;
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void markHabitAsCompleted(String habitId) {
        habitInteractionsFactory.completeHabit().complete(habitId);
    }

    private IObserver<List<Habit>> habitListObserver = new IObserver<List<Habit>>() {
        @Override
        public void onNext(List<Habit> next) {
            dispatchDisplayTodaysHabits(getTodaysHabits(next));
        }
    };

    private List<Habit> getTodaysHabits(List<Habit> habits) {
        List<Habit> todaysHabits = new ArrayList<>();

        for (Habit habit: habits) {
            if (habit.shouldBeCompletedOnDay(new Date())) {
                todaysHabits.add(habit);
            }
        }

        return todaysHabits;
    }

    private void dispatchDisplayTodaysHabits(List<Habit> habits) {
        if (todaysHabitsView != null) {
            todaysHabitsView.displayTodaysHabits(habits);
        }
    }

    @Override
    public void attachView(ITodaysHabitsView view) {
        this.todaysHabitsView = view;
        this.habitRepository.addObserver(habitListObserver);
    }

    @Override
    public void detachView() {
        this.habitRepository.addObserver(habitListObserver);
        this.todaysHabitsView = null;
    }
}
