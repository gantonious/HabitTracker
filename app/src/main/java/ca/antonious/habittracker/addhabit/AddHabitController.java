package ca.antonious.habittracker.addhabit;

import ca.antonious.habittracker.interactions.HabitInteractionsFactory;
import ca.antonious.habittracker.IController;

/**
 * Created by George on 2016-09-04.
 *
 * AddHabitController is an implementation of IController that controls
 * an IAddHabitView. It exposes a method to add a habit, and notifies the view
 * if the habit has been successfully added
 */
public class AddHabitController implements IController<IAddHabitView> {
    private IAddHabitView addHabitView;
    private HabitInteractionsFactory habitInteractionsFactory;

    public AddHabitController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void addHabit(AddHabitRequest addHabitRequest) {
        habitInteractionsFactory.addHabit().add(addHabitRequest.getName(), addHabitRequest.getStartDate(), addHabitRequest.getDaysOfTheWeek());
        dispatchOnHabitAdded();
    }

    @Override
    public void attachView(IAddHabitView view) {
        this.addHabitView = view;
    }

    @Override
    public void detachView() {
        this.addHabitView = null;
    }

    private void dispatchOnHabitAdded() {
        if (addHabitView != null) {
            addHabitView.onHabitAdded();
        }
    }
}
