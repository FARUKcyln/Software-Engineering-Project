package com.example.mapapp.connector;

public class RegisterResponse {
    UserID user;
    boolean status;
    String errorText;

    public UserID getUser() {
        return user;
    }

    public void setUser(UserID user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
