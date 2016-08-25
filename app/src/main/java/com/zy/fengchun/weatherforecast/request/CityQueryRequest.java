package com.zy.fengchun.weatherforecast.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zy.fengchun.weatherforecast.Application;
import com.zy.fengchun.weatherforecast.ConstantFiled;
import com.zy.fengchun.weatherforecast.activity.MainActivity;
import com.zy.fengchun.weatherforecast.dao.WeatherDBHelper;
import com.zy.fengchun.weatherforecast.entity.Result;
import com.zy.fengchun.weatherforecast.entity.ResultCity;
import com.zy.fengchun.weatherforecast.entity.ResultWeather;
import com.zy.fengchun.weatherforecast.utils.ACache;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fengchun on 2016/7/29.
 */
public class CityQueryRequest extends BaseRequest {

    @Override
    public String PeraredRequest() {
        String requestBody = "?" + ConstantFiled.Field_FORMAT + "=2&" + ConstantFiled.FIELD_APPKAY + "=" + ConstantFiled.APP_KEY;
        return requestBody;
    }
}
