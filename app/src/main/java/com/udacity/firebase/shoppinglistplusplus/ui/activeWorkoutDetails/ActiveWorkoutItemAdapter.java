package com.udacity.firebase.shoppinglistplusplus.ui.activeWorkoutDetails;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;
import com.udacity.firebase.shoppinglistplusplus.model.WorkoutList;

/**
 * Created by rajaee on 3/22/16.
 */
public class ActiveWorkoutItemAdapter extends FirebaseListAdapter<Workout> {
    private WorkoutList mWorkoutList;
    private String mListId;
    private String mEncodedEmail;

    public ActiveWorkoutItemAdapter(Activity activity, Class<Workout> modelClass, int modelLayout,
                                 Query ref, String listId, String encodedEmail) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        this.mListId = listId;
        this.mEncodedEmail = encodedEmail;
    }

    public void setShoppingList(WorkoutList workoutList) {
        this.mWorkoutList = workoutList;
        this.notifyDataSetChanged();
    }

    @Override
    protected void populateView(View view, final Workout item, int position) {
        TextView textViewItemName = (TextView) view.findViewById(R.id.text_view_active_list_item_name);
        textViewItemName.setText(item.getName());
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));
    }

}
