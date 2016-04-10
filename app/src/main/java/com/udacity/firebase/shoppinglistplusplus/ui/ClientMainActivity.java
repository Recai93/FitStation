package com.udacity.firebase.shoppinglistplusplus.ui;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.ui.clientAddMeal.ClientAddMealActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.clientMeals.ClientMealActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.clientMeasurements.ClientMeasurementActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.clientWorkout.ClientWorkoutActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.AddTrainerActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

public class ClientMainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, ClientWaterDialog.ClientWaterDialogListener {
    private static final String LOG_TAG = ClientMainActivity.class.getSimpleName();
    private String mEncodedEmail;
    private SharedPreferences sharedPreferences;

    private int navigationItemId;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

    private TextView tvWater;
    private TextView tvWeight;
    private long waterQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);
        setTitle("");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ClientMainActivity.this);
        mEncodedEmail = sharedPreferences.getString(Constants.KEY_ENCODED_EMAIL, null);

        initializeScreen();

    }

    public void addTrainerButtonPressed(View view) {
        Intent intent = new Intent(ClientMainActivity.this, AddTrainerActivity.class);
        startActivity(intent);
    }

    public void onShowWorkoutListPressed(View view) {
        Intent intent = new Intent(ClientMainActivity.this, ClientWorkoutActivity.class);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL, mEncodedEmail);
        startActivity(intent);
    }

    public void onShowMealListPressed(View view) {
        Intent intent = new Intent(ClientMainActivity.this, ClientMealActivity.class);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL, mEncodedEmail);
        startActivity(intent);
    }

    public void onShowMeasurementListPressed(View view) {
        Intent intent = new Intent(ClientMainActivity.this, ClientMeasurementActivity.class);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL, mEncodedEmail);
        startActivity(intent);
    }

    public void onAddDailyWaterPressed(View view) {
        Intent intent = new Intent(ClientMainActivity.this, ClientAddMealActivity.class);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL, mEncodedEmail);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_tip:
                DialogFragment tipsDialog = TipsDialogFragment.newInstance();
                tipsDialog.show(ClientMainActivity.this.getFragmentManager(), "Tips");
                return true;
            case R.id.action_drink_water:
                DialogFragment waterDialog = ClientWaterDialog.newInstance();
                waterDialog.show(ClientMainActivity.this.getFragmentManager(), "AddDailyWaterDialog");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initializeScreen() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvWater = (TextView) findViewById(R.id.tv_water);
        tvWeight = (TextView) findViewById(R.id.tv_weight);

        if (mEncodedEmail != null) {
            Firebase clientInfoRef = new Firebase(Constants.FIREBASE_URL_CLIENT_INFO).child(mEncodedEmail);
            clientInfoRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataSnapshot.getValue();
                    HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                    if (map != null) {
                        if (map.get(Constants.FIREBASE_PROPERTY_CLIENT_INFO_WATER) != null) {
                            waterQuantity = (Long) map.get(Constants.FIREBASE_PROPERTY_CLIENT_INFO_WATER);
                            tvWater.setText(String.valueOf(waterQuantity));
                        }
                        if (map.get(Constants.FIREBASE_PROPERTY_CLIENT_INFO_WEIGHT) != null) {
                            tvWeight.setText(map.get(Constants.FIREBASE_PROPERTY_CLIENT_INFO_WEIGHT).toString());
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        navigationItemId = item.getItemId();
        switch (navigationItemId) {
            case R.id.navigation_item_1:
                drawerLayout.closeDrawer(GravityCompat.START);
                logout();
                return true;
            case R.id.navigation_item_2:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.navigation_item_3:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFinish(long quantity) {
        if (quantity != 0) {
            long total = waterQuantity + quantity;
            tvWater.setText(String.valueOf(total));
            new Firebase(Constants.FIREBASE_URL_CLIENT_INFO).child(mEncodedEmail)
                    .child(Constants.FIREBASE_PROPERTY_CLIENT_INFO_WATER).setValue(total);
        }
    }
}
