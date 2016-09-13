package ca.antonious.habittracker.addhabit;

import ca.antonious.habittracker.HabitInteractionsFactory;
import ca.antonious.habittracker.IController;
import ca.antonious.habittracker.IHabitRepository;

/**
 * Created by George on 2016-09-04.
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
