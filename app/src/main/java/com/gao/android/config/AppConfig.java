package com.gao.android.config;

import com.orhanobut.logger.Logger;

/**
 * Created by GaoMatrix on 2016/9/3.
 */
public class AppConfig {
    /**
     * Logger的全局配置设置
     */
    public static void initLogger() {
//        Logger .init(YOUR_TAG)                 // default PRETTYLOGGER or use just init()
//                .methodCount(3)                 // default 2
//                .hideThreadInfo()               // default shown
//                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
//                .methodOffset(2)                // default 0
//                .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter
        Logger.init("Gao")
                .hideThreadInfo()
                .methodCount(1);
    }
}
