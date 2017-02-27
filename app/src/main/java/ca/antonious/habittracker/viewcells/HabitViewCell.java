package ca.antonious.habittracker.viewcells;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import ca.antonious.habittracker.DaysToDescriptionMapper;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.viewcelladapter.annotations.BindListener;
import ca.antonious.viewcelladapter.viewcells.BaseViewHolder;
import ca.antonious.viewcelladapter.viewcells.GenericViewCell;

/**
 * Created by George on 2017-02-27.
 */

public class HabitViewCell extends GenericViewCell<HabitViewCell.HabitViewHolder, Habit> {

    public HabitViewCell(Habit habit) {
        super(habit);
    }

    @Override
    public int getLayoutId() {
        return R.layout.habit_list_item;
    }

    @Override
    public void bindViewCell(HabitViewHolder viewHolder) {
        Habit habit = getModel();

        if (habit.hasBeenCompletedOnDay(new Date())) {
            viewHolder.setCompleted();
        } else {
            viewHolder.setNotCompleted();
        }

        viewHolder.setTitle(habit.getName());
        viewHolder.setDatesDescription(new DaysToDescriptionMapper().map(habit.getDaysToComplete()));
    }

    public interface OnHabitClickedListener {
        void onHabitClicked(Habit habit, int position);
    }

    public interface OnCompleteClickedListener {
        void onComplete(Habit habit, int position);
    }

    @BindListener
    public void bindOnHabitClickedListener(final HabitViewHolder viewHolder, final OnHabitClickedListener listener) {
        viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHabitClicked(getModel(), viewHolder.getLayoutPosition());
            }
        });
    }

    @BindListener
    public void bindOnCompleteClickedListener(final HabitViewHolder viewHolder, final OnCompleteClickedListener listener) {
        viewHolder.setOnCompleteClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onComplete(getModel(), viewHolder.getAdapterPosition());
            }
        });
    }

    public static class HabitViewHolder extends BaseViewHolder {
        private TextView title;
        private TextView dateToRepeat;
        private Button completeButton;
        private View completionIndicator;

        public HabitViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.habit_title);
            dateToRepeat = (TextView) view.findViewById(R.id.habit_dates);
            completeButton = (Button) view.findViewById(R.id.complete_button);
            completionIndicator = view.findViewById(R.id.completion_indicator);
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

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

        public void setCompleted() {
            completionIndicator.setVisibility(View.VISIBLE);
        }

        public void setNotCompleted() {
            completionIndicator.setVisibility(View.GONE);
        }
    }
}
