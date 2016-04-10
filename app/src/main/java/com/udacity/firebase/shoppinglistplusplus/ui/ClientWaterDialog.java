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

    NumberPicker numberPicker;

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

        numberPicker = (NumberPicker) rootView.findViewById(R.id.numberPicker);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(9);
        numberPicker.setWrapSelectorWheel(false);

        builder.setView(rootView)
                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ClientWaterDialogListener activity = (ClientWaterDialogListener) getActivity();
                        activity.onFinish(numberPicker.getValue());
                        dismiss();
                    }
                });

        return builder.create();
    }

    public interface ClientWaterDialogListener {
        void onFinish(long quantity);
    }

}