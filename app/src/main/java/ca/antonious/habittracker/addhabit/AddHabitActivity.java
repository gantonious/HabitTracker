package ca.antonious.habittracker.addhabit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.models.Days;
import ca.antonious.habittracker.views.DatePickerFragment;
import ca.antonious.habittracker.views.DaysOfTheWeekPicker;
import ca.antonious.habittracker.views.EditTextFragment;
import ca.antonious.habittracker.IHabitRepository;
import ca.antonious.habittracker.views.OptionPreviewView;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;

public class AddHabitActivity extends BaseActivity {
    private Button addButton;
    private OptionPreviewView nameOption;
    private OptionPreviewView startingDateOption;
    private DaysOfTheWeekPicker daysOfTheWeekPicker;

    private IHabitRepository habitRepository;

    private EditTextFragment editTextFragment;
    private DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        bindViews();
        resolveDependencies();
        configureDialogs();
        configureListeners();
    }

    private void bindViews() {
        addButton = (Button) findViewById(R.id.add_habit);
        nameOption = (OptionPreviewView) findViewById(R.id.habit_label_option);
        startingDateOption = (OptionPreviewView) findViewById(R.id.habit_start_option);
        daysOfTheWeekPicker = (DaysOfTheWeekPicker) findViewById(R.id.habit_repeat_days);
    }

    private void resolveDependencies() {
        habitRepository = getHabitTrackerApplication().getHabitRepository();
    }

    private void configureDialogs() {
        editTextFragment = new EditTextFragment();
        editTextFragment.setOnConfirmListener(onConfirmListener);

        datePickerFragment = new DatePickerFragment();
        datePickerFragment.setDateSetListener(onDateSetListener);
    }

    private void configureListeners() {
        nameOption.setOnClickListener(nameOptionClickListener);
        startingDateOption.setOnClickListener(dateOptionClickListener);
        addButton.setOnClickListener(createClickListener);
    }

    private View.OnClickListener createClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onAdd();
            finish();
        }
    };

    private View.OnClickListener nameOptionClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editTextFragment.show(getSupportFragmentManager(), "label");
        }
    };

    private View.OnClickListener dateOptionClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            datePickerFragment.show(getSupportFragmentManager(), "date");
        }
    };

    private void onAdd() {
        Habit habit = new Habit();
        habit.setName(nameOption.getPreviewText());
        habit.setDaysToComplete(fromDaysOfTheWeek(daysOfTheWeekPicker.getSelectedDays()));

        habitRepository.addHabit(habit);
    }

    private List<Days> fromDaysOfTheWeek(List<DaysOfTheWeekPicker.DayOfTheWeek> daysOfTheWeek) {
        List<Days> selectedDays = new ArrayList<>();

        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.SUNDAY)) selectedDays.add(Days.SUNDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.MONDAY)) selectedDays.add(Days.MONDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.TUESDAY)) selectedDays.add(Days.TUESDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.WEDNESDAY)) selectedDays.add(Days.WEDNESDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.THURSDAY)) selectedDays.add(Days.THURSDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.FRIDAY)) selectedDays.add(Days.FRIDAY);
        if (daysOfTheWeek.contains(DaysOfTheWeekPicker.DayOfTheWeek.SATURDAY)) selectedDays.add(Days.SATURDAY);

        return selectedDays;
    }

    private EditTextFragment.OnConfirmListener onConfirmListener = new EditTextFragment.OnConfirmListener() {
        @Override
        public void onConfirm(String text) {
            nameOption.setPreviewText(text);
        }
    };

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);

            SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("MMM dd, yyyy");
            String date = humanReadableDateFormat.format(calendar.getTime());

            startingDateOption.setPreviewText(date);
        }
    };
}
