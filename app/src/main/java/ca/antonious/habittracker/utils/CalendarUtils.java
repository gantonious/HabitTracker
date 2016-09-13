package ca.antonious.habittracker.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by George on 2016-09-12.
 */
public class CalendarUtils {
    public static int getDaysBetween(Calendar inclusiveStart, Calendar exclusiveEnd) {
        List<Integer> days = Arrays.asList(Calendar.SUNDAY,
                                           Calendar.MONDAY,
                                           Calendar.TUESDAY,
                                           Calendar.WEDNESDAY,
                                           Calendar.THURSDAY,
                                           Calendar.FRIDAY,
                                           Calendar.SATURDAY);

        return getDaysBetween(inclusiveStart, exclusiveEnd, days);
    }

    public static int getDaysBetween(Calendar inclusiveStart, Calendar exclusiveEnd, List<Integer> daysOfTheWeekToInclude) {
        Calendar calendar = (Calendar) exclusiveEnd.clone();
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        int total = 0;

        while (calendar.compareTo(inclusiveStart) >= 0) {
            if (daysOfTheWeekToInclude.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                total++;
            }
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }

        return total;
    }
}
