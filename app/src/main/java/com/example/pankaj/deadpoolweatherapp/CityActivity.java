package com.example.pankaj.deadpoolweatherapp;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/*This class is to get weather information based on city name*/
public class CityActivity extends AppCompatActivity {

    EditText cityName;
    List<WeatherInfo> weatherInfoList;
    String city=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        cityName = (EditText) findViewById(R.id.etxtCityName);
    }

    public void btnGetWeatherOnClick(View v) {

        if (!cityName.getText().equals("")) {



            //Volley library to request weather information from http://api.openweathermap.org

            RequestQueue queue = Volley.newRequestQueue(CityActivity.this);
            StringBuilder sb=new StringBuilder();
           sb.append("http://api.openweathermap.org/data/2.5/forecast/daily?q=");
            sb.append(cityName.getText().toString());
            sb.append("&units=metric&APPID=b1ed602908528e97950ba59fe098bdd1");
            String url = sb.toString();
          //  String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=delhi%20&units=metric&APPID=b1ed602908528e97950ba59fe098bdd1";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.d("Data....", "...." + WeatherInfoHandle.getWeatherinfoList(response));
                            weatherInfoList = WeatherInfoHandle.getWeatherinfoList(response);
                            for (WeatherInfo w : weatherInfoList) {
                                Log.d("Day....", "'...." + w.toString());
                            }
                            try{
                                JSONObject jsonObject=new JSONObject(response);
                                JSONObject  cityObj=jsonObject.getJSONObject("city");
                                Log.d("City name:","....."+cityObj.getString("name"));
                                city=""+cityObj.getString("name");
                            }catch (Exception e){
                               Log.e("Json....",".....Exception"+e.getMessage());
                            }

                            Pref.saveCity(city);
                            Intent intent= new Intent(CityActivity.this, MainActivity.class);
                            intent.putExtra("weatherList",(Serializable) weatherInfoList);
                            intent.putExtra("cityName",city);
                            startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley error...","...."+error.getMessage());
                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);


        } else {
            cityName.setError("Please Enter city name");
        }
    }
}

