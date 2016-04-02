package com.udacity.firebase.shoppinglistplusplus.model;

import java.util.HashMap;
import java.util.List;

public class WorkoutList {
    private List<Workout> workoutList;
    private String creator;
    private HashMap<String, Object> timestampCreated;
    private String title;

    public WorkoutList() {
    }

    public WorkoutList(List<Workout> workoutList, String creator, String title, HashMap<String, Object> timestampCreated) {
        this.workoutList = workoutList;
        this.creator = creator;
        this.timestampCreated = timestampCreated;
        this.title = title;
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

    public String getTitle() {
        return title;
    }
}
