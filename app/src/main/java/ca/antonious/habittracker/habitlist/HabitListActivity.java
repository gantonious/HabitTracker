package ca.antonious.habittracker.habitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.addhabit.AddHabitActivity;
import ca.antonious.habittracker.habitdetails.HabitDetailsActivity;
import ca.antonious.habittracker.models.Habit;

public class HabitListActivity extends BaseActivity implements IHabitListView {
    private FloatingActionButton fab;
    private RecyclerView habitRecyclerView;
    private HabitAdapter habitAdapter = new HabitAdapter();

    private HabitListController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindViews();
        resolveDependencies();
        setUpRecyclerView();

        handleAddButtonClicks();
        handleListScrolling();
        handleListItemClicks();
        handleCompleteClicks();
    }

    private void handleCompleteClicks() {
        habitAdapter.setOnCompleteClickedListener(new HabitAdapter.OnCompleteClickedListener() {
            @Override
            public void onComplete(Habit habit, int position) {
                controller.markHabitAsCompleted(habit.getId());
            }
        });
    }

    private void bindViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        habitRecyclerView = (RecyclerView) findViewById(R.id.habit_recycler_view);
    }

    private void resolveDependencies() {
        controller = getHabitTrackerApplication().getHabitListController();
    }

    private void setUpRecyclerView() {
        habitRecyclerView.setAdapter(habitAdapter);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void handleAddButtonClicks() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HabitListActivity.this, AddHabitActivity.class));
            }
        });
    }

    private void handleListScrolling() {
        habitRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 10) {
                    fab.hide();
                } else if (dy < 0) {
                    fab.show();
                }
            }
        });
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
        Intent intent = new Intent(HabitListActivity.this, HabitDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_HABIT_ID, habitID);

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.detachView();
    }

    @Override
    public void displayHabits(List<Habit> habit) {
        habitAdapter.setAll(habit);
        habitAdapter.notifyDataSetChanged();
    }
}
