package ca.antonious.habittracker.todayshabitlist;

import java.util.List;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-30.
 *
 * ITodaysHabitsView is to be implemented by a GUI class that
 * renders a list of habits that should be completed today. It exposes a method to
 * notify the view when the habit list has been updated.
 */

public interface ITodaysHabitsView {
    void displayTodaysHabits(List<Habit> habits);
}
