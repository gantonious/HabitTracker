package ca.antonious.habittracker.viewcells;

import android.view.View;
import android.widget.TextView;

import ca.antonious.habittracker.R;
import ca.antonious.viewcelladapter.viewcells.BaseViewHolder;
import ca.antonious.viewcelladapter.viewcells.GenericViewCell;

/**
 * Created by George on 2017-02-27.
 */

public class HeaderViewCell extends GenericViewCell<HeaderViewCell.HeaderViewHolder, String> {
    public HeaderViewCell(String s) {
        super(s);
    }

    @Override
    public int getLayoutId() {
        return R.layout.header_item;
    }

    @Override
    public void bindViewCell(HeaderViewHolder viewHolder) {
        viewHolder.setHeaderText(getModel());
    }

    public static class HeaderViewHolder extends BaseViewHolder {
        private TextView headerTextView;

        public HeaderViewHolder(View view) {
            super(view);

            headerTextView = (TextView) view.findViewById(R.id.header_textview);
        }

        public void setHeaderText(String header) {
            headerTextView.setText(header);
        }
    }
}
