package ca.antonious.habittracker.habitdetails;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.DaysToDescriptionMapper;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Days;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;

public class HabitDetailsActivity extends BaseActivity implements IHabitDetailsView {
    private TextView titleTextView;
    private TextView creationDateTextView;
    private TextView habitDatesTextView;
    private TextView habitCompletionStatsDescription;
    private TextView habitMissedCompletionsDescription;
    private Button completeHabitButton;

    private RecyclerView completionsRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HabitCompletionAdapter habitCompletionAdapter;

    private HabitDetailsController habitDetailsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);

        bindViews();
        resolveDependencies();
        setUpRecyclerView();
        handleCompleteButtonClicks();
    }

    private void bindViews() {
        titleTextView = (TextView) findViewById(R.id.habit_details_title);
        creationDateTextView = (TextView) findViewById(R.id.habit_details_created_date);
        habitDatesTextView = (TextView) findViewById(R.id.habit_details_dates);
        completionsRecyclerView = (RecyclerView) findViewById(R.id.habit_details_recent_completions_list);
        habitCompletionStatsDescription = (TextView) findViewById(R.id.habit_details_total_completions);
        habitMissedCompletionsDescription = (TextView) findViewById(R.id.habit_details_total_missed_completions);
        completeHabitButton = (Button) findViewById(R.id.habit_details_complete_button);
    }

    private void resolveDependencies() {
        String id = getIntent().getStringExtra(Constants.EXTRA_HABIT_ID);
        habitDetailsController = getHabitTrackerApplication().getHabitDetailsController(id);
    }

    private void setUpRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        habitCompletionAdapter = new HabitCompletionAdapter();

        completionsRecyclerView.setLayoutManager(linearLayoutManager);
        completionsRecyclerView.setAdapter(habitCompletionAdapter);

        habitCompletionAdapter.setCompletionRemovedListener(new HabitCompletionAdapter.CompletionRemovedListener() {
            @Override
            public void onCompletionRemoved(HabitCompletion habitCompletion, int position) {
                habitDetailsController.removeHabitCompletion(habitCompletion.getId());
            }
        });
    }

    private void handleCompleteButtonClicks() {
        completeHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitDetailsController.markHabitAsComplete();
            }
        });
    }

    @Override
    public void displayHabit(Habit habit) {
        titleTextView.setText(habit.getName());
        habitDatesTextView.setText(new DaysToDescriptionMapper().map(habit.getDaysToComplete()));
        creationDateTextView.setText(getCreationDateDescription(habit));
        habitCompletionStatsDescription.setText(getCompletionsDescription(habit));
        habitMissedCompletionsDescription.setText(getMissedCompletionsDescription(habit));

        habitCompletionAdapter.clear();
        habitCompletionAdapter.addAll(habit.getCompletions());
        habitCompletionAdapter.notifyDataSetChanged();
    }

    private String getCreationDateDescription(Habit habit) {
        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return "Started on " + humanReadableDateFormat.format(habit.getStartDate());
    }

    private String getCompletionsDescription(Habit habit) {
        if (habit.getCompletions().size() > 1) {
            return String.format("Completed %d times", habit.getCompletions().size());
        } else if (habit.getCompletions().size() == 1) {
            return "Completed once";
        }
        return "Never completed";
    }

    private String getMissedCompletionsDescription(Habit habit) {
        int daysMissed = getMissedDays(habit);

        if (daysMissed > 1) {
            return String.format("Missed %d times", daysMissed);
        } else if (daysMissed == 1) {
            return "Missed once";
        }
        return "Never missed";
    }

    private int getMissedDays(Habit habit) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Calendar startingDate = Calendar.getInstance();
        startingDate.setTime(habit.getStartDate());

        int total = 0;
        List<Integer> calenderDays = mapHabitDaysToCalenderDays(habit);

        while (calendar.compareTo(startingDate) > 0) {
            if (calenderDays.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                total++;
            }
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }

        return total;
    }

    private List<Integer> mapHabitDaysToCalenderDays(Habit habit) {
        List<Integer> days = new ArrayList<>();

        if (habit.getDaysToComplete().contains(Days.SUNDAY)) days.add(Calendar.SUNDAY);
        if (habit.getDaysToComplete().contains(Days.MONDAY)) days.add(Calendar.MONDAY);
        if (habit.getDaysToComplete().contains(Days.TUESDAY)) days.add(Calendar.TUESDAY);
        if (habit.getDaysToComplete().contains(Days.WEDNESDAY)) days.add(Calendar.WEDNESDAY);
        if (habit.getDaysToComplete().contains(Days.THURSDAY)) days.add(Calendar.THURSDAY);
        if (habit.getDaysToComplete().contains(Days.FRIDAY)) days.add(Calendar.FRIDAY);
        if (habit.getDaysToComplete().contains(Days.SATURDAY)) days.add(Calendar.SATURDAY);

        return days;
    }

    private void onDelete() {
        createDeleteAlertDialog().show();
    }

    private AlertDialog createDeleteAlertDialog() {
        return new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to delete this habit?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        habitDetailsController.deleteHabit();
                        finish();
                    }
                })
                .create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        habitDetailsController.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        habitDetailsController.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.habit_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            onDelete();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
