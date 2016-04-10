package com.udacity.firebase.shoppinglistplusplus.model;

import java.io.Serializable;
import java.util.List;

public class Meal implements Serializable {
    private List<String> items;
    private String name;
    private String description;
    private String type;

    public Meal() {
    }

    public Meal(String name, List<String> items, String description, String type) {
        this.name = name;
        this.items = items;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
    }

}
