package com.udacity.firebase.shoppinglistplusplus.ui.activeMealDetails;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveMealItemAdapter extends FirebaseListAdapter<Meal> {

    public ActiveMealItemAdapter(Activity activity, Class<Meal> modelClass, int modelLayout,
                                 Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, final Meal meal, int position) {
        TextView tvMealName = (TextView) view.findViewById(R.id.tv_meal_name);
        TextView tvMealDesc = (TextView) view.findViewById(R.id.tv_meal_description);
        TextView tvMealCalorie = (TextView) view.findViewById(R.id.tv_meal_calorie);
        ImageView imageMealIcon = (ImageView) view.findViewById(R.id.image_meal_ic);
        switch (meal.getType()) {
            case Constants.MEAL_TYPE_BREAKFAST:
                imageMealIcon.setImageResource(R.drawable.ic_breakfast);
                break;
            case Constants.MEAL_TYPE_LUNCH:
                imageMealIcon.setImageResource(R.drawable.ic_lunch);
                break;
            case Constants.MEAL_TYPE_DINNER:
                imageMealIcon.setImageResource(R.drawable.ic_dinner);
                break;
            case Constants.MEAL_TYPE_SNACK:
                imageMealIcon.setImageResource(R.drawable.ic_snack);
                break;
            case Constants.MEAL_TYPE_DETOX:
                imageMealIcon.setImageResource(R.drawable.ic_detox);
                break;
        }
        tvMealName.setText(meal.getName());
        tvMealDesc.setText(meal.getDescription());
        tvMealCalorie.setText("Kalori: " + meal.getCalorie());
    }

}
