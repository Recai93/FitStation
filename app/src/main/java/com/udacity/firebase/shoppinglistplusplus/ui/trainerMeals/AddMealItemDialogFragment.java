package com.udacity.firebase.shoppinglistplusplus.ui.trainerMeals;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.udacity.firebase.shoppinglistplusplus.R;

public class AddMealItemDialogFragment extends DialogFragment {
    EditText mEditTextMealItem;

    public interface EditNameDialogListener {
        void onFinishEditDialog(String mealItem);
    }

    public static AddMealItemDialogFragment newInstance() {
        AddMealItemDialogFragment addListDialogFragment = new AddMealItemDialogFragment();
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
        View rootView = inflater.inflate(R.layout.dialog_add_meal_item, null);
        mEditTextMealItem = (EditText) rootView.findViewById(R.id.edit_text_meal_item_name);

        mEditTextMealItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                    activity.onFinishEditDialog(mEditTextMealItem.getText().toString());
                    dismiss();
                    AddMealItemDialogFragment.this.getDialog().cancel();
                }
                return true;
            }
        });

        builder.setView(rootView)
                .setPositiveButton(R.string.action_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                        activity.onFinishEditDialog(mEditTextMealItem.getText().toString());
                        dismiss();
                    }
                });

        return builder.create();
    }

}
