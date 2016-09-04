package ca.antonious.habittracker.habitdetails;

import ca.antonious.habittracker.IController;

/**
 * Created by George on 2016-09-04.
 */
public class HabitDetailsController implements IController<IHabitDetailsView> {
    private IHabitDetailsView habitDetailsView;

    @Override
    public void attachView(IHabitDetailsView view) {
        this.habitDetailsView = view;
    }

    @Override
    public void detachView() {
        this.habitDetailsView = null;
    }
}
