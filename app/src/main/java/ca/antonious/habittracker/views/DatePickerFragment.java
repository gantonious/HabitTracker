package ca.antonious.habittracker.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by George on 2016-09-03.
 *
 * Used this tutorial: https://developer.android.com/guide/topics/ui/controls/pickers.html
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (dateSetListener != null) {
            dateSetListener.onDateSet(view, year, monthOfYear, dayOfMonth);
        }
    }

    public DatePickerDialog.OnDateSetListener getDateSetListener() {
        return dateSetListener;
    }

    public void setDateSetListener(DatePickerDialog.OnDateSetListener dateSetListener) {
        this.dateSetListener = dateSetListener;
    }
}
