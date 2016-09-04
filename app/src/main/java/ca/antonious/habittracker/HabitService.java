package ca.antonious.habittracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.antonious.habittracker.fileacess.IFileHandler;
import ca.antonious.habittracker.utils.StringUtils;
import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-01.
 */
public class HabitService implements IHabitService {
    private IFileHandler fileHandler;

    public HabitService(IFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public List<Habit> getHabits() {
        return new ArrayList<>(loadHabits().values());
    }

    @Override
    public void addHabit(Habit habit) {
        Map<String, Habit> habits = loadHabits();
        habits.put(habit.getId(), habit);

        saveHabits(habits);
    }

    @Override
    public void updateHabit(Habit habit) {
        Map<String, Habit> habits = loadHabits();
        habits.put(habit.getId(), habit);

        saveHabits(habits);
    }

    @Override
    public void removeHabit(String id) {
        Map<String, Habit> habits = loadHabits();
        habits.remove(id);

        saveHabits(habits);
    }

    private Map<String, Habit> loadHabits() {
        String serializedHabits = fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME);

        if (StringUtils.isStringNullOrEmpty(serializedHabits)) {
            return new HashMap<>();
        } else {
            return new Gson().fromJson(serializedHabits, new TypeToken<Map<String, Habit>>() {}.getType());
        }
    }

    private void saveHabits(Map<String, Habit> habitMap) {
        String serializedHabits = new Gson().toJson(habitMap);
        fileHandler.saveStringToFile(Constants.HABIT_MAP_FILE_NAME, serializedHabits);
    }
}
