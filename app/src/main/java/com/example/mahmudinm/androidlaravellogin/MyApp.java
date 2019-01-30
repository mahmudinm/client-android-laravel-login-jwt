package com.example.mahmudinm.androidlaravellogin;

import android.app.Application;
import android.content.Context;

import com.example.mahmudinm.androidlaravellogin.network.ApiClient;
import com.example.mahmudinm.androidlaravellogin.network.ApiInterface;

public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
