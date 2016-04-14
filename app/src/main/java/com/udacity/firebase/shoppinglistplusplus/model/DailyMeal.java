package com.udacity.firebase.shoppinglistplusplus.model;

import java.io.Serializable;

public class DailyMeal implements Serializable{
    private String name;
    private String calorie;
    private String quantity;
    private String type;

    public DailyMeal() {
    }

    public DailyMeal(String name, String calorie, String quantity, String type) {
        this.name = name;
        this.calorie = calorie;
        this.type = type;
        this.quantity = quantity;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCalorie() {
        return calorie;
    }
}
