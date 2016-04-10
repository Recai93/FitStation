package com.udacity.firebase.shoppinglistplusplus.ui.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Created by rajaee on 3/10/16.
 */
public class ClientList extends BaseActivity {
    private static final String LOG_TAG = ClientList.class.getSimpleName();
    private ClientAdapter mFriendAdapter;
    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        /* Get the push ID from the extra passed by ActiveListDetailsActivity */
        Intent intent = this.getIntent();

        /**
         * Link layout elements from XML and setup the toolbar
         */
        initializeScreen();

        /**
         * Create Firebase references
         */
        Firebase currentUserFriendsRef = new Firebase(Constants.FIREBASE_URL_USER_FRIENDS).child(mEncodedEmail);

        /**
         * Add ValueEventListeners to Firebase references
         * to control get data and control behavior and visibility of elements
         */


        /**
         * Set interactive bits, such as click events/adapters
         */
        mFriendAdapter = new ClientAdapter(ClientList.this, User.class,
                R.layout.single_user_item, currentUserFriendsRef);

        /* Set adapter for the listView */
        mListView.setAdapter(mFriendAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                User user = mFriendAdapter.getItem(position);
                intent.putExtra(Constants.KEY_ENCODED_EMAIL, user.getEmail());
                intent.putExtra(Constants.KEY_USER_NAME, user.getName());
                setResult(Constants.FRIEND_LIST_REQUEST_CODE, intent);
                Log.i(LOG_TAG, "selected user: " + user.getName());
                finish();
            }
        });
    }

    /**
     * Cleanup the adapter when activity is destroyed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        /* Set adapter for the listView */
        mFriendAdapter.cleanup();
    }

    /**
     * Link layout elements from XML and setup the toolbar
     */
    public void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_friends_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        /* Add back button to the action bar */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



}