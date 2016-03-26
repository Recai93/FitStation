package com.udacity.firebase.shoppinglistplusplus.ui.activeWorkoutDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;
import com.udacity.firebase.shoppinglistplusplus.model.WorkoutList;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveWorkoutDetailsActivity extends BaseActivity {
    private static final String LOG_TAG = ActiveWorkoutDetailsActivity.class.getSimpleName();
    private Firebase mCurrentListRef;
    private ActiveWorkoutItemAdapter mActiveListItemAdapter;
    private ListView mListView;
    private String mListId;
    private User mCurrentUser;
    private WorkoutList mWorkoutList;
    private ValueEventListener  mCurrentListRefListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_list_details);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        Intent intent = this.getIntent();
        mListId = intent.getStringExtra(Constants.KEY_LIST_ID);
        if (mListId == null) {
            finish();
            return;
        }

        mCurrentListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_WORKOUTS).child(mEncodedEmail).child(mListId);
        Firebase listItemsRef = new Firebase(Constants.FIREBASE_URL_CLIENT_WORKOUTS).child(mEncodedEmail).child(mListId).child(Constants.FIREBASE_LOCATION_CLIENT_WORKOUT_LIST);
        initializeScreen();
        mActiveListItemAdapter = new ActiveWorkoutItemAdapter(this, Workout.class,
                R.layout.single_active_list_item, listItemsRef.orderByChild(Constants.FIREBASE_PROPERTY_BOUGHT_BY),
                mListId, mEncodedEmail);
        mListView.setAdapter(mActiveListItemAdapter);

        mCurrentListRefListener = mCurrentListRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                WorkoutList shoppingList = snapshot.getValue(WorkoutList.class);

                if (shoppingList == null) {
                    finish();
                    return;
                }
                mWorkoutList = shoppingList;
                mActiveListItemAdapter.setShoppingList(mWorkoutList);
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
        mListView = (ListView) findViewById(R.id.list_view_shopping_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        View footer = getLayoutInflater().inflate(R.layout.footer_empty, null);
        mListView.addFooterView(footer);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

}
