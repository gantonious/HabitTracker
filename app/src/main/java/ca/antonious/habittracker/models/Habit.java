package ca.antonious.habittracker.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by George on 2016-09-01.
 */
public class Habit {
    private String id;
    private String name;
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

    public List<HabitCompletion> getCompletions() {
        return completions;
    }

    public void setCompletions(List<? extends HabitCompletion> completions) {
        this.completions.clear();
        this.completions.addAll(completions);
    }
}
