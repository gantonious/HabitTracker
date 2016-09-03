package ca.antonious.habittracker.observable;

/**
 * Created by George on 2016-09-02.
 */
public interface IObserver<T> {
    void onNext(T next);
}
