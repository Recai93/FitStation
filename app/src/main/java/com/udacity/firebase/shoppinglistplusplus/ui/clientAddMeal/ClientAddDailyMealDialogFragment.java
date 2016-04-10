package com.udacity.firebase.shoppinglistplusplus.ui.clientAddMeal;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.NumberPicker;

import com.udacity.firebase.shoppinglistplusplus.R;

public class ClientAddDailyMealDialogFragment extends DialogFragment {
    private NumberPicker numberPickerSetNo;
    private NumberPicker numberPickerRepNo;
    private static int position;

    public static ClientAddDailyMealDialogFragment newInstance(int position) {
        ClientAddDailyMealDialogFragment.position = position;
        ClientAddDailyMealDialogFragment addListDialogFragment = new ClientAddDailyMealDialogFragment();
        return addListDialogFragment;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_client_add_daily_meal_item, null);

        numberPickerSetNo = (NumberPicker) rootView.findViewById(R.id.number_picker_set_no);
        numberPickerRepNo = (NumberPicker) rootView.findViewById(R.id.number_picker_rep_no);

        numberPickerSetNo.setMinValue(1);
        numberPickerSetNo.setMaxValue(10);
        numberPickerSetNo.setValue(3);

        numberPickerRepNo.setMinValue(1);
        numberPickerRepNo.setMaxValue(50);
        numberPickerRepNo.setValue(25);

        builder.setView(rootView)
                .setPositiveButton(R.string.action_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        CreateDailyMealDialogListener activity = (CreateDailyMealDialogListener) getActivity();
                        activity.onFinishCreateDialog(numberPickerSetNo.getValue(), numberPickerRepNo.getValue(), position);
                        dismiss();
                    }
                });

        return builder.create();
    }

    public interface CreateDailyMealDialogListener {
        void onFinishCreateDialog(int setNo, int repNo, int position);
    }

}