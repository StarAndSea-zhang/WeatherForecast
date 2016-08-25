package com.zy.fengchun.weatherforecast.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by fengchun on 2016/8/2.
 */
public class CustomRecyclerView extends RecyclerView {
    //用作保存X,Y
    float mOldX =0,mOldY = 0;
    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            //存储down动作下的X、Y
            case MotionEvent.ACTION_DOWN:
                mOldX = ev.getX();
                mOldY = ev.getY();
                //requestDisallowInterceptTouchEvent为true说明子控件也分发事件,事实上，ViewGroup的onInterceptTouchEvent默认不拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_MOVE:
                //△X<△Y说明上下滑动，需要分发给子控件
                if (Math.abs(ev.getX()-mOldX)<Math.abs(ev.getY()-mOldY))
                {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                //△X>△Y说明左右滑动 不需要分发
                else{
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        boolean b = super.dispatchTouchEvent(ev);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            //存储down动作下的X、Y
            case MotionEvent.ACTION_DOWN:
                mOldX = ev.getX();
                mOldY = ev.getY();
                //requestDisallowInterceptTouchEvent为true说明子控件也分发事件,事实上，ViewGroup的onInterceptTouchEvent默认不拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_MOVE:
                //△X<△Y说明上下滑动，需要分发给子控件
                if (Math.abs(ev.getX()-mOldX)<Math.abs(ev.getY()-mOldY))
                {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                //△X>△Y说明左右滑动 不需要分发
                else{
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        boolean b = super.onInterceptTouchEvent(ev);
        return b;
    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch(e.getAction()){
            //存储down动作下的X、Y
            case MotionEvent.ACTION_DOWN:
                mOldX = e.getX();
                mOldY = e.getY();
                //requestDisallowInterceptTouchEvent为true说明子控件也分发事件,事实上，ViewGroup的onInterceptTouchEvent默认不拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_MOVE:
                //△X<△Y说明上下滑动，需要分发给子控件
                if (Math.abs(e.getX()-mOldX)<Math.abs(e.getY()-mOldY))
                {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                //△X>△Y说明左右滑动 不需要分发
                else{
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        boolean b = super.onTouchEvent(e);
        return b;
    }
}
