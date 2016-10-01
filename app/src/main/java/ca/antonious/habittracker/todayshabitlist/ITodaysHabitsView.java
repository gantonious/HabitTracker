package ca.antonious.habittracker.todayshabitlist;

import java.util.List;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-30.
 */

public interface ITodaysHabitsView {
    void displayTodaysHabits(List<Habit> habits);
}
