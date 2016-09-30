package ca.antonious.habittracker.habitstorage;

import java.util.List;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-01.
 *
 * IHabitService provides an interface exposing CRUD methods
 * for habit persistence. This interface is important because it abstracts
 * away exactly how the habits are loaded/saved, we don't care if they're
 * loaded from disk for from the web (although this interface is synchronous, so
 * it would need to be updated to handle the asynchronous nature of the web). This interface
 * also protects us from model changes, implementers of this interface can determine how
 * to construct habits from whatever raw data they find.
 *
 */
public interface IHabitService {
    List<Habit> getHabits();
    void addHabit(Habit habit);
    void updateHabit(Habit habit);
    void removeHabit(String id);
}
