package com.example.pankaj.deadpoolweatherapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pankaj on 6/8/17.
 */

public class WeatherInfoHandler {

    public static List<WeatherInfo> getWeatherinfoList(String weatherString){

        List<WeatherInfo> weatherinfoList=new ArrayList<>();
        WeatherInfo weatherInfo=new WeatherInfo();
        try {

            Date today = new Date();
            SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 1);  // number of days to add
            String tomorrow = (String)(formattedDate.format(c.getTime()));
            Log.d("Tomorrows date is " ,tomorrow);


            JSONObject jsonObject = new JSONObject(weatherString);

            JSONArray jsonArray= jsonObject.getJSONArray("list");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject listObj=jsonArray.getJSONObject(i);
                JSONObject main=listObj.getJSONObject("temp");
                //Log.d("Main....","...."+main);
                weatherInfo.setMinTemp(main.getDouble("min"));
                weatherInfo.setMaxTemp(main.getDouble("max"));
                weatherInfo.setDayTemp(main.getDouble("day"));
                weatherInfo.setNightTemp(main.getDouble("night"));
                weatherInfo.setMinTemp(main.getDouble("morn"));
                weatherInfo.setMaxTemp(main.getDouble("eve"));
                weatherInfo.setDayTemp(main.getDouble("day"));
                weatherInfo.setNightTemp(main.getDouble("night"));
                weatherInfo.setHumidity(listObj.getDouble("humidity"));

                JSONArray wmainArr=listObj.getJSONArray("weather");
                JSONObject wmianobj=wmainArr.getJSONObject(0);
                weatherInfo.setMainMsg(wmianobj.getString("main"));
                weatherInfo.setDescription(wmianobj.getString("description"));
                //Log.d("Main....","...."+main);
                weatherInfo.setMinTemp(main.getDouble("min"));
                weatherinfoList.add(weatherInfo);
                weatherInfo.setWeatherDate(new Date());

                Log.d("temp", "" + new Date());
            }

        }catch (org.json.JSONException e) {
                Log.e("weatherinfo..exception",".....0"+e.getMessage());
        }

        return weatherinfoList;
    }


}


