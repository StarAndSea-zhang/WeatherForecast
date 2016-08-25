package com.zy.fengchun.weatherforecast.presenter;

import android.content.Context;

import com.zy.fengchun.weatherforecast.dao.WeatherDBHelper;
import com.zy.fengchun.weatherforecast.entity.ResultWeather;
import com.zy.fengchun.weatherforecast.model.IWeatherModel;
import com.zy.fengchun.weatherforecast.model.WeatherModelImp;
import com.zy.fengchun.weatherforecast.view.IWeatherView;

import java.util.logging.Handler;

/**
 * Created by fengchun on 2016/7/29.
 */
public class WeatherPesenter extends BasePresenter<IWeatherView> {

    private IWeatherModel mWeatherModel;

    public WeatherPesenter(IWeatherView weatherView) {
        super();
        mWeatherModel = new WeatherModelImp();
        attachView(weatherView);

    }

    public void fetch(Context context) {
        getView().showLoading();
        mWeatherModel.loadWeatherData(new IWeatherModel.OnWeatherLoadListener() {

            @Override
            public void onComplete(ResultWeather weather) {
                getView().showWeatherData(weather);
            }

            @Override
            public void onError() {
            }

            @Override
            public void onFailedOpenGPS() {
                getView().onFailedOpenGPS();
            }

            @Override
            public void onNetworkNotWork() {
                getView().onNetworkNotWork();
            }

            @Override
            public void stopLoading() {
                getView().stopLoading();
            }
        }, context);
        getView().stopLoading();
    }
}
