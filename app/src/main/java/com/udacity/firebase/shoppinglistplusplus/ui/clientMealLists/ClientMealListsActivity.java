package com.udacity.firebase.shoppinglistplusplus.ui.clientMealLists;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;
import com.udacity.firebase.shoppinglistplusplus.ui.clientMealListDetails.ClientActiveMealListActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ClientMealListsActivity extends AppCompatActivity {
    private static final String LOG_TAG = ClientMealListsActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;

    private Firebase mMealListRef;
    private ValueEventListener mMealListRefListener;
    private ClientMealListsAdapter mMealListRefAdapter;

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_meal);
        initializeScreen();

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();

        Intent intent = getIntent();
        final String mEncodedEmail = intent.getStringExtra(Constants.KEY_ENCODED_EMAIL);
        if (mEncodedEmail == null) {
            finish();
            return;
        }

       mMealListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEAL_LIST)
                .child(mEncodedEmail);
        mMealListRefListener = new ValueEventListener() {
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
        mMealListRef.addListenerForSingleValueEvent(mMealListRefListener);

        mMealListRefAdapter = new ClientMealListsAdapter(ClientMealListsActivity.this, MealList.class,
                R.layout.single_client_meal, mMealListRef);
        mListView.setAdapter(mMealListRefAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MealList selectedList = mMealListRefAdapter.getItem(position);
                if (selectedList != null) {
                    Intent intent = new Intent(ClientMealListsActivity.this, ClientActiveMealListActivity.class);
                    String listId = mMealListRefAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                    intent.putExtra(Constants.KEY_ENCODED_EMAIL, mEncodedEmail);
                    startActivity(intent);
                }
            }
        });
    }

    private void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_client_meals);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_activity_client_meal));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMealListRefAdapter.cleanup();
        mMealListRef.removeEventListener(mMealListRefListener);
    }

}