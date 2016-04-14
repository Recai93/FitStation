package com.udacity.firebase.shoppinglistplusplus.ui.trainerWorkouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;

public class TrainerWorkoutAdapter extends ArrayAdapter<Workout> {
    private TextView tvWorkoutName;
    private TextView tvWorkoutSetNo;
    private TextView tvWorkoutRepNo;
    private ImageView imageButton;

    public TrainerWorkoutAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.single_trainer_workout_item, parent, false);
        }
        Workout item = getItem(position);

        tvWorkoutName = (TextView) convertView.findViewById(R.id.tv_workout_name);
        tvWorkoutSetNo = (TextView) convertView.findViewById(R.id.tv_workout_set_no);
        tvWorkoutRepNo = (TextView) convertView.findViewById(R.id.tv_workout_rep_no);
        imageButton = (ImageView) convertView.findViewById(R.id.iv_toggle_selected);
        imageButton.setVisibility(View.GONE);

        tvWorkoutName.setText(item.getName());
        tvWorkoutRepNo.setText(String.valueOf(item.getRepNo()));
        tvWorkoutSetNo.setText(String.valueOf(item.getSetNo()));
        return convertView;
    }
}

