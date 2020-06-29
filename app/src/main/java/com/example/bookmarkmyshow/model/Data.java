package com.example.bookmarkmyshow.model;

import com.example.bookmarkmyshow.bookmarks.BookmarkMovie;

import java.util.List;

public class Data {
    List<Movie> list;
    String genre;
    String type;

    public Data(List<Movie> list, String genre, String type) {
        this.list = list;
        this.genre = genre;
        this.type = type;
    }



    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public String getType() {
        return type;
    }
}
