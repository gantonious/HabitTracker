package ca.antonious.habittracker.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

/**
 * Created by George on 2016-09-03.
 */
public class EditTextFragment extends DialogFragment {
    private EditText editText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("Name")
                          .setView(new EditText(getActivity()))
                          .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {

                              }
                          })
                          .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                          });


        return alertDialogBuilder.show();
    }

    private EditText ensureEditText() {
        if (editText == null) {
            editText = new EditText(getActivity());
        }
        return editText;
    }

}
