package ca.antonious.habittracker.observable;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2016-09-14.
 */
public class TestObserver<T> implements IObserver<T> {
    private List<T> itemsEmitted = new ArrayList<>();

    @Override
    public void onNext(T next) {
        itemsEmitted.add(next);
    }

    public void assertItemEmitted(T item) {
        Assert.assertTrue(itemsEmitted.contains(item));
    }

    public void assertTotalCompletions(int total) {
        Assert.assertEquals(itemsEmitted.size(), total);
    }
}
