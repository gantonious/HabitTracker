package ca.antonious.habittracker.habitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.HabitRepository;
import ca.antonious.habittracker.IHabitRepository;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.addhabit.AddHabitActivity;
import ca.antonious.habittracker.habitdetails.HabitDetailsActivity;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObserver;

public class HabitListActivity extends BaseActivity {
    private RecyclerView habitRecyclerView;
    private HabitAdapter habitAdapter = new HabitAdapter();

    private FloatingActionButton fab;

    private IHabitRepository habitRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        habitRepository = getHabitTrackerApplication().getHabitRepository();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HabitListActivity.this, AddHabitActivity.class));
            }
        });

        habitRecyclerView = (RecyclerView) findViewById(R.id.habit_recycler_view);
        habitRecyclerView.setAdapter(habitAdapter);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

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

        habitAdapter.setItemClickedListener(new ArrayAdapter.ItemClickedListener<Habit>() {
            @Override
            public void onItemClicked(Habit item, int position) {
                Intent intent = new Intent(HabitListActivity.this, HabitDetailsActivity.class);
                intent.putExtra(Constants.EXTRA_HABIT_ID, item.getId());

                startActivity(intent);
            }
        });

        habitRepository.addObserver(new IObserver<List<Habit>>() {
            @Override
            public void onNext(List<Habit> next) {
                habitAdapter.clear();
                habitAdapter.addAll(next);
                habitAdapter.notifyDataSetChanged();
            }
        });
    }
}
