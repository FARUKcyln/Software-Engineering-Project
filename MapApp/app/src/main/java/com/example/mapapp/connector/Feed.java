package com.example.mapapp.connector;

import java.util.Date;

public class Feed {
    String _id;
    int point_latitude;
    int point_longitude;
    int  point_id;
    int amount;
    String date;
    int __v;

    public Feed() {
    }

    public Feed(String _id, int point_latitude, int point_longitude, int point_id, int amount, String date, int __v) {
        this._id = _id;
        this.point_latitude = point_latitude;
        this.point_longitude = point_longitude;
        this.point_id = point_id;
        this.amount = amount;
        this.date = date;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getPoint_latitude() {
        return point_latitude;
    }

    public void setPoint_latitude(int point_latitude) {
        this.point_latitude = point_latitude;
    }

    public int getPoint_longitude() {
        return point_longitude;
    }

    public void setPoint_longitude(int point_longitude) {
        this.point_longitude = point_longitude;
    }

    public int getPoint_id() {
        return point_id;
    }

    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
