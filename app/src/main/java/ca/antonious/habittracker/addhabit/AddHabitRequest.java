package ca.antonious.habittracker.addhabit;

import java.util.Date;
import java.util.List;

/**
 * Created by George on 2016-09-12.
 */
public class AddHabitRequest {
    private String name;
    private Date startDate;
    private List<Integer> daysOfTheWeek;

    public AddHabitRequest(String name, Date startDate, List<Integer> daysOfTheWeek) {
        this.name = name;
        this.startDate = startDate;
        this.daysOfTheWeek = daysOfTheWeek;
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

    public List<Integer> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(List<Integer> daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }
}
