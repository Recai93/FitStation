package com.udacity.firebase.shoppinglistplusplus.model;

import java.io.Serializable;

public class Workout implements Serializable {
    private String name;
    private String url;
    private String type;
    private int setNo;
    private int repNo;

    public Workout() {
    }

    public Workout(String name, String url, String type, int setNo, int repNo) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.setNo = setNo;
        this.repNo = repNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
    }

    public void setRepNo(int repNo) {
        this.repNo = repNo;
    }

    public int getSetNo() {
        return setNo;
    }

    public int getRepNo() {
        return repNo;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }
}
