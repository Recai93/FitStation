package com.udacity.firebase.shoppinglistplusplus.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by rajaee on 3/20/16.
 */
public class WorkoutList {
    private List<Workout> workoutList;
    private String creator;
    private HashMap<String, Object> timestampCreated;

    public WorkoutList() {
    }

    public WorkoutList(List<Workout> workoutList, String creator, HashMap<String, Object> timestampCreated) {
        this.workoutList = workoutList;
        this.creator = creator;
        this.timestampCreated = timestampCreated;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public String getCreator() {
        return creator;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }
}
