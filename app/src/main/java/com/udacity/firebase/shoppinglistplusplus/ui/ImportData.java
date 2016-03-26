package com.udacity.firebase.shoppinglistplusplus.ui;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.model.Workout;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by elif polat on 06.03.2016.
 */
public class ImportData {

    static String[] ChestName = {
            "Flat Bench Barbell Press",
            "Incline Bench Barbell Press",
            "Decline Bench Barbell Press",
            "Flat Bench Dumbell Press",
            "Incline Bench Dumbell Press",
            "Decline Bench Dumbell Press",
            "Pectoral Fly",
            "Flat Bench Dumbell Fly",
            "Incline Bench Dumbell Fly",
            "Decline Bench Dumbell Fly",
            "Cable Crossover",
            "Pullover",
            "Barbell Pullover",
            "Dips",
            "Push Up",
            "Traviling Push Up"
    };

    static String[] ChestUrl = {
            "Flat Bench Barbell Press",
            "Incline Bench Barbell Press",
            "Decline Bench Barbell Press",
            "Flat Bench Dumbell Press",
            "Incline Bench Dumbell Press",
            "Decline Bench Dumbell Press",
            "Pectoral Fly",
            "Flat Bench Dumbell Fly",
            "Incline Bench Dumbell Fly",
            "Decline Bench Dumbell Fly",
            "Cable Crossover",
            "Pullover",
            "Barbell Pullover",
            "Dips",
            "Push Up",
            "Traviling Push Up"
    };

    static String[] BackName = {
            "Pull Ups",
            "Chin ups",
            "Lat Pulldown",
            "Seated Row",
            "Bentover Barbell Row",
            "Bentover Cable Row",
            "One Arm Dumbell Row",
            "T-Bar Row",
            "Inverted Row",
            "Chest Supported Machine Row"
    };

    static String[] BackUrl = {
            "Pull Ups",
            "Chin ups",
            "Lat Pulldown",
            "Seated Row",
            "Bentover Barbell Row",
            "Bentover Cable Row",
            "One Arm Dumbell Row",
            "T-Bar Row",
            "Inverted Row",
            "Chest Supported Machine Row"
    };


    static String[] BicepsName = {
            "Standing Barbell Biceps Curl",
            " Standing Dumbell Biceps Curl",
            "Alternate Dumbell Biceps Curl",
            "Barbell Preacher Curl",
            "Dumbell Preacher Curl",
            "Seated Dumbell Biceps Curl",
            "Incline Dumbell Biceps Curl",
            "Hammer Curl",
            "Concentration Curl",
            "Cable Biceps Curl",
            "Biceps Curl Machine"
    };
    static String[] BicepsUrl = {
            "Standing Barbell Biceps Curl",
            " Standing Dumbell Biceps Curl",
            "Alternate Dumbell Biceps Curl",
            "Barbell Preacher Curl",
            "Dumbell Preacher Curl",
            "Seated Dumbell Biceps Curl",
            "Incline Dumbell Biceps Curl",
            "Hammer Curl",
            "Concentration Curl",
            "Cable Biceps Curl",
            "Biceps Curl Machine"
    };
    static String[] TricepsName = {
            "Flat Close Grip Bench Press",
            "Decline Close Grip Bench Press",
            "Lying Barbell Triceps Extension",
            "Lying Dumbell Triceps Extension",
            "Triceps Extension Machine",
            "Cable Pushdown",
            "Cable Reverse Pushdown",
            "Rope",
            "Close Grip Push Up",
            "Dips",
            "Skull Crusher"
    };

    static String[] TricepsUrl = {
            "Flat Close Grip Bench Press",
            "Decline Close Grip Bench Press",
            "Lying Barbell Triceps Extension",
            "Lying Dumbell Triceps Extension",
            "Triceps Extension Machine",
            "Cable Pushdown",
            "Cable Reverse Pushdown",
            "Rope",
            "Close Grip Push Up",
            "Dips",
            "Skull Crusher"
    };

    static String[] ShoulderName = {"Seated Overhead Barbell Press",
            "Seated Overhead Dumbell Press",
            "Standing Overhead Barbell Press",
            "Standing Overhead Dumbell Press",
            "Shoulder Press Machine",
            "Arnold Press",
            "Barbell Arnold Press",
            "Dumbell Upright",
            "Barbell Upright",
            "Dumbell Lateral Raises",
            "Cable Lateral Raises",
            "Lateral Machine",
            "Cable Front Raises",
            "Dumbell Front Raises",
            "Cable Rear Deltoid",
            "Rear Deltoid Machine",
            "Dumbell Rear Deltoid",
            "Military Press"
    };

    static String[] ShoulderUrl = {"Seated Overhead Barbell Press",
            "Seated Overhead Dumbell Press",
            "Standing Overhead Barbell Press",
            "Standing Overhead Dumbell Press",
            "Shoulder Press Machine",
            "Arnold Press",
            "Barbell Arnold Press",
            "Dumbell Upright",
            "Barbell Upright",
            "Dumbell Lateral Raises",
            "Cable Lateral Raises",
            "Lateral Machine",
            "Cable Front Raises",
            "Dumbell Front Raises",
            "Cable Rear Deltoid",
            "Rear Deltoid Machine",
            "Dumbell Rear Deltoid",
            "Military Press"
    };

    static String[] LegName = {
            "Barbell Squat",
            "Dumbell Squat",
            "Swissball Squat",
            "Barbell Lunge",
            "Dumbell Lunge",
            "Walking Lunge",
            "Back And Front Lunge",
            "Brazilian Lunge",
            "Leg Press Machine",
            "Leg Extension",
            "Leg Curl",
            "Jump Squat",
            "Jump Lunge",
            "Barbell Split Squat",
            "Dumbell Split Squat",
            "Barbell Romanian Deadlift",
            "Barbell Straight Leg Deadlift",
            "Barbell Sumo Squat",
            "Glute Machine",
            "Good Morning",
            "Hyperextension"
    };

    static String[] LegUrl = {
            "Barbell Squat",
            "Dumbell Squat",
            "Swissball Squat",
            "Barbell Lunge",
            "Dumbell Lunge",
            "Walking Lunge",
            "Back And Front Lunge",
            "Brazilian Lunge",
            "Leg Press Machine",
            "Leg Extension",
            "Leg Curl",
            "Jump Squat",
            "Jump Lunge",
            "Barbell Split Squat",
            "Dumbell Split Squat",
            "Barbell Romanian Deadlift",
            "Barbell Straight Leg Deadlift",
            "Barbell Sumo Squat",
            "Glute Machine",
            "Good Morning",
            "Hyperextension"
    };


    static String[] FunctionalName = {
            "Keetbell Swing",
            "Step Jump Squat",
            "Box Jump Squat",
            "Mointain Climber",
            "Jumping Jack",
            "Knee Tuck",
            "Burpess",
            "Push ups",
            "Russian Twist",
            "Plank",
            "Side Plank"
    };

    static String[] FunctionalUrl = {
            "Keetbell Swing",
            "Step Jump Squat",
            "Box Jump Squat",
            "Mointain Climber",
            "Jumping Jack",
            "Knee Tuck",
            "Burpess",
            "Push ups",
            "Russian Twist",
            "Plank",
            "Side Plank"
    };

    public static HashMap<String, List<Workout>> getData() {
        HashMap<String, List<Workout>> expandableListDetail = new HashMap<String, List<Workout>>();

        List<Workout> chestList = new ArrayList<>();
        List<Workout> backList = new ArrayList<>();
        List<Workout> bicepsList = new ArrayList<>();
        List<Workout> tricepsList = new ArrayList<>();
        List<Workout> shoulderList = new ArrayList<>();
        List<Workout> legList = new ArrayList<>();
        List<Workout> functionalList = new ArrayList<>();

        Firebase firebaseReff = new Firebase(Constants.FIREBASE_URL + "workout");


        for (int i = 0; i < ChestName.length; i++) {
            chestList.add(new Workout(ChestName[i], ChestUrl[i]));
            Firebase fbRef = firebaseReff.child("chest").push();
            firebaseReff.child("chest").child(fbRef.getKey()).setValue(chestList.get(i));
        }
//        firebaseReff.child("chest").setValue(chestList);


        for (int i = 0; i < BackName.length; i++) {
            backList.add(new Workout(BackName[i], BackUrl[i]));
            Firebase fbRef = firebaseReff.child("back").push();
            firebaseReff.child("back").child(fbRef.getKey()).setValue(backList.get(i));
        }
//        firebaseReff.child("back").setValue(backList);
//
        for (int i = 0; i < BicepsName.length; i++) {
            bicepsList.add(new Workout(BicepsName[i], BicepsUrl[i]));
            Firebase fbRef = firebaseReff.child("biceps").push();
            firebaseReff.child("biceps").child(fbRef.getKey()).setValue(bicepsList.get(i));
        }
//        firebaseReff.child("biceps").setValue(bicepsList);
//
        for (int i = 0; i < TricepsName.length; i++) {
            tricepsList.add(new Workout(TricepsName[i], TricepsUrl[i]));
            Firebase fbRef = firebaseReff.child("triceps").push();
            firebaseReff.child("triceps").child(fbRef.getKey()).setValue(tricepsList.get(i));
        }
//        firebaseReff.child("triceps").setValue(tricepsList);
//
        for (int i = 0; i < ShoulderName.length; i++) {
            shoulderList.add(new Workout(ShoulderName[i], ShoulderUrl[i]));
            Firebase fbRef = firebaseReff.child("shoulder").push();
            firebaseReff.child("shoulder").child(fbRef.getKey()).setValue(shoulderList.get(i));
        }
//        firebaseReff.child("shoulder").setValue(shoulderList);
//
        for (int i = 0; i < LegName.length; i++) {
            legList.add(new Workout(LegName[i], LegUrl[i]));
            Firebase fbRef = firebaseReff.child("leg").push();
            firebaseReff.child("leg").child(fbRef.getKey()).setValue(legList.get(i));
        }
//        firebaseReff.child("leg").setValue(legList);
//
        for (int i = 0; i < FunctionalName.length; i++) {
            functionalList.add(new Workout(FunctionalName[i], FunctionalUrl[i]));
            Firebase fbRef = firebaseReff.child("functional").push();
            firebaseReff.child("functional").child(fbRef.getKey()).setValue(functionalList.get(i));
        }
//        firebaseReff.child("functional").setValue(functionalList);
//
//
//        expandableListDetail.put("CHEST", chestList);
//        expandableListDetail.put("BACK", backList);
//        expandableListDetail.put("BICEPS", bicepsList);
//        expandableListDetail.put("TRICEPS", tricepsList);
//        expandableListDetail.put("SHOULDER", shoulderList);
//        expandableListDetail.put("LEG", legList);
//        expandableListDetail.put("FUNCTIONAL",functionalList);

        return expandableListDetail;

    }


}
