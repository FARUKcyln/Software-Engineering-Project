package com.example.mapapp.connector;

public class PostFeed {
    String point_latitude;
    String point_longitude;
    String point_id;

    public PostFeed() {
    }

    public PostFeed(String point_latitude, String point_longitude, String point_id) {
        this.point_latitude = point_latitude;
        this.point_longitude = point_longitude;
        this.point_id = point_id;
    }

    public String getPoint_latitude() {
        return point_latitude;
    }

    public void setPoint_latitude(String point_latitude) {
        this.point_latitude = point_latitude;
    }

    public String getPoint_longitude() {
        return point_longitude;
    }

    public void setPoint_longitude(String point_longitude) {
        this.point_longitude = point_longitude;
    }

    public String getPoint_id() {
        return point_id;
    }

    public void setPoint_id(String point_id) {
        this.point_id = point_id;
    }
}
