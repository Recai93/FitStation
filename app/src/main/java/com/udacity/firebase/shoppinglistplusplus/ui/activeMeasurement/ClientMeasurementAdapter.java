package com.udacity.firebase.shoppinglistplusplus.ui.activeMeasurement;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Measurement;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

/**
 * Created by rajaee on 3/22/16.
 */
public class ClientMeasurementAdapter extends FirebaseListAdapter<Measurement> {
    private String mEncodedEmail;

    public ClientMeasurementAdapter(Activity activity, Class<Measurement> modelClass, int modelLayout,
                                    Query ref, String encodedEmail) {
        super(activity, modelClass, modelLayout, ref);
        this.mEncodedEmail = encodedEmail;
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, Measurement measurement) {

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_measurement_title);
        TextView tvCreatedByTrainer = (TextView) view.findViewById(R.id.tv_created_by_trainer);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);

        tvTitle.setText(measurement.getTitle());
        tvCreatedByTrainer.setText(measurement.getCreator());
        tvDate.setText(Utils.getDate((Long)measurement.getTimestampCreated().get(Constants.FIREBASE_PROPERTY_TIMESTAMP),"dd-MM-yyyy"));

//        Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);
//        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//
//                if (user != null) {
//                    textViewCreatedByUser.setText(user.getName());
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Log.e(mActivity.getClass().getSimpleName(),
//                        mActivity.getString(R.string.log_error_the_read_failed) +
//                                firebaseError.getMessage());
//            }
//        });

    }
}