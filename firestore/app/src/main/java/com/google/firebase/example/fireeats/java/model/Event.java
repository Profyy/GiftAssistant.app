package com.google.firebase.example.fireeats.java.model;

public class Event {

    public static final String FIELD_TYPE = "type";

    private String type;

    public Event() {}
    public Event(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}