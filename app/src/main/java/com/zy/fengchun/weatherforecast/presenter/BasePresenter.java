package com.zy.fengchun.weatherforecast.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by fengchun on 2016/7/28.
 */
public abstract class BasePresenter<T> {
    protected WeakReference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    public void detachView(){
       if (mViewRef!=null){
           mViewRef.clear();
           mViewRef =null;
       }
    }

    public T getView() {
        return mViewRef.get();
    }


}
