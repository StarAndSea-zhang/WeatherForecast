package com.zy.fengchun.weatherforecast.view;

import com.zy.fengchun.weatherforecast.entity.ResultWeather;

/**
 * Created by fengchun on 2016/7/29.
 */
public interface IWeatherView {
    void showLoading();
    void showWeatherData(ResultWeather weather);
    void stopLoading();
    void onLoadWeatherError();
    void onFailedOpenGPS();
    void onNetworkNotWork();
}
