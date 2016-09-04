package ca.antonious.habittracker;

import java.util.Collections;
import java.util.List;

import ca.antonious.habittracker.models.Days;

/**
 * Created by George on 2016-09-04.
 */
public class DaysToDescriptionMapper {
    public String map(List<Days> days) {
        if (isEveryDay(days)) {
            return "Everyday";
        } else if (isOnlyWeekdays(days)) {
            return "Weekdays";
        } else if (isOnlyWeekends(days)) {
            return "Weekends";
        } else {
            return constructDaysDescription(days);
        }
    }

    private boolean isEveryDay(List<Days> days) {
        return days.contains(Days.SUNDAY) &&
                days.contains(Days.MONDAY) &&
                days.contains(Days.TUESDAY) &&
                days.contains(Days.WEDNESDAY) &&
                days.contains(Days.THURSDAY) &&
                days.contains(Days.FRIDAY) &&
                days.contains(Days.SATURDAY);
    }

    private boolean isOnlyWeekdays(List<Days> days) {
        return !days.contains(Days.SUNDAY) &&
                days.contains(Days.MONDAY) &&
                days.contains(Days.TUESDAY) &&
                days.contains(Days.WEDNESDAY) &&
                days.contains(Days.THURSDAY) &&
                days.contains(Days.FRIDAY) &&
                !days.contains(Days.SATURDAY);
    }

    private boolean isOnlyWeekends(List<Days> days) {
        return days.contains(Days.SUNDAY) &&
                !days.contains(Days.MONDAY) &&
                !days.contains(Days.TUESDAY) &&
                !days.contains(Days.WEDNESDAY) &&
                !days.contains(Days.THURSDAY) &&
                !days.contains(Days.FRIDAY) &&
                days.contains(Days.SATURDAY);
    }

    private String constructDaysDescription(List<Days> days) {
        if (days.isEmpty()) return "";

        Collections.sort(days);

        String out = "";

        for (Days day: days) {
            out += fromDay(day) + ", ";
        }

        return out.substring(0, out.length() - 2);

    }

    private String fromDay(Days day) {
        switch (day) {
            case SUNDAY: return "Sun";
            case MONDAY: return "Mon";
            case TUESDAY: return "Tues";
            case WEDNESDAY: return "Wed";
            case THURSDAY: return "Thurs";
            case FRIDAY: return "Fri";
            case SATURDAY: return "Sat";
            default: return "";
        }
    }
}
