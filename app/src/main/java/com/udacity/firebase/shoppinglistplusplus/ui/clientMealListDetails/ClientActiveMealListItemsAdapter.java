package com.udacity.firebase.shoppinglistplusplus.ui.clientMealListDetails;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ClientActiveMealListItemsAdapter extends FirebaseListAdapter<Meal> {

    public ClientActiveMealListItemsAdapter(Activity activity, Class<Meal> modelClass, int modelLayout,
                                            Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, final Meal meal, int position) {
        TextView tvMealName = (TextView) view.findViewById(R.id.tv_meal_name);
        TextView tvMealDesc = (TextView) view.findViewById(R.id.tv_meal_description);
        TextView tvMealItems = (TextView) view.findViewById(R.id.tv_meal_items);
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
        StringBuilder items = new StringBuilder();
        for (String item : meal.getItems()) {
            items.append(item).append("\n");
        }
        tvMealItems.setText(items.toString().trim());
    }

}
