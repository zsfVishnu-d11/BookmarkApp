package com.example.bookmarkmyshow.rest;

import android.util.Log;

import com.example.bookmarkmyshow.activity.MainActivity;

import java.util.List;

public class INetworkHandler implements INetworkCallback {

    @Override
    public void onSuccess(List data) {

    }

    @Override
    public void onError(Exception e) {

        Log.d("Network","Empty list");

    }
}
