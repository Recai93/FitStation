package com.udacity.firebase.shoppinglistplusplus.ui.activeMealDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveMealDetailsActivity extends BaseActivity {
    private static final String LOG_TAG = ActiveMealDetailsActivity.class.getSimpleName();
    private Firebase mCurrentListRef;
    private ActiveMealItemAdapter mActiveListItemAdapter;
    private ListView mListView;
    private String mListId;
    private User mCurrentUser;
    private MealList mMealList;
    private ValueEventListener mCurrentListRefListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_meal_details);
        Intent intent = this.getIntent();
        mListId = intent.getStringExtra(Constants.KEY_LIST_ID);
        if (mListId == null) {
            finish();
            return;
        }
        mCurrentListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST).child(mEncodedEmail).child(mListId);
        Firebase listItemsRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST).child(mEncodedEmail).child(mListId).child(Constants.FIREBASE_LOCATION_MEAL_LIST);
        initializeScreen();
        mActiveListItemAdapter = new ActiveMealItemAdapter(this, Meal.class,
                R.layout.single_client_meal_item, listItemsRef.orderByChild(Constants.FIREBASE_PROPERTY_BOUGHT_BY));
        mListView.setAdapter(mActiveListItemAdapter);
        mCurrentListRefListener = mCurrentListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                MealList shoppingList = snapshot.getValue(MealList.class);

                if (shoppingList == null) {
                    finish();
                    return;
                }
                mMealList = shoppingList;
                invalidateOptionsMenu();
                setTitle(shoppingList.getCreator());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG,
                        getString(R.string.log_error_the_read_failed) +
                                firebaseError.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_details, menu);
        MenuItem archive = menu.findItem(R.id.action_archive);
        archive.setVisible(false);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActiveListItemAdapter.cleanup();
        mCurrentListRef.removeEventListener(mCurrentListRefListener);
    }

    private void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_meals_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}
