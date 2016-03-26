package com.udacity.firebase.shoppinglistplusplus.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by elif polat on 02.03.2016.
 */
public class Measurement implements Serializable{
    private String title ;
    private double totalweight;
    private double totalmuscle;
    private double totalmuscleratio;
    private double totalfat;
    private double totalfatratio;
    private double abdomenfat;
    private double abdomenmuscle;
    private double rightarmfat;
    private double leftarmfat;
    private double rightlegfat;
    private double leftlegfat;
    private double rightarmmuscle;
    private double leftarmmuscle;
    private double rightlegmuscle;
    private double leftlegmuscle;
    private HashMap<String, Object> timestampCreated;
    private String creator;

    public Measurement() {
    }

    public Measurement(String title, double totalweight, double totalmuscle,
                       double totalfat, double totalmuscleratio, double abdomenfat,
                       double totalfatratio, double abdomenmuscle, double rightarmfat,
                       double leftarmfat, double rightlegfat, double leftlegfat,
                       double rightarmmuscle, double leftarmmuscle, double rightlegmuscle,
                       double leftlegmuscle, HashMap<String, Object> timestampCreated,
                       String creator) {
        this.title = title;
        this.totalweight = totalweight;
        this.totalmuscle = totalmuscle;
        this.totalfat = totalfat;
        this.totalmuscleratio = totalmuscleratio;
        this.abdomenfat = abdomenfat;
        this.totalfatratio = totalfatratio;
        this.abdomenmuscle = abdomenmuscle;
        this.rightarmfat = rightarmfat;
        this.leftarmfat = leftarmfat;
        this.rightlegfat = rightlegfat;
        this.leftlegfat = leftlegfat;
        this.rightarmmuscle = rightarmmuscle;
        this.leftarmmuscle = leftarmmuscle;
        this.rightlegmuscle = rightlegmuscle;
        this.leftlegmuscle = leftlegmuscle;
        this.timestampCreated = timestampCreated;
        this.creator = creator;

    }

    public double getAbdomenmuscle() {
        return abdomenmuscle;
    }

    public double getLeftlegmuscle() {
        return leftlegmuscle;
    }

    public double getRightlegmuscle() {
        return rightlegmuscle;
    }

    public double getLeftarmmuscle() {
        return leftarmmuscle;
    }

    public double getRightarmmuscle() {
        return rightarmmuscle;
    }

    public double getRightlegfat() {
        return rightlegfat;
    }

    public String getTitle() {
        return title;
    }

    public double getTotalmuscle() {
        return totalmuscle;
    }

    public double getTotalmuscleratio() {
        return totalmuscleratio;
    }

    public double getTotalfat() {
        return totalfat;
    }

    public double getTotalfatratio() {
        return totalfatratio;
    }

    public double getRightarmfat() {
        return rightarmfat;
    }

    public double getAbdomenfat() {
        return abdomenfat;
    }

    public double getLeftarmfat() {
        return leftarmfat;
    }

    public double getLeftlegfat() {
        return leftlegfat;
    }

    public double getTotalweight() {
        return totalweight;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    public String getCreator() {
        return creator;
    }

}


