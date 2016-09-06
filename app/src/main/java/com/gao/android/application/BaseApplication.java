package com.gao.android.application;

import android.app.Application;
import android.content.Context;

import com.gao.android.config.AppConfig;
import com.gao.android.exception.CrashHandler;

public class BaseApplication extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance());
        // Logger配置信息
        AppConfig.initLogger();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public Context getContext() {
        return mContext;
    }
}
