package ca.antonious.habittracker.habitlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseViewHolder;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-01.
 */
public class HabitAdapter extends ArrayAdapter<Habit, HabitAdapter.ViewHolder> {
    public HabitAdapter() {
        setHasStableIds(true);
    }

    @Override
    public HabitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HabitAdapter.ViewHolder holder, int position) {
        Habit habit = get(position);

        holder.setTitle(habit.getName());
    }

    @Override
    public long getItemId(int position) {
        return get(position).getId().hashCode();
    }

    public static class ViewHolder extends BaseViewHolder {
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
