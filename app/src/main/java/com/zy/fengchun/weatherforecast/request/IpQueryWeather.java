package com.zy.fengchun.weatherforecast.request;

import com.zy.fengchun.weatherforecast.ConstantFiled;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by fengchun on 2016/7/27.
 */
public class IpQueryWeather extends BaseRequest {
    private String mIp;

    public IpQueryWeather(String ip) {
        mIp = ip;
    }

    @Override
    public String PeraredRequest() {
        String requestBody = "?" + ConstantFiled.Field_FORMAT + "=2&" + ConstantFiled.FIELD_APPKAY + "=" + ConstantFiled.APP_KEY
                + "&" + ConstantFiled.Field_IP + "=" + mIp;
        return requestBody;
    }
}
