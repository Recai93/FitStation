package com.udacity.firebase.shoppinglistplusplus.model;

public class ClientInfo {
    private int waterQuantity;
    private double weight;
    private double calorie;

    public ClientInfo() {
    }

    public ClientInfo(int waterQuantity, double weight, double calorie) {
        this.waterQuantity = waterQuantity;
        this.weight = weight;
        this.calorie = calorie;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public double getWeight() {
        return weight;
    }

    public double getCalorie() {
        return calorie;
    }
}
