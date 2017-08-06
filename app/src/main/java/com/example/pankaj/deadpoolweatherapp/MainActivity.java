package com.example.pankaj.deadpoolweatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import java.util.List;

/*  This class intent with weather information and cityname update the UI
*
* */

public class MainActivity extends AppCompatActivity {


    List<WeatherInfo> weatherInfoList=null;
    TextView cityName;
    TextView txtDate,txtMin,txtMax,txtDay,txtNight,txtMorning,txtEvening,txtMain,txtDescription;
    LinearLayout linearLayoutToday,linearLayoutTomorrow;
    TextView txtDateTomorrow,txtMintomorrow,txtMaxtomorrow,txtDaytomorrow,txtNighttomorrow,txtMorningtomorrow,txtEveningtomorrow,txtMaintomorrow,txtDescriptiontomorrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityName=(TextView) findViewById(R.id.txtCityName);


        //get view of today layout

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


        //Tomorrow get views
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



        try {
            Intent i = getIntent();
            weatherInfoList = (List<WeatherInfo>) i.getSerializableExtra("weatherList");
            cityName.setText(cityName.getText() + ":" + i.getStringExtra("cityName"));

            //Today weather information update
            txtDate.setText(weatherInfoList.get(0).getWeatherDate());
            txtMin.setText(String.valueOf("Min: " + weatherInfoList.get(0).getMinTemp() + " \u2103"));
            txtMax.setText("Max: " + String.valueOf(weatherInfoList.get(0).getMaxTemp() + " \u2103"));
            txtDay.setText("Day: " + String.valueOf(weatherInfoList.get(0).getDayTemp() + " \u2103"));
            txtNight.setText("Night: " + String.valueOf(weatherInfoList.get(0).getNightTemp() + " \u2103"));
            txtMorning.setText("Morning: " + String.valueOf(weatherInfoList.get(0).getMorningTemp() + " \u2103"));
            txtEvening.setText("Evening: " + String.valueOf(weatherInfoList.get(0).getEveningTemp() + " \u2103"));
            txtMain.setText("Weather: " + weatherInfoList.get(0).getMainMsg());
            txtDescription.setText("Description: " + weatherInfoList.get(0).getDescription());

            //Tomorrow weather information update
            txtDateTomorrow.setText(weatherInfoList.get(1).getWeatherDate());
            txtMintomorrow.setText("Min: " + String.valueOf(weatherInfoList.get(1).getMinTemp() + " \u2103"));
            txtMaxtomorrow.setText("Max: " + String.valueOf(weatherInfoList.get(1).getMaxTemp() + " \u2103"));
            txtDaytomorrow.setText("Day: " + String.valueOf(weatherInfoList.get(1).getDayTemp() + " \u2103"));
            txtNighttomorrow.setText("Night: " + String.valueOf(weatherInfoList.get(1).getNightTemp() + " \u2103"));
            txtMorningtomorrow.setText("Morning: " + String.valueOf(weatherInfoList.get(1).getMorningTemp() + " \u2103"));
            txtEveningtomorrow.setText("Evening: " + String.valueOf(weatherInfoList.get(1).getEveningTemp() + " \u2103"));
            txtMaintomorrow.setText("Weather: " + weatherInfoList.get(1).getMainMsg());
            txtDescriptiontomorrow.setText("Description: " + weatherInfoList.get(1).getDescription());
        }catch (Exception e){

        }
    }


public void btnNextSevenDaysOnclick(View v){

    // To nextsevendays activity to show data
    Intent intent=new Intent(MainActivity.this,NextSevenDaysActivity.class);
    intent.putExtra("list",(Serializable) weatherInfoList);
    startActivity(intent);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        menu.getItem(0).setTitle(Pref.getCity());
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_change_city:
                Pref.clearCity();
                Intent intent=new Intent(MainActivity.this,CityActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.item_change_setting:
                Toast.makeText(MainActivity.this,"Under Maintenance..!!!",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
