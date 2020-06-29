package com.example.bookmarkmyshow.rest;

import com.example.bookmarkmyshow.model.HomeBodyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitApiClient {

    @GET("movie/top_rated")
    Call<HomeBodyResponse> getMovieByCategory(@Query("api_key")  String apiKey);


//    Call<HomeBodyResponse> getMovieByCategory();
}
