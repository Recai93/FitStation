package com.udacity.firebase.shoppinglistplusplus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.ClientAdapter;
import com.udacity.firebase.shoppinglistplusplus.ui.trainerMeals.TrainerMealActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.trainerMeasurements.TrainerMeasurementActivity;
import com.udacity.firebase.shoppinglistplusplus.ui.trainerWorkouts.TrainerWorkoutActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class TrainerMainActivity extends BaseActivity {
    private static final String LOG_TAG = TrainerMainActivity.class.getSimpleName();
    private String trainerName;
    private ClientAdapter mFriendAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.list_view_clients);

        String encodedEmail = PreferenceManager.getDefaultSharedPreferences(TrainerMainActivity.this)
                .getString(Constants.KEY_ENCODED_EMAIL, null);

        Firebase currentUserFriendsRef = new Firebase(Constants.FIREBASE_URL_USER_FRIENDS).child(mEncodedEmail);
        mFriendAdapter = new ClientAdapter(TrainerMainActivity.this, User.class,
                R.layout.single_user_item, currentUserFriendsRef);
        mListView.setAdapter(mFriendAdapter);

        if (encodedEmail != null) {
            Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(encodedEmail);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
                        trainerName = user.getName() + " " + user.getSurname();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }

    public void onAddWorkoutListButtonPressed(View view) {
        if (trainerName != null) {
            Intent intent = new Intent(TrainerMainActivity.this, TrainerWorkoutActivity.class);
            intent.putExtra(Constants.KEY_TRAINER_NAME, trainerName);
            startActivity(intent);
        }
    }

    public void onAddMeasurementListButtonPressed(View view) {
        if (trainerName != null) {
            Intent intent = new Intent(TrainerMainActivity.this, TrainerMeasurementActivity.class);
            intent.putExtra(Constants.KEY_TRAINER_NAME, trainerName);
            startActivity(intent);
        }
    }

    public void onAddMealListButtonPressed(View view) {
        if (trainerName != null) {
            Intent intent = new Intent(TrainerMainActivity.this, TrainerMealActivity.class);
            intent.putExtra(Constants.KEY_TRAINER_NAME, trainerName);
            startActivity(intent);
        }
    }

//    public void onShowClientListPressed(View view) {
//        Intent intent = new Intent(TrainerMainActivity.this, ShowClientList.class);
//        startActivity(intent);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_tip) {
//            startActivity(new Intent(TrainerMainActivity.this, SettingsActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
