package com.udacity.firebase.shoppinglistplusplus.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by rajaee on 3/20/16.
 */
public class MealList {
    private String creator;
    private HashMap<String, Object> timestampCreated;
    private List<Meal> mealList;

    public MealList() {
    }

    public MealList(String creator, HashMap<String, Object> timestampCreated, List<Meal> mealList) {
        this.creator = creator;
        this.timestampCreated = timestampCreated;
        this.mealList = mealList;
    }

    public String getCreator() {
        return creator;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    public List<Meal> getMealList() {
        return mealList;
    }
}
