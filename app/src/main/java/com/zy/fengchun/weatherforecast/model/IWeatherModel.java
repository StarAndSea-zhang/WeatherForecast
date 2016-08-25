package com.zy.fengchun.weatherforecast.model;

import android.content.Context;

import com.zy.fengchun.weatherforecast.entity.ResultWeather;

import java.util.logging.Handler;

/**
 * Created by fengchun on 2016/7/29.
 */
public interface IWeatherModel {
    void loadWeatherData(OnWeatherLoadListener onWeatherLoadListener, Context context);
    interface OnWeatherLoadListener{
        void onComplete(ResultWeather weather);
        void onError();
        void onFailedOpenGPS();
        void onNetworkNotWork();
        void stopLoading();
    }
}
