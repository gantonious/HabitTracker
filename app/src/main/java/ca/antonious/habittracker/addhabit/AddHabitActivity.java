package ca.antonious.habittracker.addhabit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.DayOfTheWeekToCalendarDateMapper;
import ca.antonious.habittracker.views.DatePickerFragment;
import ca.antonious.habittracker.views.DaysOfTheWeekPicker;
import ca.antonious.habittracker.views.EditTextFragment;
import ca.antonious.habittracker.views.OptionPreviewView;
import ca.antonious.habittracker.R;

public class AddHabitActivity extends BaseActivity implements IAddHabitView {
    private Button addButton;
    private OptionPreviewView nameOption;
    private OptionPreviewView startingDateOption;
    private DaysOfTheWeekPicker daysOfTheWeekPicker;

    private Date startingDate;

    private EditTextFragment editTextFragment;
    private DatePickerFragment datePickerFragment;

    private AddHabitController addHabitController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        bindViews();
        resolveDependencies();
        configureDialogs();
        configureListeners();

        setStartingDate(new Date());
    }

    private void bindViews() {
        addButton = (Button) findViewById(R.id.add_habit);
        nameOption = (OptionPreviewView) findViewById(R.id.habit_label_option);
        startingDateOption = (OptionPreviewView) findViewById(R.id.habit_start_option);
        daysOfTheWeekPicker = (DaysOfTheWeekPicker) findViewById(R.id.habit_repeat_days);
    }

    private void resolveDependencies() {
        addHabitController = getHabitTrackerApplication().getAddHabitController();
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

            setStartingDate(calendar.getTime());
        }
    };

    private void setStartingDate(Date date) {
        startingDate = date;

        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = humanReadableDateFormat.format(date);
        startingDateOption.setPreviewText(formattedDate);
    }

    private void onAdd() {
        AddHabitRequest addHabitRequest = new AddHabitRequest();

        addHabitRequest.setName(nameOption.getPreviewText());
        addHabitRequest.setStartDate(startingDate);
        addHabitRequest.setDaysOfTheWeek(new DayOfTheWeekToCalendarDateMapper().map(daysOfTheWeekPicker.getSelectedDays()));

        addHabitController.addHabit(addHabitRequest);
    }

    @Override
    public void onHabitAdded() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addHabitController.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        addHabitController.detachView();
    }
}
