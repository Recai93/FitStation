package com.udacity.firebase.shoppinglistplusplus.ui.trainerWorkouts;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrainerWorkoutItemAdapter extends FirebaseListAdapter<Workout> {

    private ArrayList<Integer> selectedIds = new ArrayList<>();
    private Map<Integer, DataClass> dataMap = new HashMap<>();

    public TrainerWorkoutItemAdapter(Activity activity, Class<Workout> modelClass, int modelLayout,
                                     Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, final Workout item, final int position) {
        TextView tvWorkoutName = (TextView) view.findViewById(R.id.tv_workout_name);
        final ImageView imageButton = (ImageView) view.findViewById(R.id.iv_toggle_selected);
        final TextView tvSetNo = (TextView) view.findViewById(R.id.tv_workout_set_no);
        final TextView tvRepNo = (TextView) view.findViewById(R.id.tv_workout_rep_no);

        imageButton.setTag(position);

        tvWorkoutName.setText(item.getName());

        if (selectedIds.contains(position)) {
            if(dataMap.containsKey(position)){
                tvSetNo.setText(String.valueOf(dataMap.get(position).getSetNo()));
                tvRepNo.setText(String.valueOf(dataMap.get(position).getRepNo()));
                imageButton.setImageResource(R.drawable.ic_shared_check);
            }
        } else {
            dataMap.remove(position);
            imageButton.setImageResource(R.drawable.icon_add_friend);
            tvSetNo.setText("--");
            tvRepNo.setText("--");
        }
    }

    public boolean isSelected(Integer position) {
        return selectedIds.contains(position);
    }


    public void toggleSelected(Integer position) {
        if (selectedIds.contains(position)) {
            selectedIds.remove(position);
        } else {
            selectedIds.add(position);
        }
        notifyDataSetChanged();
    }

    public void addData(int position, int setNo, int repNo) {
        dataMap.put(position, new DataClass(setNo, repNo));
    }

    private class DataClass {
        private int setNo;
        private int repNo;

        public DataClass(int setNo, int repNo) {
            this.setNo = setNo;
            this.repNo = repNo;
        }

        public int getRepNo() {
            return repNo;
        }

        public int getSetNo() {
            return setNo;
        }
    }

}

