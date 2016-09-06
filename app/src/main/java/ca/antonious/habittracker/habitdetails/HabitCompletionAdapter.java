package ca.antonious.habittracker.habitdetails;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.SimpleDateFormat;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseViewHolder;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.HabitCompletion;

/**
 * Created by George on 2016-09-02.
 */
public class HabitCompletionAdapter extends ArrayAdapter<HabitCompletion, HabitCompletionAdapter.ViewHolder> {
    private CompletionRemovedListener completionRemovedListener;

    public HabitCompletionAdapter() {
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_completion_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return get(position).getId().hashCode();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        HabitCompletion habitCompletion = get(position);

        holder.setCompletionDateDescription(getPrettyDateFromHabit(habitCompletion));
        holder.setCompletionTimeText(getPrettyTimeFromHabit(habitCompletion));
        holder.setOnDeleteClickedListener(generateOnDeleteListener(habitCompletion, position));
    }

    private View.OnClickListener generateOnDeleteListener(final HabitCompletion habitCompletion, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchCompletionRemovedListener(habitCompletion, position);
            }
        };
    }

    private String getPrettyDateFromHabit(HabitCompletion completion) {
        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return humanReadableDateFormat.format(completion.getCompletionTime());
    }

    private String getPrettyTimeFromHabit(HabitCompletion completion) {
        SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("hh:mm:ss");
        return humanReadableDateFormat.format(completion.getCompletionTime());
    }

    public static class ViewHolder extends BaseViewHolder {
        private TextView dateTextView;
        private ImageView deleteButton;

        public ViewHolder(View view) {
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

        @Override
        protected void onDetach() {
            super.onDetach();
            setOnDeleteClickedListener(null);
        }
    }

    public CompletionRemovedListener getCompletionRemovedListener() {
        return completionRemovedListener;
    }

    public void setCompletionRemovedListener(CompletionRemovedListener completionRemovedListener) {
        this.completionRemovedListener = completionRemovedListener;
    }

    private void dispatchCompletionRemovedListener(HabitCompletion habitCompletion, int position) {
        if (getCompletionRemovedListener() != null) {
            getCompletionRemovedListener().onCompletionRemoved(habitCompletion, position);
        }
    }

    public interface CompletionRemovedListener {
        void onCompletionRemoved(HabitCompletion habitCompletion, int position);
    }
}
