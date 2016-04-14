package com.udacity.firebase.shoppinglistplusplus.ui.clientMeals;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

public class ClientMealAdapter extends FirebaseListAdapter<MealList> {

    public ClientMealAdapter(Activity activity, Class<MealList> modelClass, int modelLayout,
                             Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, MealList mealList) {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_meal_title);
        TextView tvCreatedByTrainer = (TextView) view.findViewById(R.id.tv_created_by_trainer);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);

        String date = Utils.getDate((long) mealList.getTimestampCreated().get(Constants.FIREBASE_PROPERTY_TIMESTAMP));
        tvTitle.setText(mealList.getTitle());
        tvCreatedByTrainer.setText(mealList.getCreator());
        tvDate.setText(date);
    }
}