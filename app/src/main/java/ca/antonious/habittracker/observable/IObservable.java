package ca.antonious.habittracker.observable;

/**
 * Created by George on 2016-09-02.
 */
public interface IObservable<T> {
    void addObserver(IObserver<T> observer);
    void removeObserver(IObserver<T> observer);
    void removeAllObservers();
}
