package ca.antonious.habittracker.habitlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseViewHolder;
import ca.antonious.habittracker.DaysToDescriptionMapper;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;

/**
 * Created by George on 2016-09-01.
 */
public class HabitAdapter extends ArrayAdapter<Habit, HabitAdapter.ViewHolder> {
    private OnCompleteClickedListener onCompleteClickedListener;

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

        holder.setOnCheckListener(null);

        if (hasHabitBeenCompletedRecently(habit)) {
            holder.setCompleted();
        } else {
            holder.setNotCompleted();
        }

        holder.setTitle(habit.getName());
        holder.setDatesDescription(new DaysToDescriptionMapper().map(habit.getDaysToComplete()));
        holder.setOnCompleteClickedListener(handleCompletionClick(habit, position));
    }

    private boolean hasHabitBeenCompletedRecently(Habit habit) {
        boolean hasBeenCompletedRecently = false;

        for (HabitCompletion habitCompletion: habit.getCompletions()) {

            if (isDateWithinADay(habitCompletion.getCompletionTime())) {
                hasBeenCompletedRecently = true;
                break;
            }
        }

        return hasBeenCompletedRecently;
    }

    private boolean isDateWithinADay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);

        return date.compareTo(calendar.getTime()) > 0;
    }

    private View.OnClickListener handleCompletionClick(final Habit habit, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchOnCompleteEvent(habit, position);
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return get(position).getId().hashCode();
    }

    public static class ViewHolder extends BaseViewHolder {
        private TextView title;
        private TextView dateToRepeat;
        private Button completeButton;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.habit_title);
            dateToRepeat = (TextView) view.findViewById(R.id.habit_dates);
            completeButton = (Button) view.findViewById(R.id.complete_button);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setDatesDescription(String description) {
            dateToRepeat.setText(description);
        }

        public void setOnCompleteClickedListener(View.OnClickListener onClickListener) {
            completeButton.setOnClickListener(onClickListener);
        }

        public void setOnCheckListener(CheckBox.OnCheckedChangeListener onCheckListener) {

        }

        public void setCompleted() {

        }

        public void setNotCompleted() {

        }

        @Override
        protected void onDetach() {
            super.onDetach();
            completeButton.setOnClickListener(null);
        }
    }

    public OnCompleteClickedListener getOnCompleteClickedListener() {
        return onCompleteClickedListener;
    }

    public void setOnCompleteClickedListener(OnCompleteClickedListener onCompleteClickedListener) {
        this.onCompleteClickedListener = onCompleteClickedListener;
    }

    private void dispatchOnCompleteEvent(Habit completedHabit, int position) {
        if (onCompleteClickedListener != null) {
            onCompleteClickedListener.onComplete(completedHabit, position);
        }
    }

    public interface OnCompleteClickedListener {
        void onComplete(Habit habit, int position);
    }
}
