package com.udacity.firebase.shoppinglistplusplus.ui.trainerMeals;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.ArrayList;

public class TrainerAddMealItemActivity extends AppCompatActivity implements AddMealItemDialogFragment.EditNameDialogListener {
    private String TAG = TrainerMealActivity.class.getSimpleName();
    private ArrayList<String> mealItems;

    private ArrayAdapter<String> mActiveListItemAdapter;
    private ListView mListView;
    private EditText mEditTextMealName;
    private EditText mEditTextMealDesc;
    private Spinner mSpinnerMealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_add_meal_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.text_add_meal_list));
        }

        mListView = (ListView) findViewById(R.id.list_view_meals_list_items);
        mEditTextMealDesc = (EditText) findViewById(R.id.edit_text_meal_description);
        mEditTextMealName = (EditText) findViewById(R.id.edit_text_meal_name);
        mSpinnerMealType = (Spinner) findViewById(R.id.spinner_meal_type);

        mealItems = new ArrayList<>();

        mActiveListItemAdapter = new ArrayAdapter<>(
                getBaseContext(),
                R.layout.single_meal_item,
                R.id.list_item_meal_item,
                mealItems);
        mListView.setAdapter(mActiveListItemAdapter);
    }

    public void onAddMealPressed(View view) {
        DialogFragment dialog = AddMealItemDialogFragment.newInstance();
        dialog.show(TrainerAddMealItemActivity.this.getFragmentManager(), "AddMealItemDialogFragment");
    }

    @Override
    public void onFinishEditDialog(String mealItem) {
        mealItems.add(mealItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trainer_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            String mealName = mEditTextMealName.getText().toString();
            String mealDesc = mEditTextMealDesc.getText().toString();
            if (!mealName.equals("") && !mealDesc.equals("") && !mealItems.isEmpty() && mSpinnerMealType.getSelectedItemPosition() != 0) {
                Intent intent = new Intent();
                Meal meal = new Meal(mealName, mealItems, mealDesc, mSpinnerMealType.getSelectedItem().toString());
                intent.putExtra(Constants.KEY_MEAL_OBJECT, meal);
                setResult(Constants.TRAINER_ADD_MEAL_ITEM_REQUEST_CODE, intent);
                finish();
            } else {
                Toast.makeText(TrainerAddMealItemActivity.this, getString(R.string.toast_empty_fields), Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
