package com.udacity.firebase.shoppinglistplusplus.ui.trainerWorkouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
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
    private String clientEncodedEmail;

    private List<Workout> selectedWorkouts;

    private TrainerWorkoutAdapter workoutAdapter;

    private ListView listView;
    private TextView tvAddClient;
    private EditText etListName;
    private String trainerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_add_workout_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.text_add_workout_list));
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.KEY_TRAINER_NAME)) {
            trainerName = intent.getStringExtra(Constants.KEY_TRAINER_NAME);
        }

        tvAddClient = (TextView) findViewById(R.id.tv_add_client);
        etListName = (EditText) findViewById(R.id.et_workout_list_name);
        listView = (ListView) findViewById(R.id.list_view_workout_list);

        workoutAdapter = new TrainerWorkoutAdapter(this, R.layout.single_trainer_workout_item);
        listView.setAdapter(workoutAdapter);

        selectedWorkouts = new ArrayList<>();

    }

    public void onAddClientPressed(View view) {
        Intent intent = new Intent(TrainerWorkoutActivity.this, ClientList.class);
        startActivityForResult(intent, Constants.FRIEND_LIST_REQUEST_CODE);
    }

    public void onAddWorkoutListPressed(View view) {
        Intent intent = new Intent(TrainerWorkoutActivity.this, TrainerAddWorkoutItemActivity.class);
        startActivityForResult(intent, Constants.TRAINER_ADD_WORKOUT_ITEM_REQUEST_CODE);
    }

    private void onSaveWorkoutPressed() {
        Firebase workoutListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_WORKOUTS).child(clientEncodedEmail);

        HashMap<String, Object> timestampJoined = new HashMap<>();
        timestampJoined.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        String listName = etListName.getText().toString();
        WorkoutList list = new WorkoutList(selectedWorkouts, trainerName, listName, timestampJoined);

        Firebase ref = workoutListRef.push();
        String pushId = ref.getKey();

        workoutListRef.child(pushId).setValue(list);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.FRIEND_LIST_REQUEST_CODE) {
            if (data != null && data.hasExtra(Constants.KEY_ENCODED_EMAIL)) {
                clientEncodedEmail = data.getStringExtra(Constants.KEY_ENCODED_EMAIL);
                tvAddClient.setText(data.getStringExtra(Constants.KEY_USER_NAME));
            }
        } else if (requestCode == Constants.TRAINER_ADD_WORKOUT_ITEM_REQUEST_CODE) {
            if (data != null && data.hasExtra(Constants.KEY_WORKOUT_OBJECT)) {
                ArrayList<Workout> workoutList = (ArrayList<Workout>) data.getSerializableExtra(Constants.KEY_WORKOUT_OBJECT);
                for (Workout workout : workoutList) {
                    workoutAdapter.add(workout);
                    selectedWorkouts.add(workout);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trainer_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            if (!tvAddClient.getText().toString().equals("")
                    && !etListName.getText().toString().equals("")) {
                onSaveWorkoutPressed();
            } else {
                Toast.makeText(TrainerWorkoutActivity.this, getString(R.string.toast_empty_fields), Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
