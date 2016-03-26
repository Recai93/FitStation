package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by elif polat on 02.03.2016.
 */
public class Meal {
    private String name;
    private String calorie;
    private String description;

    public Meal() {
    }

    public Meal(String name, String calorie, String description) {
        this.name = name;
        this.calorie = calorie;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCalorie() {
        return calorie;
    }
}
