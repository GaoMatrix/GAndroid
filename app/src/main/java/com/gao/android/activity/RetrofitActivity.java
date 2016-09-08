package com.gao.android.activity;

import android.os.Bundle;
import android.widget.Button;

import com.gao.android.R;
import com.gao.android.http.RetrofitClient;
import com.gao.android.model.Subject;
import com.gao.android.subscribers.ProgressSubscriber;
import com.gao.android.subscribers.SubscriberOnNextListener;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/9/5.
 */
public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.Top250Movie)
    Button Top250Movie;

    private SubscriberOnNextListener mGetTopMovieOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        mGetTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                Logger.d(subjects.toString());
            }
        };
    }

    @OnClick(R.id.Top250Movie)
    public void onClick() {
        RetrofitClient.getInstance().
                getTopMovie(new ProgressSubscriber(mGetTopMovieOnNext, RetrofitActivity.this), 0, 10);
    }
}
