package com.example.mapapp.connector;

public class LoginResponse {
    private String jwt;
    private int responseCode;

    public String getJwt() {
        return jwt;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}

