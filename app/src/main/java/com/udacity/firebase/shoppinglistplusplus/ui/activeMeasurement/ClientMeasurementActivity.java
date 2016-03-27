package com.udacity.firebase.shoppinglistplusplus.ui.activeMeasurement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Measurement;
import com.udacity.firebase.shoppinglistplusplus.ui.activeMeasurementDetails.ActiveMeasurementDetailsActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ClientMeasurementActivity extends AppCompatActivity {
    private String mEncodedEmail;
    private ClientMeasurementAdapter mClientMeasurementAdapter;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_measurement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(getString(R.string.title_activity_client_measurement));

        mListView = (ListView) findViewById(R.id.list_view_client_measurements);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_ENCODED_EMAIL)) {
            mEncodedEmail = intent.getStringExtra(Constants.KEY_ENCODED_EMAIL);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Measurement measurement = mClientMeasurementAdapter.getItem(position);
                if (measurement != null) {
                    Intent intent = new Intent(ClientMeasurementActivity.this, ActiveMeasurementDetailsActivity.class);
                    String listId = mClientMeasurementAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                    intent.putExtra(Constants.KEY_MEASUREMENT_OBJECT, measurement);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ClientMeasurementActivity.this);
        String sortOrder = sharedPref.getString(Constants.KEY_PREF_SORT_ORDER_LISTS, Constants.ORDER_BY_KEY);

        Query orderedActiveUserListsRef;
        Firebase activeListsRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEASUREMENTS)
                .child(mEncodedEmail);
        if (sortOrder.equals(Constants.ORDER_BY_KEY)) {
            orderedActiveUserListsRef = activeListsRef.orderByKey();
        } else {
            orderedActiveUserListsRef = activeListsRef.orderByChild(sortOrder);
        }
        mClientMeasurementAdapter = new ClientMeasurementAdapter(ClientMeasurementActivity.this, Measurement.class,
                R.layout.single_client_measurement, orderedActiveUserListsRef, mEncodedEmail);
        mListView.setAdapter(mClientMeasurementAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        mClientMeasurementAdapter.cleanup();
    }

}