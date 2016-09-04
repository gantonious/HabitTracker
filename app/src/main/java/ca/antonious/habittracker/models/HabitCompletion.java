package ca.antonious.habittracker.models;

import java.util.Date;
import java.util.UUID;

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
}
