package com.google.firebase.example.fireeats.java.model;

import java.util.Date;

public class Event {

    public static final String FIELD_TYPE = "type";
    public static final String FIELD_ID = "id";
    private int id;
    private Date created;
    private String type;
    private String host;
    private String date;
    private String time;
    private String place;

    public Event() {}
    public Event(String type, String host, String date, String time) {
        this.created = new Date();
        this.type = type;
        this.host = host;
        this.date = date;
        this.time = time;
        this.place = place;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}