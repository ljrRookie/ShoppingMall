package com.ljr.shoppingmall.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by LinJiaRong on 2017/6/4.
 * TODO：
 */

public class MyApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
