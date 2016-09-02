package ca.antonious.habittracker.models;

import java.util.Date;

/**
 * Created by George on 2016-09-01.
 */
public class HabitCompletion {
    private Date completionTime;

    public HabitCompletion(Date completionTime) {
        setCompletionTime(completionTime);
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
}
