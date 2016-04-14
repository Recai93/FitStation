package com.udacity.firebase.shoppinglistplusplus.ui.trainerMeals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class TrainerMealAdapter extends ArrayAdapter<Meal>{
    public TrainerMealAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.single_client_meal_item, parent, false);
        }
        TextView tvMealName = (TextView) convertView.findViewById(R.id.tv_meal_name);
        TextView tvMealDesc = (TextView) convertView.findViewById(R.id.tv_meal_description);
        ImageView imageMealIcon = (ImageView) convertView.findViewById(R.id.image_meal_ic);

        Meal meal = getItem(position);

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
        return convertView;
    }
}
