package ca.antonious.habittracker;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by George on 2016-09-02.
 */
public abstract class ArrayAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> items = new ArrayList<>();

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);
    public abstract void onBindViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onViewDetachedFromWindow(BaseViewHolder holder) {
        holder.onDetach();
    }

    public T get(int position) {
        return items.get(position);
    }

    public void clear() {
        items.clear();
    }

    public void add(T object) {
        items.add(object);
    }

    public void addAll(Collection<? extends T> collection) {
        items.addAll(collection);
    }

    public void setAll(Collection<? extends T> collection) {
        items.clear();
        items.addAll(collection);
    }
}
