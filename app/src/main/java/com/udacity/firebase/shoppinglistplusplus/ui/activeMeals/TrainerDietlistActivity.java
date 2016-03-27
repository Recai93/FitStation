package com.udacity.firebase.shoppinglistplusplus.ui.activeMeals;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;
import com.udacity.firebase.shoppinglistplusplus.ui.ImportRepastData;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.ClientList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrainerDietlistActivity extends AppCompatActivity implements AddClientMealDialogFragment.EditNameDialogListener {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Meal>> expandableListDetail;
    private String TAG = TrainerDietlistActivity.class.getSimpleName();
    private TextView tvAddClient;
    private String clientEncodedEmail;

    private int grpPosition;
    private List<Meal> selectedMeals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_dietlist);

        selectedMeals = new ArrayList<>();

        tvAddClient = (TextView) findViewById(R.id.tv_add_client);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableRepastTypesListView);
        expandableListDetail = new ImportRepastData().getData();

        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new MealExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                grpPosition = groupPosition;
                DialogFragment dialog = AddClientMealDialogFragment.newInstance();
                dialog.show(TrainerDietlistActivity.this.getFragmentManager(), "AddMealDialogFragment");
            }
        });

    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Meal meal = new Meal(inputText, inputText, inputText,"");
        expandableListDetail.get(expandableListAdapter.getGroup(grpPosition)).add(meal);
        selectedMeals.add(meal);
    }

    public void onAddClientPressed(View view) {
        Intent intent = new Intent(TrainerDietlistActivity.this, ClientList.class);
        startActivityForResult(intent, Constants.FRIEND_LIST_REQUEST_CODE);
    }

    public void onSaveMealListPressed(View view) {
        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        String creator = PreferenceManager.getDefaultSharedPreferences(TrainerDietlistActivity.this).getString(Constants.KEY_ENCODED_EMAIL, null);

        Firebase mealListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST).child(clientEncodedEmail);

        Firebase ref = mealListRef.push();
        String pushId = ref.getKey();

        MealList list = new MealList(creator, timestampCreated, selectedMeals);

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
        }
    }

}
