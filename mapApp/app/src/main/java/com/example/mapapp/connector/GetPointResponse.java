package com.example.mapapp.connector;

import java.util.List;

public class GetPointResponse {
    List<Point> pointList;
    Boolean status;
    String errorText;

    public GetPointResponse() {
    }

    public GetPointResponse(List<Point> pointList, Boolean status, String errorText) {
        this.pointList = pointList;
        this.status = status;
        this.errorText = errorText;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
