package com.google.firebase.example.fireeats.java.model;

public class Invitee {
    String email;

    public Invitee() {}

    public Invitee(String email) {
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
