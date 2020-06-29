package com.example.bookmarkmyshow.rest;

import com.example.bookmarkmyshow.model.Movie;

import java.util.List;

public interface INetworkCallback<T> {

    public void onSuccess(List<Movie> data); //TODO:understand Json parsing using GSON
    public void onError(Exception e);

    //how do you handle individual

}
