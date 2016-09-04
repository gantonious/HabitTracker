package ca.antonious.habittracker.habitdetails;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;

import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseViewHolder;
import ca.antonious.habittracker.CompleteHabit;
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

        holder.setCompletionDateText(getPrettyDateFromHabit(habitCompletion));
        holder.setCompletionTimeText(getPrettyTimeFromHabit(habitCompletion));
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
        private TextView timeTextView;

        public ViewHolder(View view) {
            super(view);

            dateTextView = (TextView) view.findViewById(R.id.completion_date);
            timeTextView = (TextView) view.findViewById(R.id.completion_time);
        }

        public void setCompletionDateText(String completionDateText) {
            dateTextView.setText(completionDateText);
        }

        public void setCompletionTimeText(String completionTimeText) {
            timeTextView.setText(completionTimeText);
        }
    }

    public CompletionRemovedListener getCompletionRemovedListener() {
        return completionRemovedListener;
    }

    public void setCompletionRemovedListener(CompletionRemovedListener completionRemovedListener) {
        this.completionRemovedListener = completionRemovedListener;
    }

    private void dispatchCompletionRemovedListener(HabitCompletion habitCompletion) {
        if (getCompletionRemovedListener() != null) {
            getCompletionRemovedListener().onCompletionRemoved(habitCompletion);
        }
    }

    public interface CompletionRemovedListener {
        void onCompletionRemoved(HabitCompletion habitCompletion);
    }
}
