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
import android.widget.Spinner;
import android.widget.Toast;

import com.udacity.firebase.shoppinglistplusplus.R;

public class ClientAddDailyMealDialogFragment extends DialogFragment {
    private static int position;
    private NumberPicker numberPickerPortion;
    private NumberPicker numberPickerPortion1;
    private Spinner spinnerMealType;

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

        numberPickerPortion = (NumberPicker) rootView.findViewById(R.id.number_picker_portion);
        numberPickerPortion1 = (NumberPicker) rootView.findViewById(R.id.number_picker_portion1);
        spinnerMealType = (Spinner) rootView.findViewById(R.id.spinner_meal_type);

        numberPickerPortion.setMinValue(1);
        numberPickerPortion.setMaxValue(20);
        numberPickerPortion.setValue(1);

        numberPickerPortion.setWrapSelectorWheel(false);

        numberPickerPortion1.setMinValue(0);
        numberPickerPortion1.setMaxValue(50);
        numberPickerPortion1.setValue(0);

        String nums[] = {"1/8", "1/4", "1/3", "1/2", "2/3"};

        numberPickerPortion1.setMaxValue(nums.length - 1);
        numberPickerPortion1.setMinValue(0);
        numberPickerPortion1.setWrapSelectorWheel(false);
        numberPickerPortion1.setDisplayedValues(nums);

        builder.setView(rootView)
                .setPositiveButton(R.string.action_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (spinnerMealType.getSelectedItemPosition() != 0) {
                            CreateDailyMealDialogListener activity = (CreateDailyMealDialogListener) getActivity();
                            activity.onFinishCreateDialog(position, numberPickerPortion.getValue(),
                                    numberPickerPortion1.getValue(), spinnerMealType.getSelectedItem().toString());
                            dismiss();
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.toast_empty_fields), Toast.LENGTH_LONG).show();
                        }
                    }
                });


        return builder.create();
    }

    public interface CreateDailyMealDialogListener {
        void onFinishCreateDialog(int position, int portion, int portion1, String mealType);
    }

}