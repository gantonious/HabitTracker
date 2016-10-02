package ca.antonious.habittracker;

import android.app.Application;

import ca.antonious.habittracker.addhabit.AddHabitController;
import ca.antonious.habittracker.fileacess.AndroidFileHandler;
import ca.antonious.habittracker.fileacess.IFileHandler;
import ca.antonious.habittracker.habitdetails.HabitDetailsController;
import ca.antonious.habittracker.habitlist.HabitListController;
import ca.antonious.habittracker.habitstorage.HabitRepository;
import ca.antonious.habittracker.habitstorage.LocalHabitService;
import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.habitstorage.IHabitService;
import ca.antonious.habittracker.interactions.HabitInteractionsFactory;
import ca.antonious.habittracker.time.Clock;
import ca.antonious.habittracker.todayshabitlist.TodaysHabitsController;

/**
 * Created by George on 2016-09-02.
 *
 * This class exposes dependencies to the rest of the system. It takes
 * care of the lifetime for each dependency, so all the consumers
 * need to care about is what dependency they need.
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
            IHabitService habitService = new LocalHabitService(fileHandler);
            habitRepository = new HabitRepository(habitService);
        }
    }

    public HabitInteractionsFactory getHabitInteractionsFactory() {
        ensureHabitInteractionsFactory();
        return habitInteractionsFactory;
    }

    private void ensureHabitInteractionsFactory() {
        if (habitInteractionsFactory == null) {
            habitInteractionsFactory = new HabitInteractionsFactory(getHabitRepository(), new Clock());
        }
    }

    public HabitListController getHabitListController() {
        return new HabitListController(getHabitRepository(), getHabitInteractionsFactory());
    }

    public TodaysHabitsController getTodaysHabitsController() {
        return new TodaysHabitsController(getHabitRepository(), getHabitInteractionsFactory());
    }

    public HabitDetailsController getHabitDetailsController(String habitId) {
        return new HabitDetailsController(getHabitRepository(), getHabitInteractionsFactory(), habitId);
    }

    public AddHabitController getAddHabitController() {
        return new AddHabitController(getHabitInteractionsFactory());
    }
}
