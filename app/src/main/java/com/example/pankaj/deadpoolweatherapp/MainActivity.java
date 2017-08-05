package com.example.pankaj.deadpoolweatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<WeatherInfo> weatherInfoList=null;
    TextView txtDate,txtMin,txtMax,txtDay,txtNight,txtMorning,txtEvening,txtMain,txtDescription;
    LinearLayout linearLayoutToday,linearLayoutTomorrow;
    TextView txtDateTomorrow,txtMintomorrow,txtMaxtomorrow,txtDaytomorrow,txtNighttomorrow,txtMorningtomorrow,txtEveningtomorrow,txtMaintomorrow,txtDescriptiontomorrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearLayoutToday=(LinearLayout) findViewById(R.id.linearlayoutToday);
        txtDate=(TextView) linearLayoutToday.findViewById(R.id.txtDateDay);
        txtMin=(TextView) linearLayoutToday.findViewById(R.id.txtMin);
        txtMax=(TextView) linearLayoutToday.findViewById(R.id.txtMax);
        txtDay=(TextView) linearLayoutToday.findViewById(R.id.txtDaytemp);
        txtNight=(TextView) linearLayoutToday.findViewById(R.id.txtNighttemp);
        txtMorning=(TextView) linearLayoutToday.findViewById(R.id.txtMorningtemp);
        txtEvening=(TextView) linearLayoutToday.findViewById(R.id.txtEveingtemp);
        txtMain=(TextView) linearLayoutToday.findViewById(R.id.txtmaininfo);
        txtDescription=(TextView) linearLayoutToday.findViewById(R.id.txtmaininfoDescription);

        linearLayoutTomorrow=(LinearLayout) findViewById(R.id.linearlayoutTomorrow);
        txtDateTomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtDateDay);
        txtMintomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtMin);
        txtMaxtomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtMax);
        txtDaytomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtDaytemp);
        txtNighttomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtNighttemp);
        txtMorningtomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtMorningtemp);
        txtEveningtomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtEveingtemp);
        txtMaintomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtmaininfo);
        txtDescriptiontomorrow=(TextView) linearLayoutTomorrow.findViewById(R.id.txtmaininfoDescription);



        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=delhi%20&units=metric&APPID=b1ed602908528e97950ba59fe098bdd1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       Log.d("Data....","...."+WeatherInfoHandle.getWeatherinfoList(response));
                        weatherInfoList=WeatherInfoHandle.getWeatherinfoList(response);
                        for(WeatherInfo w:weatherInfoList){
                            Log.d("Day....","'...."+w.toString());
                        }

                        txtDate.setText(weatherInfoList.get(0).getWeatherDate());
                        txtMin.setText(String.valueOf(weatherInfoList.get(0).getMinTemp()));
                        txtMax.setText(String.valueOf(weatherInfoList.get(0).getMaxTemp()));
                        txtDay.setText(String.valueOf(weatherInfoList.get(0).getDayTemp()));
                        txtNight.setText(String.valueOf(weatherInfoList.get(0).getNightTemp()));
                        txtMorning.setText(String.valueOf(weatherInfoList.get(0).getMorningTemp()));
                        txtEvening.setText(String.valueOf(weatherInfoList.get(0).getEveningTemp()));
                        txtMain.setText(weatherInfoList.get(0).getMainMsg());
                        txtDescription.setText(weatherInfoList.get(0).getDescription());

                        txtDateTomorrow.setText(weatherInfoList.get(1).getWeatherDate());
                        txtMintomorrow.setText(String.valueOf(weatherInfoList.get(1).getMinTemp()));
                        txtMaxtomorrow.setText(String.valueOf(weatherInfoList.get(1).getMaxTemp()));
                        txtDaytomorrow.setText(String.valueOf(weatherInfoList.get(1).getDayTemp()));
                        txtNighttomorrow.setText(String.valueOf(weatherInfoList.get(1).getNightTemp()));
                        txtMorningtomorrow.setText(String.valueOf(weatherInfoList.get(1).getMorningTemp()));
                        txtEveningtomorrow.setText(String.valueOf(weatherInfoList.get(1).getEveningTemp()));
                        txtMaintomorrow.setText(weatherInfoList.get(1).getMainMsg());
                        txtDescriptiontomorrow.setText(weatherInfoList.get(1).getDescription());



                        /*try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("cod");

                            JSONArray jsonArray= jsonObject.getJSONArray("list");
                            JSONObject listObj1=jsonArray.getJSONObject(0);
                            JSONObject main1=listObj1.getJSONObject("temp");
                            txtDate.setText(main1.getString("day"));
                            txtMin.setText("Min:"+main1.getDouble("min")+"'C");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject listObj=jsonArray.getJSONObject(i);
                                JSONObject main=listObj.getJSONObject("temp");
                                //Log.d("Main....","...."+main);
                                double temp = main.getDouble("min");
                                Log.d("temp", "" + new Date()+1+""+temp);
                            }

                        }catch (org.json.JSONException e) {

                        }*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*
                *
                * */
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


public void btnNextSevenDaysOnclick(View v){
    Intent intent=new Intent(MainActivity.this,NextSevenDaysActivity.class);
    intent.putExtra("list",(Serializable) weatherInfoList);
    startActivity(intent);
}

}
