package com.gao.android.rxjavaretrofit;

import android.app.Application;

/**
 * Created by GaoMatrix on 2016/10/24.
 */
public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }
}
