package ca.antonious.habittracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObservable;
import ca.antonious.habittracker.observable.IObserver;

/**
 * Created by George on 2016-09-02.
 */
public class HabitRepository implements IHabitRepository {
    private IHabitService habitService;
    private Map<String, Habit> habits;

    private Set<IObserver<List<Habit>>> observers = new HashSet<>();

    public HabitRepository(IHabitService habitService) {
        this.habitService = habitService;
        this.habits = new HashMap<>();
    }

    public List<Habit> getHabits() {
        if (habits.isEmpty()) {
            for (Habit habit: habitService.getHabits()) {
                habits.put(habit.getId(), habit);
            }
        }
        return new ArrayList<>(habits.values());
    }

    public Habit getHabit(String id) {
        getHabits();

        return habits.get(id);
    }

    public void addHabit(Habit habit) {
        getHabits();

        habitService.addHabit(habit);
        habits.put(habit.getId(), habit);
        notifyChange();
    }

    public void updateHabit(Habit habit) {
        getHabits();

        habitService.updateHabit(habit);
        habits.put(habit.getId(), habit);
        notifyChange();
    }

    public void removeHabit(String id) {
        getHabits();

        habitService.removeHabit(id);
        habits.remove(id);
        notifyChange();
    }

    private void notifyChange() {
        for (IObserver<List<Habit>> observer: observers) {
            observer.onNext(getHabits());
        }
    }

    @Override
    public void addObserver(IObserver<List<Habit>> observer) {
        observers.add(observer);
        observer.onNext(getHabits());
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
