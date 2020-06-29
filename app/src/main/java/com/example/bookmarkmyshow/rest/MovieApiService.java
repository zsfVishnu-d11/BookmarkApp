package com.example.bookmarkmyshow.rest;

import com.example.bookmarkmyshow.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//Defining the HTTP operations.
//Every method represents one possible API call

public interface MovieApiService {


    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key")  String apiKey);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key")  String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("api_key")  String apiKey);

    @GET("search/movie")
    Call<MovieResponse> searchMovie(@Query("api_key") String apiKey, @Query("query") String query);

}
