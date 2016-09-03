package ca.antonious.habittracker;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by George on 2016-09-03.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View.OnClickListener onClickListener;

    public BaseViewHolder(View view) {
        super(view);
    }

    protected void onDetach() {
        onClickListener = null;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
