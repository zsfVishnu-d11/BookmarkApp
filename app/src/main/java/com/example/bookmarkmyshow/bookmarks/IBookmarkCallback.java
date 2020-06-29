package com.example.bookmarkmyshow.bookmarks;

import com.example.bookmarkmyshow.model.Movie;

import java.util.List;

public interface IBookmarkCallback<T> {

    public void onSuccess(List<Movie> data);

    public  void onFailure(Exception e);

}
