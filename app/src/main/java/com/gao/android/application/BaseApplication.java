package com.gao.android.application;

import android.app.Application;
import android.content.Context;

import com.gao.android.exception.CrashHandler;

public class BaseApplication extends Application {

    public Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
