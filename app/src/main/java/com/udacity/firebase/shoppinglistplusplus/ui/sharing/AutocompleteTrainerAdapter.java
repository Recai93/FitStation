package com.udacity.firebase.shoppinglistplusplus.ui.sharing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Populates the list_view_friends_autocomplete inside AddFriendActivity
 */
public class AutocompleteTrainerAdapter extends FirebaseListAdapter<User> {
    private String mEncodedEmail;
    private SharedPreferences mSharedPref;

    /**
     * Public constructor that initializes private instance variables when adapter is created
     */

    public AutocompleteTrainerAdapter(Activity activity, Class<User> modelClass, int modelLayout,
                                     Query ref, String encodedEmail) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        this.mEncodedEmail = encodedEmail;
    }

    /**
     * Protected method that populates the view attached to the adapter (list_view_friends_autocomplete)
     * with items inflated from single_autocomplete_item.xml
     * populateView also handles data changes and updates the listView accordingly
     */
    @Override
    protected void populateView(View view, final User trainer) {
         /* Get friends email textview and set it's text to user.email() */
        TextView textViewFriendEmail = (TextView) view.findViewById(R.id.text_view_autocomplete_item);
        textViewFriendEmail.setText(Utils.decodeEmail(trainer.getEmail()));

        /**
         * Set the onClickListener to a single list item
         * If selected email is not friend already and if it is not the
         * current user's email, we add selected user to current user's friends
         */

        textViewFriendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * If selected user is not current user proceed
                 */


                    Firebase currentUserFriendsRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);
                    Map<String,Object> _trainer = new HashMap<String, Object>();
                    _trainer.put("trainer", trainer.getEmail());
                    currentUserFriendsRef.updateChildren(_trainer);

                    mSharedPref = PreferenceManager.getDefaultSharedPreferences(mActivity);

                    if(mEncodedEmail != null){
                        Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);
                                Firebase currentTrainerClientRef = new Firebase(Constants.FIREBASE_URL_USER_FRIENDS).child(trainer.getEmail());
                                currentTrainerClientRef.child(mEncodedEmail).setValue(user);

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }


                    mActivity.finish();

            }
        });

    }


    private boolean isNotCurrentTrainer(User trainer) {
        if (trainer.getEmail().equals(mEncodedEmail)) {
            /* Toast appropriate error message if the user is trying to add themselves  */
            Toast.makeText(mActivity,
                    mActivity.getResources().getString(R.string.toast_you_cant_add_yourself),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isNotAlreadyAdded(DataSnapshot dataSnapshot, User trainer) {
        if (dataSnapshot.getValue(User.class) != null) {
            /* Toast appropriate error message if the user is already a friend of the user */
            String friendError = String.format(mActivity.getResources().
                            getString(R.string.toast_is_already_your_friend),
                    trainer.getName());

            Toast.makeText(mActivity,
                    friendError,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
