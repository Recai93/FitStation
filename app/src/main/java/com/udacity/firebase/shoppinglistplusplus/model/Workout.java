package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by elif polat on 02.03.2016.
 */
public class Workout {
    private String name;
    private String url;
    private String type;

    public Workout() {
    }

    public Workout( String name, String url, String type) {
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
