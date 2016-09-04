package ca.antonious.habittracker.habitlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseViewHolder;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-01.
 */
public class HabitAdapter extends ArrayAdapter<Habit, HabitAdapter.ViewHolder> {
    private HabitCheckedListener habitCheckedListener;

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
        super.onBindViewHolder(holder, position);

        Habit habit = get(position);
        holder.setTitle(habit.getName());
        holder.setOnCheckListener(handleCheck(habit));
    }

    private CheckBox.OnCheckedChangeListener handleCheck(final Habit habit) {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dispatchOnHabitChecked(habit, isChecked);
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return get(position).getId().hashCode();
    }

    public static class ViewHolder extends BaseViewHolder {
        private TextView title;
        private CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.habit_title);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setOnCheckListener(CheckBox.OnCheckedChangeListener onCheckListener) {
            checkBox.setOnCheckedChangeListener(onCheckListener);
        }

        @Override
        protected void onDetach() {
            checkBox.setOnCheckedChangeListener(null);
        }
    }

    public HabitCheckedListener getHabitCheckedListener() {
        return habitCheckedListener;
    }

    public void setHabitCheckedListener(HabitCheckedListener habitCheckedListener) {
        this.habitCheckedListener = habitCheckedListener;
    }

    private void dispatchOnHabitChecked(Habit habit, boolean isChecked) {
        if (getHabitCheckedListener() != null) {
            getHabitCheckedListener().onItemChecked(habit, isChecked);
        }
    }

    public interface HabitCheckedListener {
        void onItemChecked(Habit habit, boolean isChecked);
    }
}
