package ca.antonious.habittracker.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2016-09-01.
 */
public class Habit {
    private String name;
    private List<HabitCompletion> completions = new ArrayList<>();

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
