package com.mao.movie;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

public class BaseApplication extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //微信 appid appsecret
        PlatformConfig.setWeixin("wx6b92efe46c83d339", "25e5a842a6385cd4623db051675f968b");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public Context getContext() {
        return mContext;
    }
}
