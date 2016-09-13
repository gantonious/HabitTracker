package ca.antonious.habittracker.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by George on 2016-09-12.
 */
public class DateUtils {
    public static boolean areOnTheSameDate(Date lhs, Date rhs) {
        Calendar leftCalendar = Calendar.getInstance();
        leftCalendar.setTime(lhs);

        Calendar rightCalendar = Calendar.getInstance();
        rightCalendar.setTime(rhs);

        return rightCalendar.get(Calendar.DAY_OF_YEAR) == leftCalendar.get(Calendar.DAY_OF_YEAR) &&
               rightCalendar.get(Calendar.YEAR) == leftCalendar.get(Calendar.YEAR);
    }
}
