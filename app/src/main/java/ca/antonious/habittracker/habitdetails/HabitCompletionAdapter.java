package ca.antonious.habittracker.habitdetails;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import ca.antonious.habittracker.ArrayAdapter;
import ca.antonious.habittracker.BaseViewHolder;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.HabitCompletion;

/**
 * Created by George on 2016-09-02.
 */
public class HabitCompletionAdapter extends ArrayAdapter<HabitCompletion, HabitCompletionAdapter.ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_completion_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HabitCompletion habitCompletion = get(position);

        holder.setCompletionDateText(habitCompletion.getCompletionTime().toString());
        holder.setCompletionTimeText(habitCompletion.getCompletionTime().toString());
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
}
