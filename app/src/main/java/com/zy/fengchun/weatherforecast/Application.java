package com.zy.fengchun.weatherforecast;

import android.content.Context;

import okhttp3.OkHttpClient;

/**
 * Created by fengchun on 2016/7/26.
 */
public class Application extends android.app.Application {

    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    public static OkHttpClient getHttpClient() {
        return mOkHttpClient;
    }
}
