package com.gao.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.gao.android.util.ActivityManagerUtils;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for subclass
        initView();
        // for subclass
        initData();
        // add Activity to Activity Stack
        ActivityManagerUtils.getInstance().addActivity(this);
    }

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // finish this Activity
        ActivityManagerUtils.getInstance().finishActivity(this);
    }
}
