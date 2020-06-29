package com.example.bookmarkmyshow.model;

public class HomeBodyResponse {

    String status;
    HomeData data;

    public HomeBodyResponse(String status, HomeData data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public HomeData getData() {
        return data;
    }
}