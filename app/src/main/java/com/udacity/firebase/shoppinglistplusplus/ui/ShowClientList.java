package com.udacity.firebase.shoppinglistplusplus.ui;

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
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.ClientAdapter;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ShowClientList  extends BaseActivity {
    private static final String LOG_TAG = ShowClientList.class.getSimpleName();
    private ClientAdapter mFriendAdapter;
    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_client_list);


        Intent intent = this.getIntent();

        initializeScreen();


        Firebase currentUserFriendsRef = new Firebase(Constants.FIREBASE_URL_USER_FRIENDS).child(mEncodedEmail);


        mFriendAdapter = new ClientAdapter(ShowClientList.this, User.class,
                R.layout.show_single_user_item, currentUserFriendsRef);


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


    @Override
    public void onDestroy() {
        super.onDestroy();

        mFriendAdapter.cleanup();
    }


    public void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_friends_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



}
