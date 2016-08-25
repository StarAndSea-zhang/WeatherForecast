package com.zy.fengchun.weatherforecast.activity;

import android.app.Activity;
import android.os.Bundle;

import com.zy.fengchun.weatherforecast.R;
import com.zy.fengchun.weatherforecast.presenter.BasePresenter;

/**
 * Created by fengchun on 2016/7/29.
 */
public class CityActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
    }

}
