package ca.antonious.habittracker;

import android.app.Application;

/**
 * Created by George on 2016-09-02.
 */
public class HabitTrackerApplication extends Application {
    private HabitRepository habitRepository;

    public HabitRepository getHabitRepository() {
        ensureHabitRepository();
        return habitRepository;s
    }

    private void ensureHabitRepository() {
        if (habitRepository == null) {
            FileHandler fileHandler = new AndroidFileHandler(this);
            IHabitService habitService = new HabitService(fileHandler);
            habitRepository = new HabitRepository(habitService);
        }
    }
}
