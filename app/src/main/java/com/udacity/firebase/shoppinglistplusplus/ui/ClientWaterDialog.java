package com.udacity.firebase.shoppinglistplusplus.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.udacity.firebase.shoppinglistplusplus.R;

public class ClientWaterDialog extends DialogFragment {

    NumberPicker np;

    public static ClientWaterDialog newInstance() {
        ClientWaterDialog addDailyWaterDialogFragment = new ClientWaterDialog();
        Bundle bundle = new Bundle();
        addDailyWaterDialogFragment.setArguments(bundle);
        return addDailyWaterDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_water, null);
        np = (NumberPicker) rootView.findViewById(R.id.numberPicker);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        np.setMinValue(0);
        np.setMaxValue(9);
        np.setWrapSelectorWheel(false);

        builder.setView(rootView)
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

}