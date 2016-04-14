package com.udacity.firebase.shoppinglistplusplus.ui.clientWorkoutDetails;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveWorkoutItemAdapter extends FirebaseListAdapter<Workout> {

    public ActiveWorkoutItemAdapter(Activity activity, Class<Workout> modelClass, int modelLayout,
                                    Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, final Workout item, int position) {
        TextView tvWorkoutName = (TextView) view.findViewById(R.id.tv_workout_name);
        ImageView imageWorkoutIcon = (ImageView) view.findViewById(R.id.image_workout_ic);
        ImageView imageVideo = (ImageView) view.findViewById(R.id.image_video);

        imageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl())));
            }
        });
        switch (item.getType()) {
            case Constants.WORKOUT_TYPE_ABDOMEN:
                imageWorkoutIcon.setImageResource(R.drawable.ic_abdomen);
                break;
            case Constants.WORKOUT_TYPE_BACK:
                imageWorkoutIcon.setImageResource(R.drawable.ic_back);
                break;
            case Constants.WORKOUT_TYPE_BICEPS:
                imageWorkoutIcon.setImageResource(R.drawable.ic_biceps);
                break;
            case Constants.WORKOUT_TYPE_CHEST:
                imageWorkoutIcon.setImageResource(R.drawable.ic_chest);
                break;
            case Constants.WORKOUT_TYPE_FUNCTIONAL:
                imageWorkoutIcon.setImageResource(R.drawable.ic_functional);
                break;
            case Constants.WORKOUT_TYPE_LEGS:
                imageWorkoutIcon.setImageResource(R.drawable.ic_leg);
                break;
            case Constants.WORKOUT_TYPE_SHOULDER:
                imageWorkoutIcon.setImageResource(R.drawable.ic_back);
                break;
            case Constants.WORKOUT_TYPE_TRICEPS:
                imageWorkoutIcon.setImageResource(R.drawable.ic_triceps);
                break;
        }
        tvWorkoutName.setText(item.getName());
    }

}
