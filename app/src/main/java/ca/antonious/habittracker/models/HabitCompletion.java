package ca.antonious.habittracker.models;

import java.util.Date;
import java.util.UUID;

import ca.antonious.habittracker.utils.Utils;

/**
 * Created by George on 2016-09-01.
 */
public class HabitCompletion {
    private String id;
    private Date completionTime;

    public HabitCompletion(Date completionTime) {
        setId(UUID.randomUUID().toString());
        setCompletionTime(completionTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !obj.getClass().equals(getClass())) return false;

        HabitCompletion otherHabitCompletion = (HabitCompletion) obj;

        return Utils.equals(getId(), otherHabitCompletion.getId()) &&
                Utils.equals(getCompletionTime(), otherHabitCompletion.getCompletionTime());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
