package ca.antonious.habittracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import ca.antonious.habittracker.Utils.StringUtils;

/**
 * Created by George on 2016-09-03.
 */
public class OptionPreviewView extends LinearLayout {
    protected TextView labelTextView;
    protected TextView previewTextView;

    public OptionPreviewView(Context context) {
        this(context, null);
    }

    public OptionPreviewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OptionPreviewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflateLayout(context);
        bindViews();
        handleAttributes(context, attrs);
    }

    private void inflateLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.option_preview, this, true);
    }

    private void bindViews() {
        labelTextView = (TextView) findViewById(R.id.option_label);
        previewTextView = (TextView) findViewById(R.id.option_preview);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OptionsPreviewView);

        String labelAttributeText = a.getString(R.styleable.OptionsPreviewView_labelText);
        String previewAttributeText = a.getString(R.styleable.OptionsPreviewView_previewText);

        if (!StringUtils.isStringNullOrEmpty(labelAttributeText)) {
            labelTextView.setText(labelAttributeText);
        }

        if(!StringUtils.isStringNullOrEmpty(previewAttributeText)) {
            previewTextView.setText(previewAttributeText);
        }

        a.recycle();
    }
}
