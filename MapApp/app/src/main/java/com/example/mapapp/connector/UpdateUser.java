package com.example.mapapp.connector;

public class UpdateUser {
    String name;
   // String surname;
    String email;
   // String phoneNumber;
   // String password;


    public UpdateUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UpdateUser() {
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
