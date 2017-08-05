package com.example.pankaj.deadpoolweatherapp;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by pankaj on 6/8/17.
 */

public class WeatherInfo  implements Serializable{

    private String weatherDate;
    private double minTemp;
    private double maxTemp;
    private double dayTemp;
    private double nightTemp;
    private double morningTemp;
    private double eveningTemp;
    private String mainMsg;
    private  String description;
    private double humidity;

    public String getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public String getMainMsg() {
        return mainMsg;
    }

    public void setMainMsg(String mainMsg) {
        this.mainMsg = mainMsg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public double getMorningTemp() {
        return morningTemp;
    }


    public void setMorningTemp(double morningTemp) {
        this.morningTemp = morningTemp;
    }

    public double getEveningTemp() {
        return eveningTemp;
    }

    public void setEveningTemp(double eveningTemp) {
        this.eveningTemp = eveningTemp;
    }


public String toString(){



    return "Date: "+weatherDate+"\t" +
            "Min:"+minTemp+"\t" +
            "Max:"+maxTemp+"" +
            "Day:"+dayTemp+"\t" +
            "Night:"+nightTemp+"\t" +
            "Morning"+morningTemp+"\t" +
            "Eveninh"+eveningTemp+"\t" +
            "Mian:"+mainMsg+"\t" +
            "Des:"+description;
}


}
