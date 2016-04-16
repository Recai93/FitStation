package com.udacity.firebase.shoppinglistplusplus.ui.sharing;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;

public class ClientAdapter extends FirebaseListAdapter<User> {
    private static final String LOG_TAG = ClientAdapter.class.getSimpleName();


    public ClientAdapter(Activity activity, Class<User> modelClass, int modelLayout,
                         Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, final User friend) {
        view.setTag(friend.getEmail());
        TextView tvUserName = (TextView) view.findViewById(R.id.user_name);
        ImageView ivUserImage = (ImageView) view.findViewById(R.id.iv_user_image);

        tvUserName.setText(friend.getUsername());
        if (friend.getGender().equals("male")) {
            ivUserImage.setImageResource(R.drawable.avatar_male);
        } else {
            ivUserImage.setImageResource(R.drawable.avatar_male);
        }
    }
}