package com.zy.fengchun.weatherforecast.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by fengchun on 2016/8/1.
 */
public class CustomViewPager extends ViewPager {
    private float mOldX;
    private float mOldY;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}
