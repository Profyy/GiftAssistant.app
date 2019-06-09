package com.google.firebase.example.fireeats.java.model;

public class Gift {
    String description;
    String url;
    String reservedBy;
    Boolean reserved;

    public Gift() {}

    public Gift(String description, String url, Boolean reserved) {
        this.description=description;
        this.url=url;
        this.reserved = reserved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void reserve() {
        reserved = true;
    }

    public void release() {
        reserved = false;
    }

    public Boolean isReserved() {
        return reserved;
    }


}
