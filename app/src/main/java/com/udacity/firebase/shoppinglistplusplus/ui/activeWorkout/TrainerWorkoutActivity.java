package com.udacity.firebase.shoppinglistplusplus.ui.activeWorkout;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;
import com.udacity.firebase.shoppinglistplusplus.model.WorkoutList;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.ClientList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrainerWorkoutActivity extends AppCompatActivity {
    private final String TAG = TrainerWorkoutActivity.class.getName();

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Workout>> expandableListDetail;


    private TextView tvAddClient;
    private Firebase mWorkoutRef;
    private String clientEncodedEmail;

    private List<Workout> selectedWorkouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableWorkoutListView);
        tvAddClient = (TextView) findViewById(R.id.tv_add_client);

        selectedWorkouts = new ArrayList<>();
        expandableListDetail = new HashMap<>();
        mWorkoutRef = new Firebase(Constants.FIREBASE_URL_WORKOUT);

        mWorkoutRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    List<Workout> workoutList = new ArrayList<Workout>();
                    for (DataSnapshot subChild : child.getChildren()) {
                        Workout wo = subChild.getValue(Workout.class);
                        workoutList.add(wo);
                    }
                    expandableListDetail.put(child.getKey(), workoutList);
                }
                setupExpandableListview();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    private void setupExpandableListview() {
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new TrainerWorkoutExpandableListAdapter(getBaseContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Workout workout = (Workout) expandableListAdapter.getChild(groupPosition, childPosition);
//                final ImageButton buttonToggleShare = (ImageButton) v.findViewById(R.id.button_toggle_workout);
                if (!selectedWorkouts.contains(workout)) {
                    selectedWorkouts.add(workout);
                    v.setBackgroundColor(ContextCompat.getColor(TrainerWorkoutActivity.this, R.color.accent));
//                    buttonToggleShare.setImageResource(R.drawable.ic_shared_check);
                } else {
                    selectedWorkouts.remove(workout);
                    v.setBackgroundColor(ContextCompat.getColor(TrainerWorkoutActivity.this, R.color.black));
//                    buttonToggleShare.setImageResource(R.drawable.icon_add_friend);
                }
                return false;
            }
        });
    }

    public void onAddClientPressed(View view) {
        Intent intent = new Intent(TrainerWorkoutActivity.this, ClientList.class);
        startActivityForResult(intent, Constants.FRIEND_LIST_REQUEST_CODE);
    }

    public void onSaveWorkoutPressed(View view) {
        Firebase workoutListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_WORKOUTS).child(clientEncodedEmail);

        HashMap<String, Object> timestampJoined = new HashMap<>();
        timestampJoined.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        String creator = PreferenceManager.getDefaultSharedPreferences(TrainerWorkoutActivity.this).getString(Constants.KEY_ENCODED_EMAIL, null);
        WorkoutList list = new WorkoutList(selectedWorkouts, creator, timestampJoined);

        Firebase ref = workoutListRef.push();
        String pushId = ref.getKey();

        workoutListRef.child(pushId).setValue(list);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.FRIEND_LIST_REQUEST_CODE) {
            if (data != null && data.hasExtra(Constants.KEY_ENCODED_EMAIL)) {
                Log.i(TAG, data.getStringExtra(Constants.KEY_ENCODED_EMAIL)
                        + " " + data.getStringExtra(Constants.KEY_USER_NAME));
                clientEncodedEmail = data.getStringExtra(Constants.KEY_ENCODED_EMAIL);
                tvAddClient.setText(data.getStringExtra(Constants.KEY_USER_NAME));
            }
        }
    }

}
