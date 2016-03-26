package com.udacity.firebase.shoppinglistplusplus.ui.activeMealDetails;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.model.MealList;

/**
 * Created by rajaee on 3/22/16.
 */
public class ActiveMealItemAdapter extends FirebaseListAdapter<Meal> {
    private MealList mMealList;
    private String mListId;
    private String mEncodedEmail;

    public ActiveMealItemAdapter(Activity activity, Class<Meal> modelClass, int modelLayout,
                                 Query ref, String listId, String encodedEmail) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        this.mListId = listId;
        this.mEncodedEmail = encodedEmail;
    }

    public void setShoppingList(MealList workoutList) {
        this.mMealList = workoutList;
        this.notifyDataSetChanged();
    }

    @Override
    protected void populateView(View view, final Meal item, int position) {
        TextView textViewItemName = (TextView) view.findViewById(R.id.text_view_meal_name);
        textViewItemName.setText(item.getName());
    }

}
