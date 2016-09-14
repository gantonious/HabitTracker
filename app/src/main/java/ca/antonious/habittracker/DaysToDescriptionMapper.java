package ca.antonious.habittracker;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by George on 2016-09-04.
 */
public class DaysToDescriptionMapper {
    public String map(List<Integer> days) {
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

    private boolean isEveryDay(List<Integer> days) {
        return days.contains(Calendar.SUNDAY) &&
                days.contains(Calendar.MONDAY) &&
                days.contains(Calendar.TUESDAY) &&
                days.contains(Calendar.WEDNESDAY) &&
                days.contains(Calendar.THURSDAY) &&
                days.contains(Calendar.FRIDAY) &&
                days.contains(Calendar.SATURDAY);
    }

    private boolean isOnlyWeekdays(List<Integer> days) {
        return !days.contains(Calendar.SUNDAY) &&
                days.contains(Calendar.MONDAY) &&
                days.contains(Calendar.TUESDAY) &&
                days.contains(Calendar.WEDNESDAY) &&
                days.contains(Calendar.THURSDAY) &&
                days.contains(Calendar.FRIDAY) &&
                !days.contains(Calendar.SATURDAY);
    }

    private boolean isOnlyWeekends(List<Integer> days) {
        return days.contains(Calendar.SUNDAY) &&
                !days.contains(Calendar.MONDAY) &&
                !days.contains(Calendar.TUESDAY) &&
                !days.contains(Calendar.WEDNESDAY) &&
                !days.contains(Calendar.THURSDAY) &&
                !days.contains(Calendar.FRIDAY) &&
                days.contains(Calendar.SATURDAY);
    }

    private String constructDaysDescription(List<Integer> days) {
        if (days.isEmpty()) return "Never";

        Collections.sort(days);

        String out = "";

        for (Integer day: days) {
            out += fromDay(day) + ", ";
        }

        return out.substring(0, out.length() - 2);

    }

    private String fromDay(Integer day) {
        switch (day) {
            case Calendar.SUNDAY: return "Sun";
            case Calendar.MONDAY: return "Mon";
            case Calendar.TUESDAY: return "Tues";
            case Calendar.WEDNESDAY: return "Wed";
            case Calendar.THURSDAY: return "Thurs";
            case Calendar.FRIDAY: return "Fri";
            case Calendar.SATURDAY: return "Sat";
            default: return "";
        }
    }
}
