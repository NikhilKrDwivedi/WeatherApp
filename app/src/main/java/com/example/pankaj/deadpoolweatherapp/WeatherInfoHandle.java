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

public class WeatherInfoHandle {

    public static List<WeatherInfo> getWeatherinfoList(String weatherString){

        List<WeatherInfo> weatherinfoList=new ArrayList<>();
        WeatherInfo weatherInfo;
        try {

            Date today = new Date();
            SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy");
            Calendar c = Calendar.getInstance();



            JSONObject jsonObject = new JSONObject(weatherString);

            JSONArray jsonArray= jsonObject.getJSONArray("list");
            for(int i=0;i<jsonArray.length();i++){
                weatherInfo=new WeatherInfo();
                if(i==0){
                    String tomorrow = (String)(formattedDate.format(c.getTime()));
                    Log.d("Tomorrows date is " ,tomorrow);
                    weatherInfo.setWeatherDate(tomorrow);
                }else {
                    c.add(Calendar.DATE, 1);  // number of days to add
                    String tomorrow = (String)(formattedDate.format(c.getTime()));
                    Log.d("Tomorrows date is " ,tomorrow);
                    weatherInfo.setWeatherDate(tomorrow);
                }

                JSONObject listObj=jsonArray.getJSONObject(i);
                JSONObject main=listObj.getJSONObject("temp");
                //Log.d("Main....","...."+main);
                weatherInfo.setMinTemp(main.getDouble("min"));
                weatherInfo.setMaxTemp(main.getDouble("max"));
                weatherInfo.setDayTemp(main.getDouble("day"));
                weatherInfo.setNightTemp(main.getDouble("night"));
                weatherInfo.setMorningTemp(main.getDouble("morn"));
                weatherInfo.setEveningTemp(main.getDouble("eve"));

                weatherInfo.setHumidity(listObj.getDouble("humidity"));

                JSONArray wmainArr=listObj.getJSONArray("weather");
                JSONObject wmianobj=wmainArr.getJSONObject(0);
                weatherInfo.setMainMsg(wmianobj.getString("main"));
                weatherInfo.setDescription(wmianobj.getString("description"));

                weatherinfoList.add(weatherInfo);

            }

        }catch (org.json.JSONException e) {
                Log.e("weatherinfo..exception",".....0"+e.getMessage());
        }

        return weatherinfoList;
    }


}


