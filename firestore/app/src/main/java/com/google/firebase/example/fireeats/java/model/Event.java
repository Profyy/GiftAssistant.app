package com.google.firebase.example.fireeats.java.model;

import java.util.Date;

public class Event {

    public static final String FIELD_TYPE = "type";
    private Date created;
    private String type;
    private String host;
    private String date;
    private String time;
    private String city;
    private String country;

    public Event() {}
    public Event(String type, String host, String date, String time, String country, String city) {
        this.created = new Date();
        this.type = type;
        this.host = host;
        this.date = date;
        this.time = time;
        this.city = city;
        this.country = country;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}