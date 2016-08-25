package com.zy.fengchun.weatherforecast.adater;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.zy.fengchun.weatherforecast.R;
import com.zy.fengchun.weatherforecast.presenter.WeatherPesenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengchun on 2016/8/1.
 */
public class WeatherPagerAdapter extends PagerAdapter {

    private List<View> mLists = new ArrayList<View>();
    private Context mContext;
    private TabLayout mTabLayout;

    @Override
    public CharSequence getPageTitle(int position) {
        String title = mContext.getResources().getStringArray(R.array.array_pager_title)[position];
        mTabLayout.newTab().setText(title);
        return title;
    }

    public WeatherPagerAdapter(List<View> lists,Context context,TabLayout tabLayout){
        mLists = lists;
        mContext = context;
        mTabLayout = tabLayout;
    }
    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mLists.get(position)!=null){
            ((ViewPager)container).addView(mLists.get(position),0);
        }
        return mLists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}
