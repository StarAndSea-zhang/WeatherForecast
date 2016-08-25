package com.zy.fengchun.weatherforecast.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengchun on 2016/7/27.
 */
public class ResultWeather  implements Serializable {
    private WeatherSituation sk;
    private TodayWeather today;
    private List<FutureWeather> future = new ArrayList<FutureWeather>();

    public WeatherSituation getSk() {
        return sk;
    }

    public void setSk(WeatherSituation sk) {
        this.sk = sk;
    }

    public TodayWeather getToday() {
        return today;
    }

    public void setToday(TodayWeather today) {
        this.today = today;
    }

    public List<FutureWeather> getFuture() {
        return future;
    }

    public void setFuture(List<FutureWeather> future) {
        this.future = future;
    }

    @Override
    public String toString() {
        return "ResultWeather{" +
                "sk=" + sk +
                ", today=" + today +
                ", future=" + future +
                '}';
    }
}