package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by elif polat on 02.03.2016.
 */
public class Meal {
    private String name;
    private String calorie;
    private String description;
    private String type;

    public Meal() {
    }

    public Meal(String name, String calorie, String description, String type) {
        this.name = name;
        this.calorie = calorie;
        this.description = description;
        this.type = type;
    }

    public String getType() {
        return type;
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
