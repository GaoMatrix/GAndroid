package com.gao.android.activity;

import android.os.Bundle;
import android.widget.Button;

import com.gao.android.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/9/3.
 * https://github.com/orhanobut/logger
 */
public class LoggerActivity extends BaseActivity {

    @BindView(R.id.logger)
    Button logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.logger)
    public void onClick() {
        Logger.d("onClick");
        Logger.init().methodCount(3);
        Logger.d("onClick");
        Logger.init().methodCount(1);
        Logger.d("onClick");
        Logger.init().methodCount(0);
        Logger.d("onClick");
        Logger.init().methodCount(0);
        Logger.d("onClick");
        Logger.init().methodCount(0).hideThreadInfo();
        Logger.d("onClick");
        Logger.t("Logger").d("onClick");
        Logger.init("Gao");
        Logger.d("onClick");
    }
}
