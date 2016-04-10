package com.udacity.firebase.shoppinglistplusplus.model;

import java.util.Date;

/**
 * Created by elif polat on 02.03.2016.
 */
public class Message {
    private String sender;
    private String receiver;
    private String text;
    private Date date;


    public Message() {
    }

    public Message(String receiver, String sender, String text, Date date) {
        this.receiver = receiver;
        this.sender = sender;
        this.text = text;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}
