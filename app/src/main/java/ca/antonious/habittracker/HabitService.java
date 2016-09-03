package ca.antonious.habittracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.habittracker.Utils.StringUtils;
import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-01.
 */
public class HabitService implements IHabitService {
    private FileHandler fileHandler;

    public HabitService(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public List<Habit> getHabits() {
        String serializedHabits = fileHandler.loadFileAsString(Constants.HABIT_LIST_FILE_NAME);

        if (StringUtils.isStringNullOrEmpty(serializedHabits)) {
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(serializedHabits, new TypeToken<List<Habit>>() {}.getType());
        }
    }

    @Override
    public void addHabit(Habit habit) {
        List<Habit> habits = getHabits();
        habits.add(habit);

        saveHabits(habits);
    }

    @Override
    public void updateHabit(Habit habitToUpdate) {

    }

    private void saveHabits(List<? extends Habit> habits) {
        String serializedHabits = new Gson().toJson(habits);
        fileHandler.saveStringToFile(Constants.HABIT_LIST_FILE_NAME, serializedHabits);
    }
}
