package ca.antonious.habittracker.todayshabitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ca.antonious.habittracker.BaseFragment;
import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.habitdetails.HabitDetailsActivity;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.viewcells.EmptyViewCell;
import ca.antonious.habittracker.viewcells.HabitViewCell;
import ca.antonious.viewcelladapter.ViewCellAdapter;
import ca.antonious.viewcelladapter.construction.SectionBuilder;
import ca.antonious.viewcelladapter.sections.HomogeneousSection;

/**
 * Created by George on 2016-09-30.
 */

public class TodaysHabitsFragment extends BaseFragment implements ITodaysHabitsView {
    private RecyclerView habitRecyclerView;

    private HomogeneousSection<Habit, HabitViewCell> habitsSection;
    private TodaysHabitsController controller;

    public static TodaysHabitsFragment newInstance() {
        return new TodaysHabitsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_todays_habits, container, false);

        bindViews(view);
        resolveDependencies();

        habitRecyclerView.setAdapter(getViewCellAdapter());
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void bindViews(View view) {
        habitRecyclerView = (RecyclerView) view.findViewById(R.id.habit_recycler_view);
    }

    private void resolveDependencies() {
        controller = getHabitTrackerApplication().getTodaysHabitsController();
    }

    private ViewCellAdapter getViewCellAdapter() {
        habitsSection = new HomogeneousSection<>(Habit.class, HabitViewCell.class);

        return ViewCellAdapter.create()
            .section(
                SectionBuilder.wrap(habitsSection)
                    .showIfEmpty(new EmptyViewCell("Seems like you have no habits for today"))
                    .build()
            )
            .listener(new HabitViewCell.OnHabitClickedListener() {
                @Override
                public void onHabitClicked(Habit habit, int position) {
                    navigateToDetails(habit.getId());
                }
            })
            .listener(new HabitViewCell.OnCompleteClickedListener() {
                @Override
                public void onComplete(Habit habit, int position) {
                    controller.markHabitAsCompleted(habit.getId());
                }
            })
            .build();
    }

    private void navigateToDetails(String habitID) {
        Intent intent = new Intent(getActivity(), HabitDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_HABIT_ID, habitID);

        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.detachView();
    }

    @Override
    public void displayTodaysHabits(List<Habit> habits) {
        habitsSection.setAll(habits);
    }
}
