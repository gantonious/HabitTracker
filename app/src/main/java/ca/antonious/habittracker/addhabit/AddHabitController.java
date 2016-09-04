package ca.antonious.habittracker.addhabit;

import ca.antonious.habittracker.IController;

/**
 * Created by George on 2016-09-04.
 */
public class AddHabitController implements IController<IAddHabitView> {
    private IAddHabitView addHabitView;

    @Override
    public void attachView(IAddHabitView view) {
        this.addHabitView = view;
    }

    @Override
    public void detachView() {
        this.addHabitView = null;
    }
}
