package com.udacity.firebase.shoppinglistplusplus.ui.trainerMeasurements;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Measurement;
import com.udacity.firebase.shoppinglistplusplus.ui.sharing.ClientList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

public class TrainerMeasurementActivity extends AppCompatActivity {
    private final String TAG = TrainerMeasurementActivity.class.getSimpleName();

    private EditText mtitle, mtotalweight, mfatFreeMass, mtotalBodyWater, mage, mheight, mbodyMassIndex, mtotalfat,
            mabdomenfat, mtotalfatratio, mabdomenmuscle, mrightarmfat, mleftarmfat, mrightlegfat, mleftlegfat,
            mrightarmmuscle, mleftarmmuscle, mrightlegmuscle, mleftlegmuscle;

    private TextView tvAddClient;
    private String clientEncodedEmail;
    private String trainerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_measurement);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.text_add_measurement));
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.KEY_TRAINER_NAME)) {
            trainerName = intent.getStringExtra(Constants.KEY_TRAINER_NAME);
        }

        tvAddClient = (TextView) findViewById(R.id.tv_add_client);
        mtitle = (EditText) findViewById(R.id.titleEditText);
        mtotalweight = (EditText) findViewById(R.id.totalweightEditText);
        mfatFreeMass = (EditText) findViewById(R.id.fatFreeMassEditText);
        mtotalBodyWater = (EditText) findViewById(R.id.totalBodyWaterEditText);
        mage = (EditText) findViewById(R.id.ageEditText);
        mheight = (EditText) findViewById(R.id.heightEditText);
        mbodyMassIndex = (EditText) findViewById(R.id.bodyMassIndexEditText);
        mtotalfat = (EditText) findViewById(R.id.totalfatEditText);
        mabdomenfat = (EditText) findViewById(R.id.abdomenfatEditText);
        mtotalfatratio = (EditText) findViewById(R.id.totalfatratioEditText);
        mabdomenmuscle = (EditText) findViewById(R.id.abdomenmuscleEditText);
        mrightarmfat = (EditText) findViewById(R.id.rightarmfatEditText);
        mleftarmfat = (EditText) findViewById(R.id.leftarmfatEditText);
        mrightlegfat = (EditText) findViewById(R.id.rightlegfatEditText);
        mleftlegfat = (EditText) findViewById(R.id.leftlegfatEditText);
        mrightarmmuscle = (EditText) findViewById(R.id.rightarmmuscleEditText);
        mleftarmmuscle = (EditText) findViewById(R.id.leftarmmuscleEditText);
        mrightlegmuscle = (EditText) findViewById(R.id.rightlegmuscleEditText);
        mleftlegmuscle = (EditText) findViewById(R.id.leftlegmuscleEditText);

    }

    public void onAddClientPressed(View view) {
        Intent intent = new Intent(TrainerMeasurementActivity.this, ClientList.class);
        startActivityForResult(intent, Constants.FRIEND_LIST_REQUEST_CODE);
    }

    private void onSaveMeasurementPressed() {
        String title = mtitle.getText().toString();
        Double totalweight = Double.parseDouble(mtotalweight.getText().toString());
        Double fatFreeMass = Double.parseDouble(mfatFreeMass.getText().toString());
        Double totalBodyWater = Double.parseDouble(mtotalBodyWater.getText().toString());
        Double age = Double.parseDouble(mage.getText().toString());
        Double height = Double.parseDouble(mheight.getText().toString());
        Double bodyMassIndex = Double.parseDouble(mbodyMassIndex.getText().toString());
        Double totalfat = Double.parseDouble(mtotalfat.getText().toString());
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

        Measurement measurement = new Measurement(title, totalweight, fatFreeMass, totalfat, totalBodyWater, abdomenfat,
                totalfatratio, abdomenmuscle, rightarmfat, leftarmfat, rightlegfat, leftlegfat, rightarmmuscle, leftarmmuscle,
                rightlegmuscle, leftlegmuscle, age, height, bodyMassIndex, timestampCreated, trainerName);

        Firebase workoutListRef = new Firebase(Constants.FIREBASE_URL_CLIENT_MEASUREMENTS).child(clientEncodedEmail);

        Firebase clientInfoRef = new Firebase(Constants.FIREBASE_URL_CLIENT_INFO).child(clientEncodedEmail);
        clientInfoRef.child(Constants.FIREBASE_PROPERTY_CLIENT_INFO_WEIGHT).setValue(totalweight);
        clientInfoRef.child(Constants.FIREBASE_PROPERTY_TIMESTAMP).setValue(ServerValue.TIMESTAMP);


        Firebase ref = workoutListRef.push();
        String pushId = ref.getKey();

        workoutListRef.child(pushId).setValue(measurement);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trainer_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            if (!mtitle.getText().toString().equals("") &&
                    !mtotalweight.getText().toString().equals("") &&
                    !mfatFreeMass.getText().toString().equals("") &&
                    !mtotalBodyWater.getText().toString().equals("") &&
                    !mage.getText().toString().equals("") &&
                    !mheight.getText().toString().equals("") &&
                    !mbodyMassIndex.getText().toString().equals("") &&
                    !mtotalfat.getText().toString().equals("") &&
                    !mabdomenfat.getText().toString().equals("") &&
                    !mtotalfatratio.getText().toString().equals("") &&
                    !mabdomenmuscle.getText().toString().equals("") &&
                    !mrightarmfat.getText().toString().equals("") &&
                    !mleftarmfat.getText().toString().equals("") &&
                    !mrightlegfat.getText().toString().equals("") &&
                    !mleftlegfat.getText().toString().equals("") &&
                    !mrightarmmuscle.getText().toString().equals("") &&
                    !mleftarmmuscle.getText().toString().equals("") &&
                    !mrightlegmuscle.getText().toString().equals("") &&
                    !mleftlegmuscle.getText().toString().equals("") &&
                    !tvAddClient.getText().toString().equals("")) {
                onSaveMeasurementPressed();
            } else {
                Toast.makeText(TrainerMeasurementActivity.this, getString(R.string.toast_empty_fields), Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
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
