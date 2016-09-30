package ca.antonious.habittracker;

/**
 * Created by George on 2016-09-03.
 *
 * An IController meditates data flow between the Model and the View,
 * it interprets view events and interacts with the model accordingly, then
 * listens to model events and notifies the view when something important happens
 *
 * This interface exposes methods to attach/detach a view to an IController
 */
public interface IController<T> {
    void attachView(T view);
    void detachView();
}
