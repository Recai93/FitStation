package com.udacity.firebase.shoppinglistplusplus.ui.clientMealListDetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

public class ClientActiveMealListActivity extends AppCompatActivity {
    private static final String LOG_TAG = ClientActiveMealListActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;

    private Firebase mMealListRef;
    private ValueEventListener mMealListRefListener;

    private Firebase mMealListItemsRef;
    private ValueEventListener mMealListItemsRefListener;
    private ClientActiveMealListItemsAdapter mMealListItemsAdapter;

    private ListView mListView;
    private TextView tvMealListTitle;
    private TextView tvDate;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_meal_details);
        initializeScreen();

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();

        Intent intent = this.getIntent();
        String mEncodedEmail = intent.getStringExtra(Constants.KEY_ENCODED_EMAIL);
        String mListId = intent.getStringExtra(Constants.KEY_LIST_ID);
        if (mListId == null || mEncodedEmail == null) {
            finish();
            return;
        }

        mMealListItemsRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST).child(mEncodedEmail).child(mListId)
                .child(Constants.FIREBASE_LOCATION_MEAL_LIST);
        mMealListItemsRefListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAuthProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, getString(R.string.log_error_the_read_failed) +
                        firebaseError.getMessage());
            }
        };
        mMealListItemsRef.addListenerForSingleValueEvent(mMealListItemsRefListener);
        mMealListItemsAdapter = new ClientActiveMealListItemsAdapter(this, Meal.class,
                R.layout.single_client_meal_item, mMealListItemsRef);
        mListView.setAdapter(mMealListItemsAdapter);

        mMealListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST).child(mEncodedEmail).child(mListId);
        mMealListRefListener = mMealListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                MealList mealList = snapshot.getValue(MealList.class);
                if (mealList == null) {
                    finish();
                    return;
                }
                tvMealListTitle.setText(mealList.getTitle());
                String date = Utils.getDate((long) mealList.getTimestampCreated().get(Constants.FIREBASE_PROPERTY_TIMESTAMP));
                tvDate.setText(date);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, getString(R.string.log_error_the_read_failed) +
                        firebaseError.getMessage());
            }
        });
    }

    private void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_meals_list_items);
        tvMealListTitle = (TextView) findViewById(R.id.tv_meal_list_title);
        tvDate = (TextView) findViewById(R.id.tv_date);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_activity_client_meal_list));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMealListItemsAdapter.cleanup();
        mMealListRef.removeEventListener(mMealListRefListener);
        mMealListItemsRef.removeEventListener(mMealListItemsRefListener);
    }

}
