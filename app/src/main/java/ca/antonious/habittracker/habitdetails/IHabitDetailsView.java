package ca.antonious.habittracker.habitdetails;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-04.
 *
 * IHabitDetailsView is to be implemented by a GUI class that
 * renders the details of a habit. It exposes a method to notify
 * the view when a habit has been updated.
 */
public interface IHabitDetailsView {
    void displayHabit(Habit habit);
}
