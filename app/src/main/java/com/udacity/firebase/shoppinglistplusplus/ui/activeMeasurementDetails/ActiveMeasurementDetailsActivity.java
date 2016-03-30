package com.udacity.firebase.shoppinglistplusplus.ui.activeMeasurementDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Measurement;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveMeasurementDetailsActivity extends BaseActivity {
    private static final String LOG_TAG = ActiveMeasurementDetailsActivity.class.getSimpleName();

    private TextView tvRightArmFat;
    private TextView tvLeftArmFat;
    private TextView tvRightArmMuscle;
    private TextView tvLeftArmMuscle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_measurement_details);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        initializeScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_details, menu);
        MenuItem archive = menu.findItem(R.id.action_archive);
        archive.setVisible(false);
        return true;
    }

    private void initializeScreen() {
        Intent intent = getIntent();
        Measurement measurement = (Measurement) intent.getSerializableExtra(Constants.KEY_MEASUREMENT_OBJECT);




        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

}
