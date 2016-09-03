package com.gao.android.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gao.android.R;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/9/3.
 */
public class GlidActivity extends BaseActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.getView)
    Button getView;
    @BindDrawable(R.mipmap.ic_launcher)
    Drawable ic_launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.getView)
    public void onClick() {
        Glide.with(this).
                load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png")
                .override(500, 500)
                .placeholder(ic_launcher)
                .error(ic_launcher)
                .into(image);
    }

}
