package ca.antonious.habittracker.habitlist;

import java.util.List;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-03.
 *
 * IHabitListView is to be implemented by a GUI class that
 * renders a list of habits. It exposes a method to notify the view
 * when the habit list has been updated.
 */
public interface IHabitListView {
    void displayHabits(List<Habit> habit);
}
