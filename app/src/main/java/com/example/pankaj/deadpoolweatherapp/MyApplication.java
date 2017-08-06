package com.example.pankaj.deadpoolweatherapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by PankajDwivedi on 28-07-2017.
 */

public class MyApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}