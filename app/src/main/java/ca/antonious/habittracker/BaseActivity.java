package ca.antonious.habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by George on 2016-09-02.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        resolveDependencies();
    }

    protected void bindViews() {

    }

    protected void resolveDependencies() {

    }

    public HabitTrackerApplication getHabitTrackerApplication() {
        return (HabitTrackerApplication) getApplication();
    }
}
