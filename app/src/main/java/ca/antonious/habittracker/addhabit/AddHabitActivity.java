package ca.antonious.habittracker.addhabit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.DatePickerFragment;
import ca.antonious.habittracker.EditTextFragment;
import ca.antonious.habittracker.IHabitRepository;
import ca.antonious.habittracker.views.OptionPreviewView;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;

public class AddHabitActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    private Button addButton;
    private OptionPreviewView nameOption;
    private OptionPreviewView startingDateOption;

    private IHabitRepository habitRepository;

    private EditTextFragment editTextFragment;
    private DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        habitRepository = getHabitTrackerApplication().getHabitRepository();

        addButton = (Button) findViewById(R.id.add_habit);
        nameOption = (OptionPreviewView) findViewById(R.id.habit_label_option);
        startingDateOption = (OptionPreviewView) findViewById(R.id.habit_start_option);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd();
                finish();
            }
        });

        editTextFragment = new EditTextFragment();

        datePickerFragment = new DatePickerFragment();
        datePickerFragment.setDateSetListener(this);

        nameOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextFragment.show(getSupportFragmentManager(), "label");
            }
        });

        startingDateOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager(), "date");
            }
        });
    }

    private void onAdd() {
        Habit habit = new Habit();
        habit.setName(nameOption.getPreviewText());

        habitRepository.addHabit(habit);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);

        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String date = humanReadableDateFormat.format(calendar.getTime());

        startingDateOption.setPreviewText(date);
    }
}
