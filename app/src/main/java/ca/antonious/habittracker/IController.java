package ca.antonious.habittracker;

/**
 * Created by George on 2016-09-03.
 */
public interface IController<T> {
    void attachView(T view);
    void detachView();
}
