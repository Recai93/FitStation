package com.udacity.firebase.shoppinglistplusplus.ui.trainerMeals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.ClientList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrainerMealActivity extends AppCompatActivity {
    private String TAG = TrainerMealActivity.class.getSimpleName();
    private String clientEncodedEmail;
    private List<Meal> selectedMeals;

    private TrainerMealAdapter mActiveListItemAdapter;
    private ListView mListView;
    private TextView tvAddClient;
    private TextView tvMealListName;

    private String trainerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_add_meal_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.text_add_meal_list));
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.KEY_TRAINER_NAME)) {
            trainerName = intent.getStringExtra(Constants.KEY_TRAINER_NAME);
        }

        tvAddClient = (TextView) findViewById(R.id.tv_add_client);
        tvMealListName = (TextView) findViewById(R.id.edit_text_meal_list_title);
        mListView = (ListView) findViewById(R.id.list_view_meals_list);

        mActiveListItemAdapter = new TrainerMealAdapter(this, R.layout.single_client_meal_item);
        mListView.setAdapter(mActiveListItemAdapter);

        selectedMeals = new ArrayList<>();
    }

    public void onAddMealPressed(View view) {
        Intent intent = new Intent(TrainerMealActivity.this, TrainerAddMealItemActivity.class);
        startActivityForResult(intent, Constants.TRAINER_ADD_MEAL_ITEM_REQUEST_CODE);
    }

    public void onAddClientPressed(View view) {
        Intent intent = new Intent(TrainerMealActivity.this, ClientList.class);
        startActivityForResult(intent, Constants.FRIEND_LIST_REQUEST_CODE);
    }

    private void onSaveMealListPressed() {
        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        String listName = tvMealListName.getText().toString();

        Firebase mealListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST).child(clientEncodedEmail);

        Firebase ref = mealListRef.push();
        String pushId = ref.getKey();

        MealList list = new MealList(listName, trainerName, timestampCreated, selectedMeals);

        mealListRef.child(pushId).setValue(list);
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
        } else if (requestCode == Constants.TRAINER_ADD_MEAL_ITEM_REQUEST_CODE) {
            if (data != null && data.hasExtra(Constants.KEY_MEAL_OBJECT)) {
                Meal meal = (Meal) data.getSerializableExtra(Constants.KEY_MEAL_OBJECT);
                mActiveListItemAdapter.add(meal);
                selectedMeals.add(meal);
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
            if (!tvAddClient.equals("") && !tvMealListName.equals("") && !mActiveListItemAdapter.isEmpty()) {
                onSaveMealListPressed();
            } else {
                Toast.makeText(TrainerMealActivity.this, getString(R.string.toast_empty_fields), Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
