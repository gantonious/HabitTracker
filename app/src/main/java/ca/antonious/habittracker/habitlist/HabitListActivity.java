package ca.antonious.habittracker.habitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ca.antonious.habittracker.AndroidFileHandler;
import ca.antonious.habittracker.FileHandler;
import ca.antonious.habittracker.HabbitService;
import ca.antonious.habittracker.HabitAdapter;
import ca.antonious.habittracker.IHabitService;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.addhabit.AddHabitActivity;

public class HabitListActivity extends AppCompatActivity {
    private RecyclerView habitRecyclerView;
    private HabitAdapter habitAdapter = new HabitAdapter();

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHabits();
    }

    private void loadHabits() {
        FileHandler fileHandler = new AndroidFileHandler(this);
        IHabitService habitService = new HabbitService(fileHandler);

        habitAdapter.setHabits(habitService.getHabits());
        habitAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
