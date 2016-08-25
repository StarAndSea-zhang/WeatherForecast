package com.zy.fengchun.weatherforecast.activity;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zy.fengchun.weatherforecast.R;
import com.zy.fengchun.weatherforecast.adater.WeatherAdapter;
import com.zy.fengchun.weatherforecast.adater.WeatherPagerAdapter;
import com.zy.fengchun.weatherforecast.entity.ResultWeather;
import com.zy.fengchun.weatherforecast.presenter.WeatherPesenter;
import com.zy.fengchun.weatherforecast.view.CustomRecyclerView;
import com.zy.fengchun.weatherforecast.view.CustomViewPager;
import com.zy.fengchun.weatherforecast.view.IWeatherView;

public class MainActivity extends BaseActivity<IWeatherView, WeatherPesenter> implements IWeatherView {
    
    private CustomViewPager mViewpager;
    
    private Toolbar mToolBar;
    
    private android.support.design.widget.TabLayout mTabLayout;
    
    private CustomRecyclerView mFutureRecyclerView;
    
    private View mDetailView;
    
    private CoordinatorLayout mRootView;
    
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    
    private Context mContext;
    
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initPagerView();
        new WeatherPesenter(this).fetch(this);
    }
    
    @Override
    protected WeatherPesenter createPresenter() {
        return new WeatherPesenter(this);
    }
    
    private void initPagerView() {
        mTabLayout = (android.support.design.widget.TabLayout) findViewById(R.id.tl_weather);
        mViewpager = (CustomViewPager) findViewById(R.id.vp_weather);
        LayoutInflater inflater = getLayoutInflater();
        List<View> views = new ArrayList<>();
        View future = inflater.inflate(R.layout.layout_future_item, null);
        mDetailView = inflater.inflate(R.layout.layout_detail_item, null);
        views.add(future);
        views.add(mDetailView);
        mViewpager.addView(mDetailView);
        mViewpager.addView(future);
        PagerAdapter pagerAdapter = new WeatherPagerAdapter(views, this, mTabLayout);
        mViewpager.setAdapter(pagerAdapter);
        // tabLayout必须在viewpager设置了adapter后调用
        mTabLayout.setupWithViewPager(mViewpager);
        mFutureRecyclerView = (CustomRecyclerView) future.findViewById(R.id.recyclerView);
        mFutureRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 如果适配器不能影响RcyclerView的大小变化。就设置true
        mFutureRecyclerView.setHasFixedSize(true);
        mFutureRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    
    private void initToolBar() {
        mContext = this;
        mToolBar = (Toolbar) findViewById(R.id.toolbar_weather);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctl_weather);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mRootView = (CoordinatorLayout) findViewById(R.id.cl_root);
    }
    
    @Override
    public void showLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    
    @Override
    public void showWeatherData(final ResultWeather weather) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCollapsingToolbarLayout.setTitle(weather.getToday().getCity());
                ((TextView) findViewById(R.id.tv_weather)).setText(weather.getToday().getWeather());
                ((TextView) findViewById(R.id.tv_week)).setText(weather.getToday().getWeek());
                ((TextView) findViewById(R.id.tv_temperature)).setText(weather.getToday().getTemperature());
                ((TextView) findViewById(R.id.tv_wind)).setText(weather.getToday().getWind());
                ((TextView) findViewById(R.id.tv_date)).setText(weather.getToday().getDate_y());
                mFutureRecyclerView.setAdapter(new WeatherAdapter(mContext, weather.getFuture()));
                ((TextView) mDetailView.findViewById(R.id.tv_travel_index)).setText(weather.getToday()
                                                                                           .getTravel_index());
                ((TextView) mDetailView.findViewById(R.id.tv_drying_index)).setText(weather.getToday()
                                                                                           .getDrying_index());
                ((TextView) mDetailView.findViewById(R.id.tv_exercise_index)).setText(weather.getToday()
                                                                                             .getExercise_index());
                ((TextView) mDetailView.findViewById(R.id.tv_uv_index)).setText(weather.getToday().getUv_index());
                ((TextView) mDetailView.findViewById(R.id.tv_humidity)).setText(weather.getSk().getHumidity());
                ((TextView) mDetailView.findViewById(R.id.tv_wash_index)).setText(weather.getToday().getWash_index());
                ((TextView) mDetailView.findViewById(R.id.tv_updatetime)).setText(weather.getToday().getDate_y());
                ((TextView) mDetailView.findViewById(R.id.tv_wind_strength)).setText(weather.getSk().getWind_strength());
                stopLoading();
            }
        });
    }
    
    @Override
    public void stopLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // mProgressBar.setVisibility(View.GONE);
            }
        });
        
    }
    
    @Override
    public void onLoadWeatherError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(mRootView, getString(R.string.error_loaddata_failed), Snackbar.LENGTH_LONG).show();
            }
        });
    }
    
    @Override
    public void onFailedOpenGPS() {
        Snackbar.make(mRootView, getString(R.string.error_no_gps), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.open_gps), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, 0);
                    }
                })
                .show();
    }
    
    @Override
    public void onNetworkNotWork() {
        Snackbar.make(mRootView, getString(R.string.error_no_network), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.open_network), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                        startActivityForResult(intent, 0);
                    }
                })
                .show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(this.getString(R.string.add_city));
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getOrder() == 0) {
            Intent intent = new Intent(this, CityActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
