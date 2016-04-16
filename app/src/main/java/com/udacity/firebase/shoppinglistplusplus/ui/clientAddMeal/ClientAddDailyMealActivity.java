package com.udacity.firebase.shoppinglistplusplus.ui.clientAddMeal;

import android.app.DialogFragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.DailyMeal;
import com.udacity.firebase.shoppinglistplusplus.ui.trainerMeals.TrainerMealActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientAddDailyMealActivity extends AppCompatActivity
        implements ClientAddDailyMealDialogFragment.CreateDailyMealDialogListener {
    Firebase mealListRef;
    private String TAG = TrainerMealActivity.class.getSimpleName();
    private ListView mListView;
    private ClientAddDailyMealAdapter addMealAdapter;
    private ArrayList<DailyMeal> mealsList;
    private EditText mEditTextAddFriendEmail;
    private AutocompleteDailyMealAdapter mFriendsAutocompleteAdapter;
    private String mInput;
    private ListView mListViewAutocomplete;
    private Firebase mealsRef;
    private String mail;
    private long calorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_add_meal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.text_daily_meal_list));
        }

        mail = PreferenceManager.getDefaultSharedPreferences(ClientAddDailyMealActivity.this).getString(Constants.KEY_ENCODED_EMAIL, null);
        mealListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_DAILY_MEALS).child(mail);

        addMealAdapter = new ClientAddDailyMealAdapter(this, DailyMeal.class, R.layout.single_client_daily_meal, mealListRef);
        mListView = (ListView) findViewById(R.id.list_view_meals_list);
        mListView.setAdapter(addMealAdapter);
        mealsList = new ArrayList<>();

        //autocomplete
        mListViewAutocomplete = (ListView) findViewById(R.id.list_view_friends_autocomplete);
        mEditTextAddFriendEmail = (EditText) findViewById(R.id.edit_text_add_friend_email);

        mealsRef = new Firebase(Constants.FIREBASE_URL_MEALS);

        mEditTextAddFriendEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /* Get the input after every textChanged event and transform it to lowercase */
                mInput = mEditTextAddFriendEmail.getText().toString().toLowerCase();

            /* Clean up the old adapter */
                if (mFriendsAutocompleteAdapter != null) mFriendsAutocompleteAdapter.cleanup();
            /* Nullify the adapter data if the input length is less than 2 characters */
                if (mInput.equals("") || mInput.length() < 2) {
                    mListViewAutocomplete.setAdapter(null);

            /* Define and set the adapter otherwise. */
                } else {
                    mFriendsAutocompleteAdapter = new AutocompleteDailyMealAdapter(ClientAddDailyMealActivity.this, DailyMeal.class,
                            R.layout.single_autocomplete_daily_meal, mealsRef.orderByChild(Constants.FIREBASE_PROPERTY_NAME)
                            .startAt(mInput).endAt(mInput + "~").limitToFirst(5));

                    mListViewAutocomplete.setAdapter(mFriendsAutocompleteAdapter);
                }

            }
        });

        mListViewAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogFragment dialog = ClientAddDailyMealDialogFragment.newInstance(position);
                dialog.show(ClientAddDailyMealActivity.this.getFragmentManager(), "AddMealItemDialogFragment");
            }
        });

        Firebase clientInfoRef = new Firebase(Constants.FIREBASE_URL_CLIENT_INFO).child(mail);
        clientInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getValue();
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                if (map != null) {
                    if (map.get(Constants.FIREBASE_PROPERTY_CLIENT_INFO_CALORIE) != null) {
                        calorie = Long.parseLong(map.get(Constants.FIREBASE_PROPERTY_CLIENT_INFO_CALORIE).toString());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFriendsAutocompleteAdapter != null) {
            mFriendsAutocompleteAdapter.cleanup();
        }
    }

    @Override
    public void onFinishCreateDialog(int position, int portion, int portion1, String mealType) {
        DailyMeal dailyMeal = mFriendsAutocompleteAdapter.getItem(position);
        dailyMeal.setCalorie(String.valueOf(portion * Integer.parseInt(dailyMeal.getCalorie())));
        dailyMeal.setQuantity(String.valueOf(portion));
        dailyMeal.setType(mealType);
        mealsList.add(dailyMeal);
        Firebase ref = mealListRef.push();
        String pushId = ref.getKey();
        mealListRef.child(pushId).setValue(dailyMeal);

        long total = calorie + (portion * Long.parseLong(dailyMeal.getCalorie()));
        Firebase clientInfoRef = new Firebase(Constants.FIREBASE_URL_CLIENT_INFO).child(mail);
        clientInfoRef.child(Constants.FIREBASE_PROPERTY_CLIENT_INFO_CALORIE).setValue(total);
        clientInfoRef.child(Constants.FIREBASE_PROPERTY_TIMESTAMP).setValue(ServerValue.TIMESTAMP);
    }
}
