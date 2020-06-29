package com.example.bookmarkmyshow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private String BASE_URL = "http://api.themoviedb.org/3/";

    private Retrofit retrofit;
    OkHttpClient.Builder okHttpClient;


    private static RetrofitSingleton retrofitSingletonInstance;
    private Gson gson;


    public RetrofitSingleton() {

        gson = new GsonBuilder()
                .setLenient()
                .create();

        okHttpClient = new OkHttpClient.Builder().readTimeout(3, TimeUnit.MINUTES);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }


    public static synchronized RetrofitSingleton getInstance() {

        if (retrofitSingletonInstance == null) {
            retrofitSingletonInstance = new RetrofitSingleton();
        }

        return retrofitSingletonInstance;


    }

    public RetrofitApiClient getApi() {

        return retrofit.create(RetrofitApiClient.class);

    }


}