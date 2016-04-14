package com.udacity.firebase.shoppinglistplusplus.ui.trainerWorkouts;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrainerAddWorkoutItemActivity extends AppCompatActivity
        implements AddWorkoutItemDialogFragment.CreateWorkoutDialogListener {
    private final String TAG = TrainerWorkoutActivity.class.getName();
    private String clientEncodedEmail;

    private Map<Integer, Workout> selectedWorkoutMap;

    private TrainerWorkoutItemAdapter workoutAdapter;

    private ArrayList<Integer> selectedIds;

    private ListView listView;
    private Spinner spinnerWorkoutType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_add_workout_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.text_add_workout_list));
        }

        listView = (ListView) findViewById(R.id.list_view_meals_list_items);
        spinnerWorkoutType = (Spinner) findViewById(R.id.spinner_workout_type);

        spinnerWorkoutType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerWorkoutType.getSelectedItemPosition() == 0) {
                    if (workoutAdapter != null) {
                        workoutAdapter.cleanup();
                    }
                } else {
                    workoutAdapter = new TrainerWorkoutItemAdapter(TrainerAddWorkoutItemActivity.this,
                            Workout.class,
                            R.layout.single_trainer_workout_item,
                            getWorkoutRef(spinnerWorkoutType.getSelectedItem().toString()));
                    listView.setAdapter(workoutAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            workoutAdapter.toggleSelected(position);
                            if (workoutAdapter.isSelected(position)) {
                                DialogFragment dialog = AddWorkoutItemDialogFragment.newInstance(position);
                                dialog.show(getFragmentManager(), "AddWorkoutItemDialogFragment");
                            } else {
                                onUnselectWorkout(position);
                            }
                        }
                    });


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        selectedWorkoutMap = new HashMap<>();
        selectedIds = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trainer_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            if (spinnerWorkoutType.getSelectedItemPosition() != 0) {
                ArrayList<Workout> selectedWorkouts = new ArrayList<>();
                for (Workout workout : selectedWorkoutMap.values()) {
                    selectedWorkouts.add(workout);
                }
                selectedWorkoutMap = new HashMap<>();
                Intent intent = new Intent();
                intent.putExtra(Constants.KEY_WORKOUT_OBJECT, selectedWorkouts);
                setResult(Constants.TRAINER_ADD_WORKOUT_ITEM_REQUEST_CODE, intent);
                finish();
            } else {
                Toast.makeText(TrainerAddWorkoutItemActivity.this, getString(R.string.toast_empty_fields), Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private Firebase getWorkoutRef(String workoutType) {
        Firebase ref = new Firebase(Constants.FIREBASE_URL_WORKOUT);
        if (workoutType.equals("Göğüs")) {
            return ref.child(Constants.WORKOUT_TYPE_CHEST);
        } else if (workoutType.equals("Sırt")) {
            return ref.child(Constants.WORKOUT_TYPE_BACK);
        } else if (workoutType.equals("Bacak")) {
            return ref.child(Constants.WORKOUT_TYPE_LEGS);
        } else if (workoutType.equals("Ön kol")) {
            return ref.child(Constants.WORKOUT_TYPE_BICEPS);
        } else if (workoutType.equals("Arka kol")) {
            return ref.child(Constants.WORKOUT_TYPE_TRICEPS);
        } else if (workoutType.equals("Karın")) {
            return ref.child(Constants.WORKOUT_TYPE_ABDOMEN);
        } else if (workoutType.equals("Fonksiyonel")) {
            return ref.child(Constants.WORKOUT_TYPE_FUNCTIONAL);
        } else if (workoutType.equals("Omuz")) {
            return ref.child(Constants.WORKOUT_TYPE_SHOULDER);
        }
        return null;
    }

    @Override
    public void onFinishCreateDialog(int setNo, int repNo, int position) {
        Workout workout = workoutAdapter.getItem(position);
        workout.setSetNo(setNo);
        workout.setRepNo(repNo);
        workoutAdapter.addData(position, setNo, repNo);
        selectedWorkoutMap.put(position, workout);
        workoutAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUnselectWorkout(int position) {
        selectedWorkoutMap.remove(position);
        workoutAdapter.notifyDataSetChanged();
    }
}
