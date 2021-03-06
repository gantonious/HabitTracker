package ca.antonious.habittracker.habitstoragetests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.fileacess.IFileHandler;
import ca.antonious.habittracker.habitstorage.LocalHabitService;
import ca.antonious.habittracker.models.Habit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
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
public class LocalHabitServiceTests {
    private IFileHandler fileHandler;
    private LocalHabitService localHabitService;

    private Habit habit1;
    private Habit habit2;
    private Habit habit3;

    private Habit updatedHabit2;

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

        updatedHabit2 = new Habit();
        updatedHabit2.setId("KindOfAwesomeHabit-Id");
        updatedHabit2.setName("UpdatedKindOfAwesomeHabit");
        updatedHabit2.setStartDate(new Date(221329389));
        updatedHabit2.setDaysToComplete(Arrays.asList(Calendar.MONDAY, Calendar.WEDNESDAY));

        habit3 = new Habit();
        habit3.setId("CoolHabit-Id");
        habit3.setName("CoolHabit");
        habit3.setStartDate(new Date(1298472988));
        habit3.setDaysToComplete(Arrays.asList(Calendar.SATURDAY, Calendar.WEDNESDAY));

        fileHandler = mock(IFileHandler.class);
        localHabitService = new LocalHabitService(fileHandler);
    }

    @Test
    public void test_getHabits_ifFileDoesNotExist_thenReturnsEmptyList() {
        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(null);

        List<Habit> expectedHabitList = new ArrayList<>();
        List<Habit> actualHabitList = localHabitService.getHabits();

        assertEquals(expectedHabitList, actualHabitList);
    }

    @Test
    public void test_getHabits_ifFileExistsButDataIsEmpty_thenReturnsEmptyList() {
        String serializedHabits = getSerializedHabitData(new ArrayList<Habit>());
        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(serializedHabits);

        List<Habit> expectedHabitList = new ArrayList<>();
        List<Habit> actualHabitList = localHabitService.getHabits();

        assertEquals(expectedHabitList, actualHabitList);
    }

    @Test
    public void test_getHabits_ifFileExistsAndIsPopulatedWithData_thenReturnsDataFromFileHandler() {
        String serializedHabits = getSerializedHabitData(Arrays.asList(habit1, habit2));
        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(serializedHabits);

        List<Habit> expectedHabitList = Arrays.asList(habit1, habit2);
        List<Habit> actualHabitList = localHabitService.getHabits();

        assertEquals(expectedHabitList, actualHabitList);
    }

    @Test
    public void test_addHabit_ifHabitExists_thenNoInteractionsOccurWithTheFileHandler() {
        String baseSerializedHabits = getSerializedHabitData(Arrays.asList(habit1, habit2));

        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(baseSerializedHabits);
        localHabitService.addHabit(habit2);

        verify(fileHandler, never()).saveStringToFile(anyString(), anyString());
    }

    @Test
    public void test_addHabit_ifHabitDoesNotExist_thenSavesWithHabitAdded() {
        String baseSerializedHabits = getSerializedHabitData(Arrays.asList(habit1, habit2));
        String expectedSerializedMap = getSerializedHabitData(Arrays.asList(habit1, habit2, habit3));

        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(baseSerializedHabits);
        localHabitService.addHabit(habit3);

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(fileHandler).saveStringToFile(anyString(), stringCaptor.capture());

        assertSerializedHabitMapsAreEquivalent(expectedSerializedMap, stringCaptor.getValue());
    }

    @Test
    public void test_updateHabit_ifHabitDoesNotExist_thenNoInteractionsOccurWithTheFileHandler() {
        String baseSerializedHabits = getSerializedHabitData(Arrays.asList(habit1, habit2));
        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(baseSerializedHabits);

        localHabitService.updateHabit(habit3);

        verify(fileHandler, never()).saveStringToFile(anyString(), anyString());
    }

    @Test
    public void test_updateHabit_ifHabitExists_thenSavesWithHabitUpdated() {
        String baseSerializedHabits = getSerializedHabitData(Arrays.asList(habit1, habit2));
        String expectedSerializedMap = getSerializedHabitData(Arrays.asList(habit1, updatedHabit2));

        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(baseSerializedHabits);
        localHabitService.updateHabit(updatedHabit2);

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(fileHandler).saveStringToFile(anyString(), stringCaptor.capture());

        assertSerializedHabitMapsAreEquivalent(expectedSerializedMap, stringCaptor.getValue());
    }

    @Test
    public void test_removeHabit_ifHabitDoesNotExist_thenNoInteractionsOccurWithTheFileHandler() {
        String baseSerializedHabits = getSerializedHabitData(Arrays.asList(habit1, habit2));
        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(baseSerializedHabits);

        localHabitService.removeHabit(habit3.getId());

        verify(fileHandler, never()).saveStringToFile(anyString(), anyString());
    }

    @Test
    public void test_removeHabit_ifHabitExists_thenSavesWithHabitRemoved() {
        String baseSerializedHabits = getSerializedHabitData(Arrays.asList(habit1, habit2));
        String expectedSerializedMap = getSerializedHabitData(Arrays.asList(habit2));

        when(fileHandler.loadFileAsString(Constants.HABIT_MAP_FILE_NAME)).thenReturn(baseSerializedHabits);
        localHabitService.removeHabit(habit1.getId());

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(fileHandler).saveStringToFile(anyString(), stringCaptor.capture());

        assertSerializedHabitMapsAreEquivalent(expectedSerializedMap, stringCaptor.getValue());
    }

    private void assertSerializedHabitMapsAreEquivalent(String expectedSerializedMap, String actualSerializedMap) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

        Map<String, Habit> expectedMap = gson.fromJson(expectedSerializedMap, new TypeToken<Map<String, Habit>>() {}.getType());
        Map<String, Habit> actualMap = gson.fromJson(actualSerializedMap, new TypeToken<Map<String, Habit>>() {}.getType());

        assertEquals(expectedMap, actualMap);
    }

    private Map<String, Habit> getDeserializedHabitData(List<Habit> habits) {
        Map<String, Habit> habitMap = new HashMap<>();
        for (Habit habit: habits) {
            habitMap.put(habit.getId(), habit);
        }

        return habitMap;
    }

    private String getSerializedHabitData(List<Habit> habits) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
        return gson.toJson(getDeserializedHabitData(habits));
    }
}
