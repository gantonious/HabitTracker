package ca.antonious.habittracker.habitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.antonious.habittracker.BaseFragment;
import ca.antonious.habittracker.Constants;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.habitdetails.HabitDetailsActivity;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.viewcells.EmptyViewCell;
import ca.antonious.habittracker.viewcells.HabitViewCell;
import ca.antonious.viewcelladapter.ViewCellAdapter;
import ca.antonious.viewcelladapter.decorators.EmptySectionDecorator;
import ca.antonious.viewcelladapter.sections.HomogeneousSection;

/**
 * Created by George on 2016-09-30.
 */

public class HabitListFragment extends BaseFragment implements IHabitListView {
    private RecyclerView habitRecyclerView;

    private HomogeneousSection<Habit, HabitViewCell> habitsSection;
    private ViewCellAdapter viewCellAdapter;

    private HabitListController controller;
    public static HabitListFragment newInstance() {
        return new HabitListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_all_habits, container, false);

        bindViews(view);
        resolveDependencies();
        setUpRecyclerView();

        return view;
    }

    private void bindViews(View view) {
        habitRecyclerView = (RecyclerView) view.findViewById(R.id.habit_recycler_view);
    }

    private void resolveDependencies() {
        controller = getHabitTrackerApplication().getHabitListController();
    }

    private void setUpRecyclerView() {
        habitsSection = new HomogeneousSection<>(Habit.class, HabitViewCell.class);

        EmptyViewCell emptyViewCell = new EmptyViewCell("Seems like you have no habits being tracked");
        EmptySectionDecorator habitsSectionWithEmptyView = new EmptySectionDecorator(habitsSection, emptyViewCell);

        viewCellAdapter = new ViewCellAdapter();
        viewCellAdapter.add(habitsSectionWithEmptyView);

        viewCellAdapter.addListener(new HabitViewCell.OnHabitClickedListener() {
            @Override
            public void onHabitClicked(Habit habit, int position) {
                navigateToDetails(habit.getId());
            }
        });

        viewCellAdapter.addListener(new HabitViewCell.OnCompleteClickedListener() {
            @Override
            public void onComplete(Habit habit, int position) {
                controller.markHabitAsCompleted(habit.getId());
            }
        });

        habitRecyclerView.setAdapter(viewCellAdapter);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    public void displayHabits(List<Habit> habits) {
        habitsSection.setAll(habits);
        viewCellAdapter.notifyDataSetChanged();
    }
}
