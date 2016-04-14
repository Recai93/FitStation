package com.udacity.firebase.shoppinglistplusplus.model;

import java.io.Serializable;

public class DailyMeal2 implements Serializable {
    private String name;
    private String calorie;

    public DailyMeal2() {
    }

    public DailyMeal2(String name, String calorie) {
        this.name = name;
        this.calorie = calorie;
    }


    public String getName() {
        return name;
    }

    public String getCalorie() {
        return calorie;
    }
}
