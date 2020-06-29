package com.example.bookmarkmyshow.model;

import java.util.List;

public class HomeData {

    List<Data> data;

    public HomeData(List<Data> data) {
        this.data = data;
    }


    public List<Data> getData() {
        return data;
    }
}
