package ca.antonious.habittracker.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import ca.antonious.habittracker.R;

/**
 * Created by George on 2016-09-03.
 */
public class EditTextFragment extends DialogFragment {
    private EditText editText;
    private OnConfirmListener onConfirmListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.set_text_dialog, null);
        editText = (EditText) view.findViewById(R.id.text_input);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
            .setTitle("Name")
            .setView(view)
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dispatchOnConfirm(editText.getText().toString());
                }
            });

        return alertDialogBuilder.show();
    }

    public OnConfirmListener getOnConfirmListener() {
        return onConfirmListener;
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public void dispatchOnConfirm(String text) {
        if (getOnConfirmListener() != null) {
            getOnConfirmListener().onConfirm(text);
        }
    }

    public interface OnConfirmListener {
        void onConfirm(String text);
    }

}
