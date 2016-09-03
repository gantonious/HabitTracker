package ca.antonious.habittracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObservable;
import ca.antonious.habittracker.observable.IObserver;

/**
 * Created by George on 2016-09-02.
 */
public class HabitRepository implements IObservable<List<Habit>> {
    private IHabitService habitService;
    private List<Habit> habits;

    private Set<IObserver<List<Habit>>> observers = new HashSet<>();

    public HabitRepository(IHabitService habitService) {
        this.habitService = habitService;
        this.habits = new ArrayList<>();
    }

    public List<Habit> getHabits() {
        if (habits.isEmpty()) {
            habits.addAll(habitService.getHabits());
        }
        return habits;
    }

    public void addHabit(Habit habit) {
        // get habits to make sure state is good
        getHabits();

        habitService.addHabit(habit);
        habits.add(habit);
        notifyChange();
    }

    public void updateHabit(Habit habit) {
        // get habits to make sure state is good
        
        habitService.updateHabit(habit);
        notifyChange();
    }

    private void notifyChange() {
        for (IObserver<List<Habit>> observer: observers) {
            observer.onNext(habits);
        }
    }

    @Override
    public void addObserver(IObserver<List<Habit>> observer) {
        observers.add(observer);
        observer.onNext(habits);
    }

    @Override
    public void removeObserver(IObserver<List<Habit>> observer) {
        observers.remove(observer);
    }

    @Override
    public void removeAllObservers() {
        observers.clear();
    }
}
