package ca.antonious.habittracker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by George on 2016-09-02.
 */
public abstract class ArrayAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    private ItemClickedListener<T> itemClickedListener;

    private List<T> items = new ArrayList<>();

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public void onBindViewHolder(VH holder, int position) {
        holder.setOnClickListener(handleClick(get(position), position));
    }

    private View.OnClickListener handleClick(final T object, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchItemClicked(object, position);
            }
        };
    }

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

    public ItemClickedListener<T> getItemClickedListener() {
        return itemClickedListener;
    }

    public void setItemClickedListener(ItemClickedListener<T> itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }

    private void dispatchItemClicked(T object, int position) {
        if (getItemClickedListener() != null) {
            getItemClickedListener().onItemClicked(object, position);
        }
    }

    public interface ItemClickedListener<T> {
        void onItemClicked(T item, int position);
    }
}
