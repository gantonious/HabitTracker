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
        parseAttributes(context, attrs);
    }

    private void inflateLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.option_preview, this, true);
    }

    private void bindViews() {
        labelTextView = (TextView) findViewById(R.id.option_label);
        previewTextView = (TextView) findViewById(R.id.option_preview);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OptionsPreviewView);

        parseLabelTextAttribute(typedArray);
        parsePreviewTextAttribute(typedArray);

        typedArray.recycle();
    }

    private void parseLabelTextAttribute(TypedArray typedArray) {
        String labelAttributeText = typedArray.getString(R.styleable.OptionsPreviewView_labelText);

        if (!StringUtils.isStringNullOrEmpty(labelAttributeText)) {
            labelTextView.setText(labelAttributeText);
        }
    }

    private void parsePreviewTextAttribute(TypedArray typedArray) {
        String previewAttributeText = typedArray.getString(R.styleable.OptionsPreviewView_previewText);

        if (!StringUtils.isStringNullOrEmpty(previewAttributeText)) {
            previewTextView.setText(previewAttributeText);
        }
    }

    public String getLabelText() {
        return labelTextView.getText().toString();
    }

    public void setLabelText(String text) {
        labelTextView.setText(text);
    }

    public String getPreviewText() {
        return previewTextView.getText().toString();
    }

    public void setPreviewText(String text) {
        previewTextView.setText(text);
    }
}
