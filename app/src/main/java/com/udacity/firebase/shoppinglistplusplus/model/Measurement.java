package com.udacity.firebase.shoppinglistplusplus.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by elif polat on 02.03.2016.
 */
public class Measurement implements Serializable{
    private String title ;
    private double totalWeight;
    private double fatFreeMass;
    private double totalBodyWater;
    private double totalFat;
    private double totalFatRatio;
    private double age;
    private double height;
    private double bodyMassIndex;

    private double abdomenFat;
    private double abdomenMuscle;
    private double rightArmFat;
    private double leftArmFat;
    private double rightLegFat;
    private double leftLegFat;
    private double rightArmMuscle;
    private double leftArmMuscle;
    private double rightLegMuscle;
    private double leftLegMuscle;
    private HashMap<String, Object> timestampCreated;
    private String creator;

    public Measurement() {
    }

    public Measurement(String title, double totalWeight, double fatFreeMass,
                       double totalFat, double totalBodyWater, double abdomenFat,
                       double totalFatRatio, double abdomenMuscle, double rightArmFat,
                       double leftArmFat, double rightLegFat, double leftLegFat,
                       double rightArmMuscle, double leftArmMuscle, double rightLegMuscle,
                       double leftLegMuscle, double age, double height, double bodyMassIndex,
                       HashMap<String, Object> timestampCreated, String creator) {
        this.title = title;
        this.totalWeight = totalWeight;
        this.fatFreeMass = fatFreeMass;
        this.totalFat = totalFat;
        this.totalBodyWater = totalBodyWater;
        this.abdomenFat = abdomenFat;
        this.totalFatRatio = totalFatRatio;
        this.abdomenMuscle = abdomenMuscle;
        this.rightArmFat = rightArmFat;
        this.leftArmFat = leftArmFat;
        this.rightLegFat = rightLegFat;
        this.leftLegFat = leftLegFat;
        this.rightArmMuscle = rightArmMuscle;
        this.leftArmMuscle = leftArmMuscle;
        this.rightLegMuscle = rightLegMuscle;
        this.leftLegMuscle = leftLegMuscle;
        this.age = age;
        this.height = height;
        this.bodyMassIndex = bodyMassIndex;
        this.timestampCreated = timestampCreated;
        this.creator = creator;
    }

    public double getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public double getAbdomenMuscle() {
        return abdomenMuscle;
    }

    public double getLeftLegMuscle() {
        return leftLegMuscle;
    }

    public double getRightLegMuscle() {
        return rightLegMuscle;
    }

    public double getLeftArmMuscle() {
        return leftArmMuscle;
    }

    public double getRightArmMuscle() {
        return rightArmMuscle;
    }

    public double getRightLegFat() {
        return rightLegFat;
    }

    public String getTitle() {
        return title;
    }

    public double getFatFreeMass() {
        return fatFreeMass;
    }

    public double getTotalBodyWater() {
        return totalBodyWater;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getTotalFatRatio() {
        return totalFatRatio;
    }

    public double getRightArmFat() {
        return rightArmFat;
    }

    public double getAbdomenFat() {
        return abdomenFat;
    }

    public double getLeftArmFat() {
        return leftArmFat;
    }

    public double getLeftLegFat() {
        return leftLegFat;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    public String getCreator() {
        return creator;
    }

}


