package com.udacity.firebase.shoppinglistplusplus.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents Sign up screen and functionality of the app
 */
public class CreateAccountActivity extends BaseActivity {
    private static final String LOG_TAG = CreateAccountActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;
    private Firebase mFirebaseRef;
    private EditText mEditTextUsernameCreate, mEditTextEmailCreate, mEditTextNameCreate,
            mEditTextSurnameCreate, mEditTextPhoneNumberCreate, mEditTextBirthdayCreate,
            mEditTextPasword, mEditTextConfirmPassword;
    private RadioGroup mGenderRadioGroup;
    private String mUserName, mUserEmail, mPassword, mConfirmPassword, mName, mSurName, mPhoneNumber, mGender, mTrainer;
    private Date mBirthday;
    private boolean mIsTrainer;
    private SecureRandom mRandom = new SecureRandom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        /**
         * Create Firebase references
         */
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        /**
         * Link layout elements from XML and setup the progress dialog
         */
        initializeScreen();
    }

    /**
     * Override onCreateOptionsMenu to inflate nothing
     *
     * @param menu The menu with which nothing will happen
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    /**
     * Link layout elements from XML and setup the progress dialog
     */
    public void initializeScreen() {
        mEditTextUsernameCreate = (EditText) findViewById(R.id.edit_text_username_create);
        mEditTextEmailCreate = (EditText) findViewById(R.id.edit_text_mail_create);
        mEditTextBirthdayCreate = (EditText) findViewById(R.id.edit_text_birthday_create);
        mEditTextNameCreate = (EditText) findViewById(R.id.edit_text_name_create);
        mEditTextSurnameCreate = (EditText) findViewById(R.id.edit_text_surname_create);
        mEditTextPhoneNumberCreate = (EditText) findViewById(R.id.edit_text_phonenumber_create);
        mGenderRadioGroup = (RadioGroup) findViewById(R.id.radio_group_gender);
        mGenderRadioGroup.check(R.id.radio_button_female);
        mEditTextPasword = (EditText) findViewById(R.id.edit_text_password_create);
        mEditTextConfirmPassword = (EditText) findViewById(R.id.edit_text_confirm_password_create);


        LinearLayout linearLayoutCreateAccountActivity = (LinearLayout) findViewById(R.id.linear_layout_create_account_activity);
        initializeBackground(linearLayoutCreateAccountActivity);

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getResources().getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getResources().getString(R.string.progress_dialog_check_inbox));
        mAuthProgressDialog.setCancelable(false);
    }

    /**
     * Open LoginActivity when user taps on "Sign in" textView
     */
    public void onSignInPressed(View view) {
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * Create new account using Firebase email/password provider
     */
    public void onCreateAccountPressed(View view) {
        mUserName = mEditTextUsernameCreate.getText().toString();
        mUserEmail = mEditTextEmailCreate.getText().toString().toLowerCase();
        mName = mEditTextNameCreate.getText().toString();
        mSurName = mEditTextSurnameCreate.getText().toString();
        mPhoneNumber = mEditTextPhoneNumberCreate.getText().toString();
        //mBirthday = mEditTextBirthdayCreate.getText().toString();
        //mPassword = new BigInteger(130, mRandom).toString(32);
        mPassword = mEditTextPasword.getText().toString();
        mConfirmPassword = mEditTextConfirmPassword.getText().toString();


        mIsTrainer = ((CheckBox) findViewById(R.id.TrainerCheckBox)).isChecked();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isTrainer", mIsTrainer).apply();
        mGender = ((RadioButton) mGenderRadioGroup
                .findViewById(mGenderRadioGroup.getCheckedRadioButtonId())).getText().toString();


        boolean validEmail = isEmailValid(mUserEmail);
        boolean validUserName = isUserNameValid(mUserName);
        boolean validInputs = validEmail && validUserName;

        if (mUserName.equals("")) {
            mEditTextUsernameCreate.setError(getString(R.string.error_cannot_be_empty));
            validInputs = false;
        }

        if (mName.equals("")) {
            mEditTextNameCreate.setError(getString(R.string.error_cannot_be_empty));
            validInputs = false;
        }

        if (mSurName.equals("")) {
            mEditTextSurnameCreate.setError(getString(R.string.error_cannot_be_empty));
            validInputs = false;
        }

        if (mPhoneNumber.equals("")) {
            mEditTextPhoneNumberCreate.setError(getString(R.string.error_cannot_be_empty));
            validInputs = false;
        }
        if (mPassword.equals("")) {
            mEditTextPasword.setError(getString(R.string.error_cannot_be_empty));
            validInputs = false;
        }
        if (mConfirmPassword.equals("")) {
            mEditTextConfirmPassword.setError(getString(R.string.error_cannot_be_empty));
            validInputs = false;
        }

        if (!mConfirmPassword.equals(mPassword)) {
            mEditTextConfirmPassword.setError("Password area should match!");
            mEditTextPasword.setError("Password area should match!");
            validInputs = false;
        }
        if (!validInputs) return;

        mAuthProgressDialog.show();

        /**
         * Create new user with specified email and password
         */
        mFirebaseRef.createUser(mUserEmail, mPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(final Map<String, Object> result) {
                /**
                 * If user was successfully created, run resetPassword() to send temporary 24h
                 * password to the user's email and make sure that user owns specified email
                 */
                mAuthProgressDialog.dismiss();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(CreateAccountActivity.this);
                SharedPreferences.Editor spe = sp.edit();

                /**
                 * Save name and email to sharedPreferences to create User database record
                 * when the registered user will sign in for the first time
                 */
                spe.putString(Constants.KEY_SIGNUP_EMAIL, mUserEmail).apply();

                /**
                 * Encode user email replacing "." with ","
                 * to be able to use it as a Firebase db key
                 */
                createUserInFirebaseHelper((String) result.get("uid"));
                finish();

            }

            @Override
            public void onError(FirebaseError firebaseError) {
                /* Error occurred, log the error and dismiss the progress dialog */
                Log.d(LOG_TAG, getString(R.string.log_error_occurred) +
                        firebaseError);
                mAuthProgressDialog.dismiss();
                /* Display the appropriate error message */
                if (firebaseError.getCode() == FirebaseError.EMAIL_TAKEN) {
                    mEditTextEmailCreate.setError(getString(R.string.error_email_taken));
                } else {
                    showErrorToast(firebaseError.getMessage());
                }

            }
        });


    }

    /**
     * Creates a new user in Firebase from the Java POJO
     */
    private void createUserInFirebaseHelper(final String authUserId) {
        final String encodedEmail = Utils.encodeEmail(mUserEmail);

        /**
         * Create the user and uid mapping
         */
        HashMap<String, Object> userAndUidMapping = new HashMap<String, Object>();

        /* Set raw version of date to the ServerValue.TIMESTAMP value and save into dateCreatedMap */
        HashMap<String, Object> timestampJoined = new HashMap<>();
        timestampJoined.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        /* Create a HashMap version of the user to add */
        User newUser = new User(mName, mSurName, mUserName, mGender, encodedEmail, mBirthday, mIsTrainer, mPhoneNumber, mTrainer, timestampJoined);
        HashMap<String, Object> newUserMap = (HashMap<String, Object>)
                new ObjectMapper().convertValue(newUser, Map.class);

        /* Add the user and UID to the update map */
        userAndUidMapping.put("/" + Constants.FIREBASE_LOCATION_USERS + "/" + encodedEmail,
                newUserMap);
        userAndUidMapping.put("/" + Constants.FIREBASE_LOCATION_UID_MAPPINGS + "/"
                + authUserId, encodedEmail);
        if (mIsTrainer) {
            userAndUidMapping.put("/" + Constants.FIREBASE_LOCATION_TRAINER + "/" + encodedEmail,
                    newUserMap);
        }

        /* Try to update the database; if there is already a user, this will fail */
        mFirebaseRef.updateChildren(userAndUidMapping, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    /* Try just making a uid mapping */
                    mFirebaseRef.child(Constants.FIREBASE_LOCATION_UID_MAPPINGS)
                            .child(authUserId).setValue(encodedEmail);
                }
                /**
                 *  The value has been set or it failed; either way, log out the user since
                 *  they were only logged in with a temp password
                 **/
                mFirebaseRef.unauth();
            }
        });
    }

    private boolean isEmailValid(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEditTextEmailCreate.setError(String.format(getString(R.string.error_invalid_email_not_valid),
                    email));
            return false;
        }
        return isGoodEmail;
    }

    private boolean isUserNameValid(String userName) {
        if (userName.equals("")) {
            mEditTextUsernameCreate.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }


    /**
     * Show error toast to users
     */
    private void showErrorToast(String message) {
        Toast.makeText(CreateAccountActivity.this, message, Toast.LENGTH_LONG).show();
    }
}