package com.gao.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gao.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.glide)
    Button glide;
    @BindView(R.id.retrofit)
    Button retrofit;
    @BindView(R.id.adapter)
    Button adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.glide, R.id.retrofit, R.id.logger, R.id.adapter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.glide:
                startActivity(new Intent(this, GlidActivity.class));
                break;
            case R.id.logger:
                startActivity(new Intent(this, LoggerActivity.class));
                break;
            case R.id.retrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.adapter:
                startActivity(new Intent(this, AdapterActivity.class));
                break;
        }
    }
}
