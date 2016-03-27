package com.udacity.firebase.shoppinglistplusplus.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;

import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

public class TipsDialogFragment extends DialogFragment {

    public static TipsDialogFragment newInstance() {
        TipsDialogFragment tipsDialogFragment = new TipsDialogFragment();
        return tipsDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("İpucu");
        alertDialogBuilder.setMessage(Utils.getTip());
        alertDialogBuilder.setPositiveButton("Tamam",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return alertDialogBuilder.create();
    }

}
