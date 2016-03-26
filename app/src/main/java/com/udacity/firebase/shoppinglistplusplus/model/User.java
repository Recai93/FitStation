package com.udacity.firebase.shoppinglistplusplus.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Defines the data structure for User objects.
 */
public class User {
    private String name;
    private String surname;
    private String username;
    private String gender;
    private String email;
    private Date birthday;
    private boolean isTrainer;
    private String phoneNumber;
    private String trainer;
    private HashMap<String, Object> timestampJoined;
    private boolean hasLoggedInWithPassword;

    public User() {
    }

    public User(String name, String surname, String username, String gender,
                String email, Date birthday, boolean isTrainer, String phoneNumber,
                String trainer, HashMap<String, Object> timestampJoined) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.username = username;
        this.isTrainer = isTrainer;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.trainer = trainer;
        this.timestampJoined = timestampJoined;
        this.hasLoggedInWithPassword = false;
    }


    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public boolean getIsTrainer() {
        return isTrainer;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getTrainer() {
        return trainer;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public boolean isHasLoggedInWithPassword() {
        return hasLoggedInWithPassword;
    }
}
