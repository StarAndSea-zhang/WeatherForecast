package com.zy.fengchun.weatherforecast.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zy.fengchun.weatherforecast.ConstantFiled;
import com.zy.fengchun.weatherforecast.activity.MainActivity;
import com.zy.fengchun.weatherforecast.entity.Result;
import com.zy.fengchun.weatherforecast.entity.ResultWeather;
import com.zy.fengchun.weatherforecast.utils.ACache;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by fengchun on 2016/7/27.
 */
public class GeoQueryWeather extends BaseRequest {

    private String mLongitude;
    private String mLatitude;

    /**
     *
     * @param longitude
     * @param latitude
     */
    public GeoQueryWeather(String longitude, String latitude) {
        this.mLongitude = longitude;
        this.mLatitude = latitude;
    }

    @Override
    public String PeraredRequest() {
        String requestBody = "?" + ConstantFiled.Field_FORMAT + "=2&" + ConstantFiled.FIELD_APPKAY + "=" + ConstantFiled.APP_KEY
                + "&" +ConstantFiled.FIELD_LOCATION + "="+ mLongitude
                + "&"+ConstantFiled.FIELD_LATITUDE+"="+mLatitude;
        return requestBody;
    }

}
