package com.example.pankaj.deadpoolweatherapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView txtDate,txtMin,txtMax,txtDay,txtNight,txtMorning,txtEvening,txtMain,txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDate=(TextView) findViewById(R.id.txtDateDay);
        txtMin=(TextView) findViewById(R.id.txtMin);
        txtMax=(TextView) findViewById(R.id.txtMax);
        txtDay=(TextView) findViewById(R.id.txtDaytemp);
        txtNight=(TextView) findViewById(R.id.txtNighttemp);
        txtMorning=(TextView) findViewById(R.id.txtMorningtemp);
        txtEvening=(TextView) findViewById(R.id.txtEveingtemp);
        txtMain=(TextView) findViewById(R.id.txtmaininfo);
        txtDescription=(TextView) findViewById(R.id.txtmaininfoDescription);



        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=delhi%20&APPID=b1ed602908528e97950ba59fe098bdd1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       Log.d("Data....","...."+response);
                        try {
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

                        }
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




}
