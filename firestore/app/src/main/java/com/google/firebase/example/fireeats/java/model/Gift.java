package com.google.firebase.example.fireeats.java.model;

public class Gift {
    String description;
    String url;

    public Gift() {}

    public Gift(String description, String url) {
        this.description=description;
        this.url=url;
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
}
