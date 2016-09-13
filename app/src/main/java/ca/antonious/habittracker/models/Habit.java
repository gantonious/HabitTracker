package ca.antonious.habittracker.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ca.antonious.habittracker.utils.CalendarUtils;

/**
 * Created by George on 2016-09-01.
 */
public class Habit {
    private String id;
    private String name;
    private Date startDate;
    private List<Integer> daysToComplete = new ArrayList<>();
    private List<HabitCompletion> completions = new ArrayList<>();

    public Habit() {
        setId(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public List<HabitCompletion> getCompletions() {
        return completions;
    }

    public void setCompletions(List<? extends HabitCompletion> completions) {
        this.completions.clear();
        this.completions.addAll(completions);
    }

    public HabitCompletion getCompletion(String completionId) {
        for (HabitCompletion habitCompletion: getCompletions()) {
            if (completionId.equals(habitCompletion.getId())) {
                return habitCompletion;
            }
        }

        return null;
    }

    public List<Integer> getDaysToComplete() {
        return daysToComplete;
    }

    public void setDaysToComplete(List<Integer> daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    public String getStartingDateDescription() {
        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return "Started on " + humanReadableDateFormat.format(getStartDate());
    }

    public String getCompletionsDescription() {
        if (getCompletions().size() > 1) {
            return String.format("Completed %d times", getCompletions().size());
        } else if (getCompletions().size() == 1) {
            return "Completed once";
        }
        return "Never completed";
    }

    public String getMissedDaysDescription() {
        int missedDays = getMissedDays();

        if (missedDays > 1) {
            return String.format("Missed %d times", missedDays);
        } else if (missedDays == 1) {
            return "Missed once";
        }
        return "Never missed";
    }

    public int getMissedDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Calendar startingDate = Calendar.getInstance();
        startingDate.setTime(getStartDate());

        return CalendarUtils.getDaysBetween(startingDate, calendar, getDaysToComplete());
    }
}
