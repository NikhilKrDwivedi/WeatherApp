package com.example.pankaj.deadpoolweatherapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RemoteFetch fetch=new RemoteFetch();
        Log.d("weather Data","....."+fetch.getJSON(this,"Delhi"));
    }


    private class RemoteFetch {

        private static final String OPEN_WEATHER_MAP_API =
                "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=b1ed602908528e97950ba59fe098bdd1";

        public  JSONObject getJSON(Context context, String city){
            try {
                URL url = new URL(String.format(OPEN_WEATHER_MAP_API));
                Log.d("Online.....","Data....1");
                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();
                Log.d("Online.....","Data....2");
                connection.addRequestProperty("Deadpool",
                        context.getString(R.string.open_weather_maps_app_id));
                Log.d("Online.....","Data....3"+connection.getResponseMessage());
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                Log.d("Online.....","Data....4");

                StringBuffer json = new StringBuffer(1024);
                String tmp="";
                while((tmp=reader.readLine())!=null)
                    json.append(tmp).append("\n");
                Log.d("Online.....","Data....5");
                reader.close();
                JSONObject data = new JSONObject(json.toString());
                Log.d("Online.....","Data....6");
                // This value will be 404 if the request was not
                // successful
                if(data.getInt("cod") != 200){
                    Log.d("Online.....","Data....7");
                    return null;
                }

                return data;
            }catch(Exception e){
                Log.e("Eception....","....."+e.getMessage());
                return null;
            }
        }
    }


}
