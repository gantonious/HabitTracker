package ca.antonious.habittracker.viewcells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.HabitCompletion;
import ca.antonious.viewcelladapter.annotations.BindListener;
import ca.antonious.viewcelladapter.viewcells.BaseViewHolder;
import ca.antonious.viewcelladapter.viewcells.GenericViewCell;

/**
 * Created by George on 2017-02-27.
 */

public class HabitCompletionViewCell extends GenericViewCell<HabitCompletionViewCell.HabitCompletionViewHolder, HabitCompletion> {
    public HabitCompletionViewCell(HabitCompletion habitCompletion) {
        super(habitCompletion);
    }

    @Override
    public int getLayoutId() {
        return R.layout.habit_completion_list_item;
    }

    @Override
    public void bindViewCell(HabitCompletionViewHolder viewHolder) {
        HabitCompletion habitCompletion = getModel();

        viewHolder.setCompletionDateDescription(getPrettyDateFromHabit(habitCompletion));
        viewHolder.setCompletionTimeText(getPrettyTimeFromHabit(habitCompletion));
    }

    private String getPrettyDateFromHabit(HabitCompletion completion) {
        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a");
        return humanReadableDateFormat.format(completion.getCompletionTime());
    }

    private String getPrettyTimeFromHabit(HabitCompletion completion) {
        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("hh:mm:ss");
        return humanReadableDateFormat.format(completion.getCompletionTime());
    }

    public interface OnCompletionRemovedListener {
        void onCompletionRemoved(HabitCompletion habitCompletion, int position);
    }

    @BindListener
    public void bindOnCompletionRemovedListener(final HabitCompletionViewHolder viewHolder, final OnCompletionRemovedListener listener) {
        viewHolder.setOnDeleteClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCompletionRemoved(getModel(), viewHolder.getAdapterPosition());
            }
        });
    }

    public static class HabitCompletionViewHolder extends BaseViewHolder {
        private TextView dateTextView;
        private ImageView deleteButton;

        public HabitCompletionViewHolder(View view) {
            super(view);

            dateTextView = (TextView) view.findViewById(R.id.completion_date);
            deleteButton = (ImageView) view.findViewById(R.id.delete_completion_icon);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setCompletionDateDescription(String description) {
            dateTextView.setText(description);
        }

        public void setCompletionTimeText(String completionTimeText) {

        }

        public void setOnDeleteClickedListener(View.OnClickListener onDeleteClickedListener) {
            deleteButton.setOnClickListener(onDeleteClickedListener);
        }
    }

}
