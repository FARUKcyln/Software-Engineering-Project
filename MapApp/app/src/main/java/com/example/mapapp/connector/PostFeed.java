package com.example.mapapp.connector;

public class PostFeed {
    int point;
    int amount;


    public PostFeed(int point, int amount) {
        this.point = point;
        this.amount = amount;
    }

    public PostFeed() {
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
