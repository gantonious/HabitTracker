package ca.antonious.habittracker.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.habittracker.R;

/**
 * Created by George on 2016-09-04.
 */
public class DaysOfTheWeekPicker extends LinearLayout {
    protected ToggleButton sundayToggle;
    protected ToggleButton mondayToggle;
    protected ToggleButton tuesdayToggle;
    protected ToggleButton wednesdayToggle;
    protected ToggleButton thursdayToggle;
    protected ToggleButton fridayToggle;
    protected ToggleButton saturdayToggle;

    public DaysOfTheWeekPicker(Context context) {
        this(context, null);
    }

    public DaysOfTheWeekPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DaysOfTheWeekPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflateLayout(context);
        bindViews();
    }

    private void inflateLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.day_of_the_week_picker, this, true);
    }

    private void bindViews() {
        sundayToggle = (ToggleButton) findViewById(R.id.sunday_toggle);
        mondayToggle = (ToggleButton) findViewById(R.id.monday_toggle);
        tuesdayToggle = (ToggleButton) findViewById(R.id.tuesday_toggle);
        wednesdayToggle = (ToggleButton) findViewById(R.id.wednesday_toggle);
        thursdayToggle = (ToggleButton) findViewById(R.id.thursday_toggle);
        fridayToggle = (ToggleButton) findViewById(R.id.friday_toggle);
        saturdayToggle = (ToggleButton) findViewById(R.id.saturday_toggle);
    }

    public List<DayOfTheWeek> getSelectedDays() {
        List<DayOfTheWeek> selectedDays = new ArrayList<>();

        if (sundayToggle.isChecked()) selectedDays.add(DayOfTheWeek.SUNDAY);
        if (mondayToggle.isChecked()) selectedDays.add(DayOfTheWeek.MONDAY);
        if (tuesdayToggle.isChecked()) selectedDays.add(DayOfTheWeek.TUESDAY);
        if (wednesdayToggle.isChecked()) selectedDays.add(DayOfTheWeek.WEDNESDAY);
        if (thursdayToggle.isChecked()) selectedDays.add(DayOfTheWeek.THURSDAY);
        if (fridayToggle.isChecked()) selectedDays.add(DayOfTheWeek.FRIDAY);
        if (saturdayToggle.isChecked()) selectedDays.add(DayOfTheWeek.SATURDAY);

        return selectedDays;
    }

    public enum DayOfTheWeek {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }
}
