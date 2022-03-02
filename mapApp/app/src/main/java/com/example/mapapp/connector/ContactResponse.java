package com.example.mapapp.connector;

public class ContactResponse {
    String text;
    Boolean status;

    public ContactResponse(String text, Boolean status) {
        this.text = text;
        this.status = status;
    }

    public ContactResponse() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

