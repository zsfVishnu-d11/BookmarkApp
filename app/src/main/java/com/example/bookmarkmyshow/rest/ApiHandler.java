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

public class ApiHandler {

    public ApiHandler(){}

    private static final String TAG = ApiHandler.class.getSimpleName();
    //Retrofit : REST client library which creates and processes HTTP requests from a REST api
    private static Retrofit retrofit = null;
    private final static String API_KEY = "42a67939ed1c4b8c106331b02b24cb54";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";



//    public static void connectAndGetApiData(final Context context, final RecyclerView recyclerView) {
    public void connectAndGetApiData(final INetworkCallback iNetworkCallback) {
        //generic callbacks


        if (retrofit == null) {

            retrofit = new Retrofit.Builder() //building our retrofit object
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //ConvertFactory will takes care
                    .build();                           // of the parsing of data we're sending and also the responses we get
            //GsonConvertFactory map our JSON data to the Movie class defined earlier
            //TODO:what happens when we're not explicitely mentioning the HTTP client
        }







        //Service Call
        MovieApiService movieApiService = retrofit.create(MovieApiService.class); // Retrofit takes care of the construction
                                                                            // of our service interface by injecting the code necessary
                                                                            // to make the request, based on our previous annotations.
        Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);
        //this object makes calls to the api
        call.enqueue(new Callback<MovieResponse>() { //what does enque do? -> Calls may be executed
            @Override                                //synchronously with execute(), or asynchronously with enqueue
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                iNetworkCallback.onSuccess(movies);
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
                Log.d(TAG, "Number of movies received: " + movies.size());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
//                iNetworkHandler.onError((Exception) throwable);
            }

        });









    }




}
