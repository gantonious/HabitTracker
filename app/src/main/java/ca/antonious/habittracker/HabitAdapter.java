package ca.antonious.habittracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-01.
 */
public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder> {
    private List<Habit> habits = new ArrayList<>();

    public HabitAdapter() {
        setHasStableIds(true);
    }

    public void setHabits(List<? extends Habit> habits) {
        this.habits.clear();
        this.habits.addAll(habits);
    }

    public Habit getHabit(int position) {
        return habits.get(position);
    }

    @Override
    public HabitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HabitAdapter.ViewHolder holder, int position) {
        Habit habit = getHabit(position);

        holder.setTitle(habit.getName());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    @Override
    public long getItemId(int position) {
        return getHabit(position).getId().hashCode();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.habit_title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }
    }
}
