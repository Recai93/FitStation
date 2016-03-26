package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by elif polat on 02.03.2016.
 */
public class Workout {
    private String name;
    private String url;

    public Workout() {
    }

    public Workout( String name, String url) {
        this.name = name;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }



}
