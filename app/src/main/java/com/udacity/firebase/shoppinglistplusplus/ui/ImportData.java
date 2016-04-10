package com.udacity.firebase.shoppinglistplusplus.ui;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.model.Workout2;
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
            "https://www.youtube.com/watch?v=S6L4uwH0ycY",
            "https://www.youtube.com/watch?v=yUY1B3I9EJA",
            "https://www.youtube.com/watch?v=qX2o_FiQeDg",
            "https://www.youtube.com/watch?v=pqFZKi1o2X8",
            "https://www.youtube.com/watch?v=WxWlCfZUt6w",
            "https://www.youtube.com/watch?v=Pf1nDoqx_1A",
            "https://www.youtube.com/watch?v=wvL4nE6XxfQ",
            "https://www.youtube.com/watch?v=sAdTZaCW5Vc",
            "https://www.youtube.com/watch?v=kUIQPABCmz8",
            "https://www.youtube.com/watch?v=IMALXhhHRKM",
            "https://www.youtube.com/watch?v=-PThCISYkdM",
            "https://www.youtube.com/watch?v=tpLnfSQJ0gg",
            "https://www.youtube.com/watch?v=3f3W-3TMJgA",
            "https://youtu.be/72gpRKE_CZI",
            "https://www.youtube.com/watch?v=oKIecYcjWIQ",
            "https://www.youtube.com/watch?v=V3Wd7bCGPfE"
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
            "https://www.youtube.com/watch?v=iUNoLR0pYjY",
            "https://www.youtube.com/watch?v=b-ztMQpj8yc",
            "https://www.youtube.com/watch?v=u3gQT2aMVaI",
            "https://www.youtube.com/watch?v=7qK7x-d8V2A",
            "https://www.youtube.com/watch?v=B1T6ZYrPAy4",
            "https://www.youtube.com/watch?v=dES3MxCj-zI",
            "https://www.youtube.com/watch?v=-koP10y1qZI",
            "https://www.youtube.com/watch?v=j3Igk5nyZE4",
            "https://www.youtube.com/watch?v=5LZa9TzkjRg",
            "https://www.youtube.com/watch?v=uvSLtHpEUws"
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
            "https://www.youtube.com/watch?v=LY1V6UbRHFM",
            "https://www.youtube.com/watch?v=av7-8igSXTs",
            "https://www.youtube.com/watch?v=sAq_ocpRh_I",
            "https://www.youtube.com/watch?v=DoCWeUBA0Gs",
            "https://www.youtube.com/watch?v=l9BHpnkLwMA",
            "https://www.youtube.com/watch?v=soxrZlIl35U",
            "https://www.youtube.com/watch?v=b4jOP-spQW8",
            "https://www.youtube.com/watch?v=zC3nLlEvin4",
            "https://www.youtube.com/watch?v=Jvj2wV0vOYU",
            "https://www.youtube.com/watch?v=c7wc00q7HMc",
            "https://www.youtube.com/watch?v=PmPUIqa4vSo"

    };
    static String[] TricepsUrl = {
            "https://www.youtube.com/watch?v=nEF0bv2FW94",
            "https://www.youtube.com/watch?v=0XoW1gnDARM",
            "https://www.youtube.com/watch?v=Eizj7tuWMvw",
            "https://www.youtube.com/watch?v=MO_03opCc0g",
            "https://www.youtube.com/watch?v=qCF_hG27jVU",
            "https://www.youtube.com/watch?v=2-LAMcpzODU",
            "https://www.youtube.com/watch?v=kuSFFIY4MpU",
            "https://www.youtube.com/watch?v=vB5OHsJ3EME&feature=youtu.be",
            "https://www.youtube.com/watch?v=G2mlaEfpEIM",
            "https://youtu.be/wjUmnZH528Y",
            "https://www.youtube.com/watch?v=d_KZxkY_0cM"


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
            "Dumbell Rear Deltoid"
    };

    static String[] ShoulderUrl = {"https://www.youtube.com/watch?v=flpBXsHSVDk",
            "https://www.youtube.com/watch?v=b5JzUH8gsOg",
            "https://www.youtube.com/watch?v=2yjwXTZQDDI",
            "https://www.youtube.com/watch?v=B-aVuyhvLHU",
            "https://www.youtube.com/watch?v=Wqq43dKW1TU",
            "https://www.youtube.com/watch?v=3ml7BH7mNwQ",
            "",
            "https://www.youtube.com/watch?v=IhZLB48kluc",
            "https://www.youtube.com/watch?v=amCU-ziHITM",
            "https://www.youtube.com/watch?v=3VcKaXpzqRo",
            "https://www.youtube.com/watch?v=PlJEfHTsyUo",
            "https://www.youtube.com/watch?v=xlxDquY0AQ8",
            "https://www.youtube.com/watch?v=H0O4HNvtB70",
            "https://www.youtube.com/watch?v=-t7fuZ0KhDA",
            "https://www.youtube.com/watch?v=DAC4qmKv5o0",
            "https://www.youtube.com/watch?v=K-ilBw_D1a4",
            "https://www.youtube.com/watch?v=p1yQnTNE808"
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
            "https://www.youtube.com/watch?v=b_uAOqA-pxY",
            "https://www.youtube.com/watch?v=cAnOVuEBadQ",
            "https://www.youtube.com/watch?v=rdpFZtaIwKc",
            "https://www.youtube.com/watch?v=0_9sJd9P8M0",
            "https://www.youtube.com/watch?v=fRsTLzZy1SM",
            "https://www.youtube.com/watch?v=7mDWDlzFobQ",
            "https://www.youtube.com/watch?v=De4fAXtOUkw",
            "",
            "https://www.youtube.com/watch?v=IZxyjW7MPJQ",
            "https://www.youtube.com/watch?v=YyvSfVjQeL0",
            "https://www.youtube.com/watch?v=ELOCsoDSmrg",
            "https://www.youtube.com/watch?v=U4s4mEQ5VqU",
            "https://www.youtube.com/watch?v=y7Iug7eC0dk",
            "https://www.youtube.com/watch?v=FQi20qWvy1E",
            "https://www.youtube.com/watch?v=KvloZ_0wi_4",
            "https://www.youtube.com/watch?v=JCXUYuzwNrM",
            "https://www.youtube.com/watch?v=5nx1I6nTFM8",
            "https://www.youtube.com/watch?v=diBqFqCXQIc",
            "https://www.youtube.com/watch?v=5lOLPHbK_NY",
            "https://www.youtube.com/watch?v=5Xj6XUa77qc",
            "https://www.youtube.com/watch?v=O8d8I7-qtGg",

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
            " https://www.youtube.com/watch?v=vdezTMulJ-k",
            "https://www.youtube.com/watch?v=lNjTf27lb-k&index=1&list=PLetagxtbPjbop9NQppY5AB9OQuJiFRwX2",
            "https://www.youtube.com/watch?v=uxx6bdjryec",
            "https://www.youtube.com/watch?v=nmwgirgXLYM",
            "https://www.youtube.com/watch?v=UpH7rm0cYbM",
            "https://www.youtube.com/watch?v=BDA3DERQKFk",
            "https://www.youtube.com/watch?v=Pf7wZvraWV0",
            "https://www.youtube.com/watch?v=Q7cPaJZoOng",
            "https://www.youtube.com/watch?v=wkD8rjkodUI",
            "https://www.youtube.com/watch?v=pSHjTRCQxIw",
            "https://www.youtube.com/watch?v=NXr4Fw8q60o",

    };

    static String[] AbdomenName = {
            "Crosscrunch",
            "Leg raises",
            "Hanging Leg raises",
            "Flutter kicks",
            "Side Plank dips",
            "Windmills",
            "V-up",
            "Plank",
            "Side plank without Leg lift",
            "Rocking plank",
            "Barbell roll out",
            "Alternate heel touchers",
            "Bent knee hip raise",
            "Bosu ball crunch",
            "Cable crunch",
            "Cross body crunch",
            "Decline crunch",
            "Dumbell side bend",
            "Elbow to knee",
            "Ball pull in",
            "Hanging pike raises",
            "Jackknife sit up",
            "Russian twist",
            "Barbell twist",
            "Spider abs"
    };

    static String[] AbdomenUrl = {
            "https://youtu.be/fd1tgwx65-w",
            "https://youtu.be/JB2oyawG9KI",
            "https://youtu.be/wQkDkFQPpbQ",
            "https://youtu.be/eEG9uXjx4vQ",
            "https://youtu.be/cIgJkPjnFe4",
            "https://youtu.be/C4y21LH4m4k",
            "https://youtu.be/iP2fjvG0g3w",
            "https://youtu.be/aqzpeq7WxlE",
            "https://youtu.be/ZZkgopVBPMg",
            "https://youtu.be/MFIFuJTizmY",
            "https://youtu.be/8s9rcrCS28k",
            "https://youtu.be/5K0pqM2Gq2c",
            "https://youtu.be/VsK9rz0xNI4",
            "https://youtu.be/ms54teTH74M",
            "https://youtu.be/NJQROeaBiVE",
            "https://youtu.be/cDIYH5rH0qU",
            "https://youtu.be/42_-OvIOZXc",
            "https://youtu.be/ARAWlmlgPbg",
            "https://youtu.be/hlcQQdYSJgY",
            "https://youtu.be/B9GC4LBD9wM",
            "https://youtu.be/Xy9bW-V36ys",
            "https://youtu.be/I0EJnQlJqMU",
            "https://youtu.be/1YeRvNkPVVY",
            "https://youtu.be/pXmaiD4NIdg",
            "https://youtu.be/EkutYJA6liQ"

    };

    public static HashMap<String, List<Workout2>> getData() {
        HashMap<String, List<Workout2>> expandableListDetail = new HashMap<>();

        List<Workout2> chestList = new ArrayList<>();
        List<Workout2> backList = new ArrayList<>();
        List<Workout2> bicepsList = new ArrayList<>();
        List<Workout2> tricepsList = new ArrayList<>();
        List<Workout2> shoulderList = new ArrayList<>();
        List<Workout2> legList = new ArrayList<>();
        List<Workout2> functionalList = new ArrayList<>();
        List<Workout2> abdomenList = new ArrayList<>();

        Firebase firebaseReff = new Firebase(Constants.FIREBASE_URL + "workout");

        for (int i = 0; i < ChestName.length; i++) {
            chestList.add(new Workout2(ChestName[i], ChestUrl[i], "chest"));
            Firebase fbRef = firebaseReff.child("chest").push();
            firebaseReff.child("chest").child(fbRef.getKey()).setValue(chestList.get(i));
        }


        for (int i = 0; i < BackName.length; i++) {
            backList.add(new Workout2(BackName[i], BackUrl[i], "back"));
            Firebase fbRef = firebaseReff.child("back").push();
            firebaseReff.child("back").child(fbRef.getKey()).setValue(backList.get(i));
        }

        for (int i = 0; i < BicepsName.length; i++) {
            bicepsList.add(new Workout2(BicepsName[i], BicepsUrl[i], "biceps"));
            Firebase fbRef = firebaseReff.child("biceps").push();
            firebaseReff.child("biceps").child(fbRef.getKey()).setValue(bicepsList.get(i));
        }

        for (int i = 0; i < TricepsName.length; i++) {
            tricepsList.add(new Workout2(TricepsName[i], TricepsUrl[i], "triceps"));
            Firebase fbRef = firebaseReff.child("triceps").push();
            firebaseReff.child("triceps").child(fbRef.getKey()).setValue(tricepsList.get(i));
        }

        for (int i = 0; i < ShoulderName.length; i++) {
            shoulderList.add(new Workout2(ShoulderName[i], ShoulderUrl[i], "shoulder"));
            Firebase fbRef = firebaseReff.child("shoulder").push();
            firebaseReff.child("shoulder").child(fbRef.getKey()).setValue(shoulderList.get(i));
        }

        for (int i = 0; i < LegName.length; i++) {
            legList.add(new Workout2(LegName[i], LegUrl[i], "leg"));
            Firebase fbRef = firebaseReff.child("leg").push();
            firebaseReff.child("leg").child(fbRef.getKey()).setValue(legList.get(i));
        }

        for (int i = 0; i < FunctionalName.length; i++) {
            functionalList.add(new Workout2(FunctionalName[i], FunctionalUrl[i], "functional"));
            Firebase fbRef = firebaseReff.child("functional").push();
            firebaseReff.child("functional").child(fbRef.getKey()).setValue(functionalList.get(i));
        }


        for (int i = 0; i < AbdomenName.length; i++) {
            abdomenList.add(new Workout2(AbdomenName[i], AbdomenUrl[i], "abdomen"));
            Firebase fbRef = firebaseReff.child("abdomen").push();
            firebaseReff.child("abdomen").child(fbRef.getKey()).setValue(abdomenList.get(i));
        }

        return expandableListDetail;

    }


}