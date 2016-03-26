package com.udacity.firebase.shoppinglistplusplus.ui.activeWorkout;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.model.WorkoutList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Created by rajaee on 3/22/16.
 */
public class ClientWorkoutAdapter extends FirebaseListAdapter<WorkoutList> {
    private String mEncodedEmail;

    public ClientWorkoutAdapter(Activity activity, Class<WorkoutList> modelClass, int modelLayout,
                                Query ref, String encodedEmail) {
        super(activity, modelClass, modelLayout, ref);
        this.mEncodedEmail = encodedEmail;
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, WorkoutList list) {

        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_workout_name);
        final TextView textViewCreatedByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);

        String ownerEmail = list.getCreator();

        textViewListName.setText("asdf");

        Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(ownerEmail);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user != null) {
                    textViewCreatedByUser.setText(user.getName());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(mActivity.getClass().getSimpleName(),
                        mActivity.getString(R.string.log_error_the_read_failed) +
                                firebaseError.getMessage());
            }
        });

    }
}