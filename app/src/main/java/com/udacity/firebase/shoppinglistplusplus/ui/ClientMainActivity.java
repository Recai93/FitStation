package com.udacity.firebase.shoppinglistplusplus.ui;

import android.content.Intent;
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

import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.ElifJsoup;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.ui.activeMeals.ClientMealActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.activeMeasurement.ClientMeasurementActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.activeWorkout.ClientWorkoutActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.AddTrainerActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ClientMainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String LOG_TAG = ClientMainActivity.class.getSimpleName();
    private Firebase mUserRef;
    private ValueEventListener mUserRefListener;
    private String mEncodedEmail;

    private int navigationItemId;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);
        setTitle("");

        new ElifJsoup().getData2();

        mEncodedEmail = PreferenceManager.getDefaultSharedPreferences(ClientMainActivity.this).getString(Constants.KEY_ENCODED_EMAIL, null);

        mUserRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);

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
                startActivity(new Intent(ClientMainActivity.this, SettingsActivity.class));
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
        //Check to see which item was being clicked and perform appropriate action
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

}
