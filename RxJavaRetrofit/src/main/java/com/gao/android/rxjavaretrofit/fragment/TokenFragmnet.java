package com.gao.android.rxjavaretrofit.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.model.FakeThing;
import com.gao.android.rxjavaretrofit.model.FakeToken;
import com.gao.android.rxjavaretrofit.network.Network;
import com.gao.android.rxjavaretrofit.network.api.FakeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by GaoMatrix on 2016/10/22.
 */
public class TokenFragmnet extends Fragment {

    @BindView(R.id.dataTextView)
    TextView mDataTextView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.getDataButton)
    Button mGetDataButton;

    private Subscription mSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);
        return view;
    }

    @OnClick(R.id.getDataButton)
    public void onClick() {
        getData();
    }

    private void getData() {
        mSwipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        final FakeApi fakeApi = Network.getFakeApi();
        // 需要先请求 token 再访问的接口，
        // 使用 flatMap() 将 token 的请求和实际数据的请求连贯地串起来，
        // 而不必写嵌套的 Callback 结构。
        mSubscription = fakeApi.getFakeToken("fake_auth_code")
                .flatMap(new Func1<FakeToken, Observable<FakeThing>>() {
                    @Override
                    public Observable<FakeThing> call(FakeToken fakeToken) {
                        return fakeApi.getFakeData(fakeToken);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FakeThing>() {
                    @Override
                    public void call(FakeThing fakeThing) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mDataTextView.setText(getString(R.string.got_data, fakeThing.getId(), fakeThing.getName()));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
