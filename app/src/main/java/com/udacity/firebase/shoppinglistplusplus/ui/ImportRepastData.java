package com.udacity.firebase.shoppinglistplusplus.ui;

import com.udacity.firebase.shoppinglistplusplus.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by elif polat on 19.03.2016.
 */
public class ImportRepastData {
    private String[] repastTypes = {
            "Kahvalti",
            "Ogle Yemegi",
            "Ara Ogun",
            "Aksam Yemegi",
            "Detoks"
    };

    public HashMap<String, List<Meal>> getData() {
        HashMap<String, List<Meal>> map = new HashMap<>();
        for (int i = 0; i < repastTypes.length; i++) {
            map.put(repastTypes[i], new ArrayList<Meal>());
        }
        return map;
    }
}
