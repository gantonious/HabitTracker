package ca.antonious.habittracker;

import android.app.Application;

import ca.antonious.habittracker.habitlist.HabitListController;

/**
 * Created by George on 2016-09-02.
 */
public class HabitTrackerApplication extends Application {
    private IHabitRepository habitRepository;

    public IHabitRepository getHabitRepository() {
        ensureHabitRepository();
        return habitRepository;
    }

    private void ensureHabitRepository() {
        if (habitRepository == null) {
            FileHandler fileHandler = new AndroidFileHandler(this);
            IHabitService habitService = new HabitService(fileHandler);
            habitRepository = new HabitRepository(habitService);
        }
    }

    public HabitListController getHabitListController() {
        return new HabitListController(getHabitRepository());
    }
}
