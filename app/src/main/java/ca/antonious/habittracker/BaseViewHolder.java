package ca.antonious.habittracker;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by George on 2016-09-03.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View view) {
        super(view);
    }

    protected void onDetach() {
        itemView.setOnClickListener(null);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }
}
