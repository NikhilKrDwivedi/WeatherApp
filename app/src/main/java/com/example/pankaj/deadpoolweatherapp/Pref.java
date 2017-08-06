package com.example.pankaj.deadpoolweatherapp;

import android.content.SharedPreferences;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pankaj on 6/8/17.
 */

public class Pref {

    public Pref(){

    }

    public static void saveCity(String name){
        SharedPreferences MyPref= MyApplication.getContext().getSharedPreferences("City",MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor= MyPref.edit();
        editor.putString("name",name);
        editor.commit();

    }

    public static String getCity(){
        SharedPreferences MyPref= MyApplication.getContext().getSharedPreferences("City",MODE_PRIVATE);
        return MyPref.getString("name",null);

    }

    public static void clearCity(){
        SharedPreferences MyPref= MyApplication.getContext().getSharedPreferences("City",MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor= MyPref.edit();
        editor.clear();
        editor.commit();
    }



}
