package ca.antonious.habittracker.todayshabitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseFragment;
import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.habitdetails.HabitDetailsActivity;
import ca.antonious.habittracker.habitlist.HabitAdapter;
import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-30.
 */

public class TodaysHabitsFragment extends BaseFragment implements ITodaysHabitsView {
    private TextView emptyHabitsTextView;
    private RecyclerView habitRecyclerView;
    private HabitAdapter habitAdapter = new HabitAdapter();

    private TodaysHabitsController controller;

    public static TodaysHabitsFragment newInstance() {
        return new TodaysHabitsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_todays_habits, container, false);

        bindViews(view);
        resolveDependencies();
        setUpRecyclerView();

        handleAddButtonClicks();
        handleListItemClicks();
        handleCompleteClicks();

        return view;
    }

    private void handleCompleteClicks() {
        habitAdapter.setOnCompleteClickedListener(new HabitAdapter.OnCompleteClickedListener() {
            @Override
            public void onComplete(Habit habit, int position) {
                controller.markHabitAsCompleted(habit.getId());
            }
        });
    }

    private void bindViews(View view) {
        //addHabitButton = (FloatingActionButton) findViewById(R.id.fab);
        emptyHabitsTextView = (TextView) view.findViewById(R.id.empty_habits_view);
        habitRecyclerView = (RecyclerView) view.findViewById(R.id.habit_recycler_view);
    }

    private void resolveDependencies() {
        controller = getHabitTrackerApplication().getTodaysHabitsController();
    }

    private void setUpRecyclerView() {
        habitRecyclerView.setAdapter(habitAdapter);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void handleAddButtonClicks() {
        /*addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HabitListActivity.this, AddHabitActivity.class));
            }
        });*/
    }

    private void handleListItemClicks() {
        habitAdapter.setItemClickedListener(new ArrayAdapter.ItemClickedListener<Habit>() {
            @Override
            public void onItemClicked(Habit item, int position) {
                navigateToDetails(item.getId());
            }
        });
    }

    private void navigateToDetails(String habitID) {
        Intent intent = new Intent(getActivity(), HabitDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_HABIT_ID, habitID);

        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.detachView();
    }

    @Override
    public void displayTodaysHabits(List<Habit> habits) {
        if (habits.isEmpty()) {
            displayNoHabitsMessage();
        } else {
            displayHabitsList(habits);
        }
    }

    private void displayNoHabitsMessage() {
        habitAdapter.clear();
        habitAdapter.notifyDataSetChanged();

        emptyHabitsTextView.setVisibility(View.VISIBLE);
    }

    private void displayHabitsList(List<Habit> habits) {
        emptyHabitsTextView.setVisibility(View.GONE);

        habitAdapter.setAll(habits);
        habitAdapter.notifyDataSetChanged();
    }
}
