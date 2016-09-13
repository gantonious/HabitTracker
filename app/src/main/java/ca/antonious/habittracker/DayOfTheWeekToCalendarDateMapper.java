package ca.antonious.habittracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.antonious.habittracker.views.DaysOfTheWeekPicker;

/**
 * Created by George on 2016-09-12.
 */
public class DayOfTheWeekToCalendarDateMapper {
    public List<Integer> map(List<DaysOfTheWeekPicker.DayOfTheWeek> daysOfTheWeek) {
        List<Integer> selectedDays = new ArrayList<>();

        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.SUNDAY)) selectedDays.add(Calendar.SUNDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.MONDAY)) selectedDays.add(Calendar.MONDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.TUESDAY)) selectedDays.add(Calendar.TUESDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.WEDNESDAY)) selectedDays.add(Calendar.WEDNESDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.THURSDAY)) selectedDays.add(Calendar.THURSDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.FRIDAY)) selectedDays.add(Calendar.FRIDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.SATURDAY)) selectedDays.add(Calendar.SATURDAY);

        return selectedDays;
    }
}
