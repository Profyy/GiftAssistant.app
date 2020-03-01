package com.google.firebase.example.fireeats.java.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        private List<String> invited;


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
            this.invited = Arrays.asList(email);
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
        if(type.equals("Birhday")){
            photo = "https://firebasestorage.googleapis.com/v0/b/friendlychat-179ac.appspot.com/o/birthday.png?alt=media&token=d9baaf95-f9e6-43e9-9f59-31d6bf6f19ef";
        } else if(type.equals("Graduate")) {
            photo = "https://firebasestorage.googleapis.com/v0/b/friendlychat-179ac.appspot.com/o/graduate.jpg?alt=media&token=9e066188-9d85-45b2-859d-90d8c8a9d669";
        } else if(type.equals("Wedding")) {
            photo = "https://firebasestorage.googleapis.com/v0/b/friendlychat-179ac.appspot.com/o/wedding.jpg?alt=media&token=31017c2a-29c5-407b-abd5-a5d0c2c2d73c";
        }
        else if(type.equals("Bachelorette")) {
            photo = "https://firebasestorage.googleapis.com/v0/b/friendlychat-179ac.appspot.com/o/bachelorette.jpg?alt=media&token=645011b0-0a86-4da5-bba3-e5a7a2d94dcf";
        }
        else if (type.equals("Bachelore")) {
            photo = "https://firebasestorage.googleapis.com/v0/b/friendlychat-179ac.appspot.com/o/bachelor.jpg?alt=media&token=b65a3362-ffa9-4f86-9d3f-1a28dfdbdb0e";
        }
        else {
            photo = "https://firebasestorage.googleapis.com/v0/b/friendlychat-179ac.appspot.com/o/common.jpg?alt=media&token=2cb06155-e11a-4579-8565-3fcbde5daa5b";
        }
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

    public List<String> getInvited () { return invited; }
}