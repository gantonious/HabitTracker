package ca.antonious.habittracker;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by George on 2016-09-02.
 */
public class BaseActivity extends AppCompatActivity {
    public HabitTrackerApplication getHabitTrackerApplication() {
        return (HabitTrackerApplication) getApplication();
    }
}
