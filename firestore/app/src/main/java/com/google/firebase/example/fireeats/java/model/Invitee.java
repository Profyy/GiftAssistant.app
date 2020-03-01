package com.google.firebase.example.fireeats.java.model;

public class Invitee {
    String name;
    String email;

    public Invitee() {}

    public Invitee(String name, String email) {
            this.name=name;
            this.email=email;
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
