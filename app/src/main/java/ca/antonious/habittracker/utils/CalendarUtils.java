package ca.antonious.habittracker.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by George on 2016-09-12.
 */
public class CalendarUtils {
    public static int getDaysBetween(Calendar start, Calendar end) {
        List<Integer> days = Arrays.asList(Calendar.SUNDAY,
                                           Calendar.MONDAY,
                                           Calendar.TUESDAY,
                                           Calendar.WEDNESDAY,
                                           Calendar.THURSDAY,
                                           Calendar.FRIDAY,
                                           Calendar.SATURDAY);

        return getDaysBetween(start, end, days);
    }

    public static int getDaysBetween(Calendar start, Calendar end, List<Integer> daysOfTheWeekToInclude) {
        int total = 0;
        Calendar calendar = (Calendar) end.clone();

        while (calendar.compareTo(start) > 0) {
            if (daysOfTheWeekToInclude.contains(start.get(Calendar.DAY_OF_WEEK))) {
                total++;
            }
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }

        return total;
    }
}
