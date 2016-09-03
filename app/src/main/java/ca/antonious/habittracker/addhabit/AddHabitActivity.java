package ca.antonious.habittracker.addhabit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.antonious.habittracker.AndroidFileHandler;
import ca.antonious.habittracker.FileHandler;
import ca.antonious.habittracker.HabbitService;
import ca.antonious.habittracker.IHabitService;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;

public class AddHabitActivity extends AppCompatActivity {
    private Button addButton;
    private EditText habitTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        addButton = (Button) findViewById(R.id.add_habit);
        habitTitle = (EditText) findViewById(R.id.new_habit_title);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd();
                finish();
            }
        });
    }

    private void onAdd() {
        Habit habit = new Habit();
        habit.setName(habitTitle.getText().toString());

        FileHandler fileHandler = new AndroidFileHandler(this);
        IHabitService habitService = new HabbitService(fileHandler);

        habitService.addHabit(habit);
    }

}
