package com.example.pankaj.deadpoolweatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button btnGetCityWeather;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        progressDialog=new ProgressDialog(this,R.style.MyTheme);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);



        cityName = (EditText) findViewById(R.id.etxtCityName);
        btnGetCityWeather=(Button) findViewById(R.id.btnGetCityWeather);
        if(!Pref.getCity().equals(null)){
            cityName.setVisibility(View.INVISIBLE);
            btnGetCityWeather.setVisibility(View.INVISIBLE);
            updateWeather(Pref.getCity());
        }else {
            cityName.setVisibility(View.VISIBLE);
            btnGetCityWeather.setVisibility(View.VISIBLE);
        }

        btnGetCityWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cityName.getText().equals("")) {

                    updateWeather(cityName.getText().toString());



                } else {
                    cityName.setError("Please Enter city name");
                }
            }
        });

    }

    private void updateWeather(String cityName){
        //Volley library to request weather information from http://api.openweathermap.org
        progressDialog.setTitle("Weather Info");
        progressDialog.setMessage("Getting data please wait...!!!");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(CityActivity.this);
        StringBuilder sb=new StringBuilder();
        sb.append("http://api.openweathermap.org/data/2.5/forecast/daily?q=");
        sb.append(cityName);
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
                        progressDialog.dismiss();
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

    }
}

