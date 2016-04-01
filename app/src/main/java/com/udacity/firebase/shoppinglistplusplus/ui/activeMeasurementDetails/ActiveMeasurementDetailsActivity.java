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
    private TextView tvRightLegFat;
    private TextView tvLeftLegFat;
    private TextView tvRightLegMuscle;
    private TextView tvLeftLegMuscle;
    private TextView tvAbdomenFat;
    private TextView tvAbdomenMuscle;
    private TextView tvTotalWeight;
    private TextView tvAge;
    private TextView tvHeight;
    private TextView tvBodyMassIndex;
    private TextView tvTotalFatRatio;
    private TextView tvFatFreeMass;
    private TextView tvTotalBodyWater;
    private TextView tvTotalFat;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_measurement_details);
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
        tvAbdomenFat=(TextView)findViewById(R.id.tv_abdomen_fat);
        tvRightArmFat=(TextView)findViewById(R.id.tv_right_arm_fat);
        tvLeftArmFat=(TextView)findViewById(R.id.tv_left_arm_fat);
        tvRightArmMuscle=(TextView)findViewById(R.id.tv_right_arm_muscle);
        tvLeftArmMuscle=(TextView)findViewById(R.id.tv_left_arm_muscle);
        tvRightLegFat=(TextView)findViewById(R.id.tv_right_leg_fat);
        tvLeftLegFat=(TextView)findViewById(R.id.tv_left_leg_fat);
        tvRightLegMuscle=(TextView)findViewById(R.id.tv_right_leg_muscle);
        tvLeftLegMuscle=(TextView)findViewById(R.id.tv_left_leg_muscle);
        tvAbdomenMuscle=(TextView)findViewById(R.id.tv_abdomen_muscle);
        tvTotalWeight=(TextView)findViewById(R.id.tv_total_weight);
        tvAge=(TextView)findViewById(R.id.tv_age);
        tvHeight=(TextView)findViewById(R.id.tv_height);
        tvBodyMassIndex=(TextView)findViewById(R.id.tv_body_mass_index);
        tvTotalFatRatio=(TextView)findViewById(R.id.tv_total_fat_ratio);
        tvFatFreeMass=(TextView)findViewById(R.id.tv_fat_free_mass);
        tvTotalBodyWater=(TextView)findViewById(R.id.tv_total_body_water);
        tvTotalFat=(TextView)findViewById(R.id.tv_total_fat);

        tvAbdomenFat.setText(String.valueOf(measurement.getAbdomenFat()));
        tvRightArmFat.setText(String.valueOf(measurement.getRightArmFat()));
        tvLeftArmFat.setText(String.valueOf(measurement.getLeftArmFat()));
        tvRightArmMuscle.setText(String.valueOf(measurement.getRightArmMuscle()));
        tvLeftArmMuscle.setText(String.valueOf(measurement.getLeftArmMuscle()));
        tvRightLegFat.setText(String.valueOf(measurement.getRightLegFat()));
        tvLeftLegFat.setText(String.valueOf(measurement.getLeftLegFat()));
        tvRightLegMuscle.setText(String.valueOf(measurement.getRightLegMuscle()));
        tvLeftLegMuscle.setText(String.valueOf(measurement.getLeftLegMuscle()));
        tvAbdomenMuscle.setText(String.valueOf(measurement.getAbdomenMuscle()));
        tvTotalWeight.setText(String.valueOf(measurement.getTotalWeight()));
        tvAge.setText(String.valueOf(measurement.getAge()));
        tvHeight.setText(String.valueOf(measurement.getHeight()));
        tvBodyMassIndex.setText(String.valueOf(measurement.getBodyMassIndex()));
        tvTotalFatRatio.setText(String.valueOf(measurement.getTotalFatRatio()));
        tvFatFreeMass.setText(String.valueOf(measurement.getFatFreeMass()));
        tvTotalBodyWater.setText(String.valueOf(measurement.getTotalBodyWater()));
        tvTotalFat.setText(String.valueOf(measurement.getTotalFat()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}