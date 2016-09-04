package ca.antonious.habittracker.habitlist;

import java.util.List;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-03.
 */
public interface IHabitListView {
    void displayHabits(List<Habit> habit);
}
