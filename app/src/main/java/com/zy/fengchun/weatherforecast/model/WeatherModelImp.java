package com.zy.fengchun.weatherforecast.model;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zy.fengchun.weatherforecast.ConstantFiled;
import com.zy.fengchun.weatherforecast.R;
import com.zy.fengchun.weatherforecast.activity.MainActivity;
import com.zy.fengchun.weatherforecast.entity.Result;
import com.zy.fengchun.weatherforecast.entity.ResultWeather;
import com.zy.fengchun.weatherforecast.request.CityQueryRequest;
import com.zy.fengchun.weatherforecast.request.GeoQueryWeather;
import com.zy.fengchun.weatherforecast.request.IpQueryWeather;
import com.zy.fengchun.weatherforecast.utils.ACache;
import com.zy.fengchun.weatherforecast.utils.LocationUtils;
import com.zy.fengchun.weatherforecast.utils.NetworkUtils;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fengchun on 2016/7/29.
 */
public class WeatherModelImp implements IWeatherModel {
    @Override
    public void loadWeatherData(final OnWeatherLoadListener onWeatherLoadListener, final Context context) {
        if (NetworkUtils.checkNetWorkEnable(context) == true) {
            final Callback geoCallback = new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    onWeatherLoadListener.onError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Gson gson = new Gson();
                    Result<ResultWeather> result = new Result<ResultWeather>();
                    String parsed = null;
                    try {
                        parsed = response.body().string();
                        result = gson.fromJson(parsed, new TypeToken<Result<ResultWeather>>() {
                        }.getType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //如果响应获取到正确的天气数据，进行缓存
                    if (result.getResult() != null && result.getError_code().equals(ConstantFiled.NOERROR)) {
                        ACache aCache = ACache.get(context);
                        aCache.put("weather_data", parsed, 2 * ACache.TIME_DAY);

                        onWeatherLoadListener.onComplete(result.getResult());
                    }
                }
            };
            Callback ipCallback = new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    onWeatherLoadListener.onError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Gson gson = new Gson();
                    Result<ResultWeather> result = new Result<ResultWeather>();
                    String parsed = null;
                    try {
                        parsed = response.body().string();
                        result = gson.fromJson(parsed, new TypeToken<Result<ResultWeather>>() {
                        }.getType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //如果响应获取到正确的天气数据
                    if (result.getResult() != null && result.getError_code().equals(ConstantFiled.NOERROR)) {
                        ACache aCache = ACache.get(context);
                        aCache.put("weather_data", parsed, 2 * ACache.TIME_DAY);
                        onWeatherLoadListener.onComplete(result.getResult());
                    }
                    //否则通过GPS请求
                    else {
                        Location location = LocationUtils.getLocation(context);
                        if (location != null) {
                            new GeoQueryWeather(String.valueOf(location.getLongitude()), String.valueOf(location.getLatitude())).createRequest(ConstantFiled.ACTION_GEO_QUERY, geoCallback);
                        } else if (location == null) {
                            onWeatherLoadListener.onFailedOpenGPS();
                        }
                    }
                }
            };
            //首先根据请求IP请求
            new IpQueryWeather(NetworkUtils.getIpAddress(context)).createRequest(ConstantFiled.ACTION_IP_QUERY, ipCallback);

        }
        //网络未打开错误
        else {
            onWeatherLoadListener.onNetworkNotWork();
        }
    }

}
