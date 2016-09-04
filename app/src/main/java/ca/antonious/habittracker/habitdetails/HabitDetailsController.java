package ca.antonious.habittracker.habitdetails;

import java.util.List;

import ca.antonious.habittracker.HabitInteractionsFactory;
import ca.antonious.habittracker.IController;
import ca.antonious.habittracker.IHabitRepository;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObserver;

/**
 * Created by George on 2016-09-04.
 */
public class HabitDetailsController implements IController<IHabitDetailsView> {
    private String habitId;
    private IHabitDetailsView habitDetailsView;
    private IHabitRepository habitRepository;
    private HabitInteractionsFactory habitInteractionsFactory;

    public HabitDetailsController(IHabitRepository habitRepository, HabitInteractionsFactory habitInteractionsFactory, String habitId) {
        this.habitId = habitId;
        this.habitRepository = habitRepository;
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void deleteHabit() {
        habitInteractionsFactory.deleteHabit().delete(habitId);
    }

    public void markHabitAsComplete() {
        habitInteractionsFactory.completeHabit().complete(habitId);
    }

    public void removeHabitCompletion(String completionId) {
        habitInteractionsFactory.removeCompletion().remove(habitId, completionId);
    }

    private IObserver<List<Habit>> habitListObserver = new IObserver<List<Habit>>() {
        @Override
        public void onNext(List<Habit> next) {
            Habit newHabit = getHabitFromHabitList(next);
            if(newHabit != null) {
                dispatchDisplayHabit(newHabit);
            }

        }
    };

    private Habit getHabitFromHabitList(List<Habit> habits) {
        for (Habit habit: habits) {
            if (habitId.equals(habit.getId())) {
                return habit;
            }
        }
        return null;
    }

    @Override
    public void attachView(IHabitDetailsView view) {
        this.habitDetailsView = view;
        this.habitRepository.addObserver(habitListObserver);
    }

    @Override
    public void detachView() {
        this.habitDetailsView = null;
        this.habitRepository.removeObserver(habitListObserver);
    }

    private void dispatchDisplayHabit(Habit habit) {
        if (habitDetailsView != null) {
            habitDetailsView.displayHabit(habit);
        }
    }
}