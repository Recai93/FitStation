package com.udacity.firebase.shoppinglistplusplus.ui.clientMeasurements;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Measurement;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

public class ClientMeasurementAdapter extends FirebaseListAdapter<Measurement> {

    public ClientMeasurementAdapter(Activity activity, Class<Measurement> modelClass, int modelLayout,
                                    Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, Measurement measurement) {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_measurement_title);
        TextView tvCreatedByTrainer = (TextView) view.findViewById(R.id.tv_created_by_trainer);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);

        String date = Utils.getDate((Long) measurement.getTimestampCreated().get(Constants.FIREBASE_PROPERTY_TIMESTAMP));
        tvTitle.setText(measurement.getTitle());
        tvCreatedByTrainer.setText(measurement.getCreator());
        tvDate.setText(date);
    }
}