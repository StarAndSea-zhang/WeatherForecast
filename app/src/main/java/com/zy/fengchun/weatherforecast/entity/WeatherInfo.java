package com.zy.fengchun.weatherforecast.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fengchun on 2016/7/26.
 */
public class WeatherInfo implements Serializable {
    private String wind;
    private String date;
    private String windStrength;
    private String dressAdvice;
    private String week;
    private String weatherDescri;
    private String humidity;
    private String windDirection;
    private String temperature;
    private String dressIndex;
    private List<WeatherInfo> weatherInfos;
}
