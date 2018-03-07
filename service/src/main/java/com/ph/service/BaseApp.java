package com.ph.service;

import android.app.Application;
import android.content.Context;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 */

public class BaseApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }


}
