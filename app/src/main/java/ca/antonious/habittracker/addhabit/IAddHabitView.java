package ca.antonious.habittracker.addhabit;


/**
 * Created by George on 2016-09-04.
 *
 * IAddHabitView is an interface to be implemented by a GUI
 * class that is add habits, it exposes a method to notify the
 * vie when a habit is successfully added
 */
public interface IAddHabitView {
    void onHabitAdded();
}
