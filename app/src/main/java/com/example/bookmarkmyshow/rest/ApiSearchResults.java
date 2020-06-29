package com.example.bookmarkmyshow.rest;

import android.util.Log;

import com.example.bookmarkmyshow.model.Movie;
import com.example.bookmarkmyshow.model.MovieResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSearchResults {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String API_KEY = "42a67939ed1c4b8c106331b02b24cb54";
    private String searchKey;
    private static final String TAG = ApiHandler.class.getSimpleName();
    //Retrofit : REST client library which creates and processes HTTP requests from a REST api
    private static Retrofit retrofit = null;


    public ApiSearchResults(String searchKey){

        if (searchKey == null || searchKey == "init")
            this.searchKey = "John";
        else
            this.searchKey = searchKey;
    }



    public void connectAndGetApiData(final INetworkCallback iNetworkCallback) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()                                    //building our retrofit object
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //ConvertFactory will takes care
                    .build();                                                     // of the parsing of data we're sending and also the responses we get
        }                                                                      //GsonConvertFactory map our JSON data to the Movie class defined earlier

        //Service Call
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        Call<MovieResponse> call = movieApiService.searchMovie(API_KEY,searchKey);
        call.enqueue(new Callback<MovieResponse>() { //Calls may be executed synchronously with execute(), or asynchronously with enqueue
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                try
                {
                    List<Movie> resp = response.body().getResults();
                    if (!(resp == null))
                        iNetworkCallback.onSuccess(resp);

                }
                catch (Exception e) {
                    Log.d("Response", "null");
                }
//                INetworkCallback iNetworkCallback1 = new INetworkCallback() {
//                    @Override
//                    public void onSuccess(List data) {
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                };
//                MoviesAdapter moviesAdapter = new MoviesAdapter(movies, R.layout.card_inner_horizontal, context);
//                recyclerView.setAdapter(moviesAdapter);
//                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.card_inner_horizontal, context));
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
