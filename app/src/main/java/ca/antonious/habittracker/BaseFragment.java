package ca.antonious.habittracker;

import android.support.v4.app.Fragment;

/**
 * Created by George on 2016-09-30.
 *
 * BaseFragment exposes the HabitTrackerApplication class which can be
 * used to resolve dependencies
 */

public class BaseFragment extends Fragment {
    public HabitTrackerApplication getHabitTrackerApplication() {
        return (HabitTrackerApplication) getActivity().getApplication();
    }
}
