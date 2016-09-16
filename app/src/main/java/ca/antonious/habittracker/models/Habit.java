package ca.antonious.habittracker.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ca.antonious.habittracker.utils.DateUtils;
import ca.antonious.habittracker.utils.Utils;

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

    public void addCompletion(HabitCompletion habitCompletion) {
        this.completions.add(habitCompletion);
    }

    public List<Integer> getDaysToComplete() {
        return daysToComplete;
    }

    public void setDaysToComplete(List<Integer> daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    public boolean hasBeenCompletedOnDay(Date date) {
        for (HabitCompletion completion: completions) {
            if (DateUtils.areOnTheSameDate(completion.getCompletionTime(), date)) {
                return true;
            }
        }
        return false;
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

    public String getMissedDaysDescription(Date upperBound) {
        int missedDays = getMissedDays(upperBound);

        if (missedDays > 1) {
            return String.format("Missed %d times", missedDays);
        } else if (missedDays == 1) {
            return "Missed once";
        }
        return "Never missed";
    }

    private int getMissedDays(Date upperBound) {
        if (startDate.compareTo(upperBound) > 0) {
            return 0;
        }

        Calendar iterationCalendar = Calendar.getInstance();
        iterationCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(upperBound);

        int totalDaysMissed = 0;

        while (!DateUtils.areOnTheSameDate(iterationCalendar.getTime(), endCalendar.getTime())) {
            if (wasDateMissed(iterationCalendar)) {
                totalDaysMissed++;
            }
            iterationCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return totalDaysMissed;
    }

    private boolean wasDateMissed(Calendar calendar) {
        return getDaysToComplete().contains(calendar.get(Calendar.DAY_OF_WEEK)) &&
                !hasBeenCompletedOnDay(calendar.getTime());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !obj.getClass().equals(getClass())) return false;

        Habit otherHabit = (Habit) obj;

        return Utils.equals(getId(), otherHabit.getId()) &&
                Utils.equals(getName(), otherHabit.getName()) &&
                Utils.equals(getStartDate(), otherHabit.getStartDate()) &&
                Utils.equals(getDaysToComplete(), otherHabit.getDaysToComplete()) &&
                Utils.equals(getCompletions(), otherHabit.getCompletions());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
