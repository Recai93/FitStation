package com.udacity.firebase.shoppinglistplusplus.ui.activeWorkout;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.WorkoutList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

/**
 * Created by rajaee on 3/22/16.
 */
public class ClientWorkoutAdapter extends FirebaseListAdapter<WorkoutList> {

    public ClientWorkoutAdapter(Activity activity, Class<WorkoutList> modelClass, int modelLayout,
                                Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, WorkoutList workoutList) {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_workout_title);
        TextView tvCreatedByTrainer = (TextView) view.findViewById(R.id.tv_created_by_trainer);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);

        String date = Utils.getDate((Long) workoutList.getTimestampCreated().get(Constants.FIREBASE_PROPERTY_TIMESTAMP), "dd-MM-yyyy");
        tvTitle.setText(date + " egzersiz listesi");
        tvCreatedByTrainer.setText(workoutList.getCreator());
        tvDate.setText(date);

    }
}