package com.udacity.firebase.shoppinglistplusplus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.ui.activeMeals.TrainerDietlistActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.activeMeasurement.TrainerMeasurementActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.activeWorkout.TrainerWorkoutActivity;

public class TrainerMainActivity extends BaseActivity {
    private static final String LOG_TAG = TrainerMainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_main);
        initializeScreen();
    }

    public void AddWorkoutlistButtonPressed(View view) {
        Intent intent = new Intent(TrainerMainActivity.this, TrainerWorkoutActivity.class);
        startActivity(intent);
    }

    public void AddMesurementListButtonPressed(View view) {
        Intent intent = new Intent(TrainerMainActivity.this, TrainerMeasurementActivity.class);
        startActivity(intent);
    }

    public void AddMealListButtonPressed(View view) {
        Intent intent = new Intent(TrainerMainActivity.this, TrainerDietlistActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_tip) {
            startActivity(new Intent(TrainerMainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initializeScreen() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }


}
