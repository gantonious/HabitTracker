package ca.antonious.habittracker.time;

import java.util.Date;

/**
 * Created by George on 2016-09-15.
 */
public class Clock implements IClock {
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
