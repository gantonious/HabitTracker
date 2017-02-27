package ca.antonious.habittracker.viewcells;

import android.view.View;
import android.widget.TextView;

import ca.antonious.habittracker.R;
import ca.antonious.viewcelladapter.viewcells.BaseViewHolder;
import ca.antonious.viewcelladapter.viewcells.GenericViewCell;

/**
 * Created by George on 2017-02-27.
 */

public class EmptyViewCell extends GenericViewCell<EmptyViewCell.EmptyViewHolder, String> {
    public EmptyViewCell(String s) {
        super(s);
    }

    @Override
    public int getLayoutId() {
        return R.layout.full_screen_empty_view;
    }

    @Override
    public void bindViewCell(EmptyViewHolder viewHolder) {
        viewHolder.setEmptyText(getModel());
    }

    public static class EmptyViewHolder extends BaseViewHolder {
        private TextView emptyTextView;

        public EmptyViewHolder(View view) {
            super(view);

            emptyTextView = (TextView) view.findViewById(R.id.empty_message);
        }

        public void setEmptyText(String empty) {
            emptyTextView.setText(empty);
        }
    }
}
