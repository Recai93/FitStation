package com.udacity.firebase.shoppinglistplusplus.ui.activeMeasurement;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Measurement;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.ClientList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

public class TrainerMeasurementActivity extends AppCompatActivity {
    private final String TAG = TrainerMeasurementActivity.class.getSimpleName();

    private EditText mtitle, mtotalweight, mtotalmuscle, mtotalfat, mtotalmuscleratio, mabdomenfat, mtotalfatratio,
            mabdomenmuscle, mrightarmfat, mleftarmfat, mrightlegfat, mleftlegfat, mrightarmmuscle, mleftarmmuscle,
            mrightlegmuscle, mleftlegmuscle;

    private TextView tvAddClient;
    private String clientEncodedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_measurement);

        tvAddClient = (TextView) findViewById(R.id.tv_add_client);

        mtitle = (EditText) findViewById(R.id.titleEditText);
        mtotalweight = (EditText) findViewById(R.id.totalweightEditText);
        mtotalmuscle = (EditText) findViewById(R.id.totalmuscleEditText);
        mtotalfat = (EditText) findViewById(R.id.totalfatEditText);
        mtotalmuscleratio = (EditText) findViewById(R.id.totalmuscleratioEditText);
        mabdomenfat = (EditText) findViewById(R.id.abdomenfatEditText);
        mtotalfatratio = (EditText) findViewById(R.id.totalfatratioEditText);
        mabdomenmuscle = (EditText) findViewById(R.id.abdomenmuscleEditText);
        mrightarmfat = (EditText) findViewById(R.id.rightarmfatEditText);
        mleftarmfat = (EditText) findViewById(R.id.leftarmfatEditText);
        mrightlegfat = (EditText) findViewById(R.id.rightlegfatEditText);
        mleftlegfat = (EditText) findViewById(R.id.leftarmfatEditText);
        mrightarmmuscle = (EditText) findViewById(R.id.rightarmmuscleEditText);
        mleftarmmuscle = (EditText) findViewById(R.id.leftarmmuscleEditText);
        mrightlegmuscle = (EditText) findViewById(R.id.rightlegmuscleEditText);
        mleftlegmuscle = (EditText) findViewById(R.id.leftlegmuscleEditText);

    }

    public void onAddClientPressed(View view) {
        Intent intent = new Intent(TrainerMeasurementActivity.this, ClientList.class);
        startActivityForResult(intent, Constants.FRIEND_LIST_REQUEST_CODE);
    }

    public void onSaveMeasurementPressed(View view) {
        String title = mtitle.getText().toString();
        Double totalweight = Double.parseDouble(mtotalweight.getText().toString());
        Double totalmuscle = Double.parseDouble(mtotalmuscle.getText().toString());
        Double totalfat = Double.parseDouble(mtotalfat.getText().toString());
        Double totalmuscleratio = Double.parseDouble(mtotalmuscleratio.getText().toString());
        Double abdomenfat = Double.parseDouble(mabdomenfat.getText().toString());
        Double totalfatratio = Double.parseDouble(mtotalfatratio.getText().toString());
        Double abdomenmuscle = Double.parseDouble(mabdomenmuscle.getText().toString());
        Double rightarmfat = Double.parseDouble(mrightarmfat.getText().toString());
        Double leftarmfat = Double.parseDouble(mleftarmfat.getText().toString());
        Double rightlegfat = Double.parseDouble(mrightlegfat.getText().toString());
        Double leftlegfat = Double.parseDouble(mleftlegfat.getText().toString());
        Double rightarmmuscle = Double.parseDouble(mrightarmmuscle.getText().toString());
        Double leftarmmuscle = Double.parseDouble(mleftarmmuscle.getText().toString());
        Double rightlegmuscle = Double.parseDouble(mrightlegmuscle.getText().toString());
        Double leftlegmuscle = Double.parseDouble(mleftlegmuscle.getText().toString());

        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        String creator = PreferenceManager.getDefaultSharedPreferences(TrainerMeasurementActivity.this).getString(Constants.KEY_ENCODED_EMAIL, null);
        Measurement measurement = new Measurement(title, totalweight, totalmuscle, totalfat, totalmuscleratio, abdomenfat, totalfatratio,
                abdomenmuscle, rightarmfat, leftarmfat, rightlegfat, leftlegfat, rightarmmuscle, leftarmmuscle, rightlegmuscle, leftlegmuscle,
                0, 0, 0, timestampCreated, creator);

        Firebase workoutListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEASUREMENTS).child(clientEncodedEmail);

        Firebase ref = workoutListRef.push();
        String pushId = ref.getKey();

        workoutListRef.child(pushId).setValue(measurement);
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
