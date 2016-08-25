package com.zy.fengchun.weatherforecast.entity;

import java.io.Serializable;

/**
 * Created by fengchun on 2016/7/26.
 */
public class FutureWeather implements Serializable{
    private String wind;
    private String date;
    private String week;
    private String temperature;
    private String weather;
    private WeatherId weather_id;

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public WeatherId getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(WeatherId weather_id) {
        this.weather_id = weather_id;
    }

    @Override
    public String toString() {
        return "FutureWeather{" +
                "wind='" + wind + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + weather_id +
                '}';
    }
}
