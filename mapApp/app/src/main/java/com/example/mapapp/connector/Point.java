package com.example.mapapp.connector;

public class Point {
    int Id;
    double longitude;
    double latitude;
    double Storage;

    public Point() {
    }

    public Point(int id, double longitude, double latitude, double storage) {
        Id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        Storage = storage;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getStorage() {
        return Storage;
    }

    public void setStorage(double storage) {
        Storage = storage;
    }
}
