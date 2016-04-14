package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by rajaee on 4/8/16.
 */
public class Workout2 {
    private String name;
    private String url;
    private String type;
    private int setNo;
    private int repNo;

    public Workout2() {
    }

    public Workout2(String name, String url, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
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
