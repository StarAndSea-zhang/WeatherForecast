package com.zy.fengchun.weatherforecast.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.zy.fengchun.weatherforecast.presenter.BasePresenter;

/**
 * Created by fengchun on 2016/7/29.
 */
public abstract class BaseActivity<V,T extends BasePresenter<V>> extends ActionBarActivity {

    protected T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        mPresenter = createPresenter();

        mPresenter.attachView((V)this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
    protected abstract T createPresenter();
}
