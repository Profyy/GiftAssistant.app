package com.google.firebase.example.fireeats.java.model;

import java.util.Date;

public class Event {

    public static final String FIELD_TYPE = "type";
    private Date created;
    private String name;
    private String email;
    private String type;
    private String date;
    private String time;
    private String city;
    private String country;
    private String photo;


    public Event() {}

    public Event(String type, String date, String time, String country, String city, String name, String email) {
        this.created = new Date();
        this.name = name;
        this.email = email;
        this.type = type;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}