package com.udacity.firebase.shoppinglistplusplus.ui.activeMeals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;
import com.udacity.firebase.shoppinglistplusplus.ui.activeMealDetails.ActiveMealDetailsActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ClientMealActivity extends AppCompatActivity {
    private String mEncodedEmail;
    private ClientMealAdapter mClientMealAdapter;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_meal);

        mListView = (ListView) findViewById(R.id.list_view_client_meals);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_ENCODED_EMAIL)) {
            mEncodedEmail = intent.getStringExtra(Constants.KEY_ENCODED_EMAIL);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MealList selectedList = mClientMealAdapter.getItem(position);
                if (selectedList != null) {
                    Intent intent = new Intent(ClientMealActivity.this, ActiveMealDetailsActivity.class);
                    String listId = mClientMealAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ClientMealActivity.this);
        String sortOrder = sharedPref.getString(Constants.KEY_PREF_SORT_ORDER_LISTS, Constants.ORDER_BY_KEY);

        Query orderedActiveUserListsRef;
        Firebase activeListsRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST)
                .child(mEncodedEmail);
        if (sortOrder.equals(Constants.ORDER_BY_KEY)) {
            orderedActiveUserListsRef = activeListsRef.orderByKey();
        } else {
            orderedActiveUserListsRef = activeListsRef.orderByChild(sortOrder);
        }
        mClientMealAdapter = new ClientMealAdapter(ClientMealActivity.this, MealList.class,
                R.layout.single_client_meal, orderedActiveUserListsRef,
                mEncodedEmail);
        mListView.setAdapter(mClientMealAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        mClientMealAdapter.cleanup();
    }

}
