package com.udacity.firebase.shoppinglistplusplus.ui.clientAddMeal;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.DailyMeal;

public class AutocompleteDailyMealAdapter extends FirebaseListAdapter<DailyMeal> {

    public AutocompleteDailyMealAdapter(Activity activity, Class<DailyMeal> modelClass,
                                        int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, final DailyMeal dailyMeal) {
        TextView tvMealName = (TextView) view.findViewById(R.id.tv_meal_name);
        tvMealName.setText(dailyMeal.getName());
    }

}