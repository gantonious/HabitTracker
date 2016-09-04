package ca.antonious.habittracker;

import android.app.Application;

import ca.antonious.habittracker.fileacess.AndroidFileHandler;
import ca.antonious.habittracker.fileacess.IFileHandler;
import ca.antonious.habittracker.habitdetails.HabitDetailsController;
import ca.antonious.habittracker.habitlist.HabitListController;

/**
 * Created by George on 2016-09-02.
 */
public class HabitTrackerApplication extends Application {
    private IHabitRepository habitRepository;
    private HabitInteractionsFactory habitInteractionsFactory;

    public IHabitRepository getHabitRepository() {
        ensureHabitRepository();
        return habitRepository;
    }

    private void ensureHabitRepository() {
        if (habitRepository == null) {
            IFileHandler fileHandler = new AndroidFileHandler(this);
            IHabitService habitService = new HabitService(fileHandler);
            habitRepository = new HabitRepository(habitService);
        }
    }

    public HabitInteractionsFactory getHabitInteractionsFactory() {
        ensureHabitInteractionsFactory();
        return habitInteractionsFactory;
    }

    private void ensureHabitInteractionsFactory() {
        if (habitInteractionsFactory == null) {
            habitInteractionsFactory = new HabitInteractionsFactory(getHabitRepository());
        }
    }

    public HabitListController getHabitListController() {
        return new HabitListController(getHabitRepository(), getHabitInteractionsFactory());
    }

    public HabitDetailsController getHabitDetailsController(String habitId) {
        return new HabitDetailsController(getHabitRepository(), getHabitInteractionsFactory(), habitId);
    }
}
