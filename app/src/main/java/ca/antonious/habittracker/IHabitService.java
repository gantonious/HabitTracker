package ca.antonious.habittracker;

import java.util.List;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-01.
 */
public interface IHabitService {
    List<Habit> getHabits();
    void addHabit(Habit habit);
    void updateHabit(Habit habit);
    void removeHabit(String id);
}
