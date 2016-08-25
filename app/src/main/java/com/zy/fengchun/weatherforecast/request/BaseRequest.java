package com.zy.fengchun.weatherforecast.request;

import android.content.Context;

import com.zy.fengchun.weatherforecast.Application;
import com.zy.fengchun.weatherforecast.ConstantFiled;

import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fengchun on 2016/7/26.
 */
public abstract class BaseRequest {

    private Response mResponse;
    public Request createRequest(String action,Callback callback) {
        Request request = new Request.Builder().addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 4.4.2; NoxW Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36")
                .addHeader("Accept-Encoding", "gzip,deflate")
                .addHeader("Accept-Language", "zh-CN,en-US;q=0.8")
                .addHeader("X-Requested-With","com.android.browser")
                .addHeader("Accept-Charset", "utf-8").url(ConstantFiled.HOST + action+PeraredRequest()).get().build();
        Application.getHttpClient().newCall(request).enqueue(callback);
        return request;
    }

    protected abstract String PeraredRequest();
}
