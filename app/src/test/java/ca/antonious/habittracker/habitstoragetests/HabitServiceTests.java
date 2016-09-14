package ca.antonious.habittracker.habitstoragetests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.fileacess.IFileHandler;
import ca.antonious.habittracker.habitstorage.HabitService;
import ca.antonious.habittracker.models.Habit;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by George on 2016-09-13.
 */
public class HabitServiceTests {
    private IFileHandler fileHandler;
    private HabitService habitService;

    private Habit habit1;
    private Habit habit2;

    @Before
    public void set_up() {
        habit1 = new Habit();
        habit1.setId("AwesomeHabit-Id");
        habit1.setName("AwesomeHabit");
        habit1.setStartDate(new Date(347874912));
        habit1.setDaysToComplete(Arrays.asList(Calendar.SUNDAY));

        habit2 = new Habit();
        habit2.setId("KindOfAwesomeHabit-Id");
        habit2.setName("KindOfAwesomeHabit");
        habit2.setStartDate(new Date(221329389));
        habit2.setDaysToComplete(Arrays.asList(Calendar.MONDAY, Calendar.WEDNESDAY));

        fileHandler = mock(IFileHandler.class);
        habitService = new HabitService(fileHandler);
    }

    @Test
    public void test_getHabits_thenReturnsDataFromFileHandler() {
        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(getSerializedHabitData());

        List<Habit> expectedHabitList = Arrays.asList(habit1, habit2);
        List<Habit> actualHabitList = habitService.getHabits();

        assertEquals(expectedHabitList, actualHabitList);
    }

    private Map<String, Habit> getDeserializedHabitData() {
        Map<String, Habit> habitMap = new HashMap<>();
        habitMap.put(habit1.getId(), habit1);
        habitMap.put(habit2.getId(), habit2);

        return habitMap;
    }

    private String getSerializedHabitData() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
        return gson.toJson(getDeserializedHabitData());
    }
}
