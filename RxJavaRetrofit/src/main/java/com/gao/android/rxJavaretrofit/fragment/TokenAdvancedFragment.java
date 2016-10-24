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
public class TokenAdvancedFragment extends Fragment {
    @BindView(R.id.dataTextView)
    TextView mDataTextView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.getDataButton)
    Button mGetDataButton;
    @BindView(R.id.clearDataButton)
    Button mClearDataButton;

    private Subscription mSubscription;
    private boolean mIsTokenUpdated = false;
    private FakeToken mCachedFakeToken = new FakeToken(true);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token_advanced, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);
        return view;
    }

    @OnClick({R.id.getDataButton, R.id.clearDataButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getDataButton:
                getData();
                break;
            case R.id.clearDataButton:
                mCachedFakeToken.setExpired(true);
                Toast.makeText(getActivity(), R.string.token_destroyed, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getData() {
        mIsTokenUpdated = false;
        mSwipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        final FakeApi fakeApi = Network.getFakeApi();
        mSubscription = Observable.just(null)
                .flatMap(new Func1<Object, Observable<FakeThing>>() {

                    @Override
                    public Observable<FakeThing> call(Object o) {
                        return mCachedFakeToken.getToken() == null
                                ? Observable.<FakeThing>error(new NullPointerException("Token is null"))
                                : fakeApi.getFakeData(mCachedFakeToken);
                    }
                })
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                if (throwable instanceof IllegalArgumentException || throwable instanceof NullPointerException) {
                                    return fakeApi.getFakeToken("fake_auth_code")
                                            .doOnNext(new Action1<FakeToken>() {
                                                @Override
                                                public void call(FakeToken fakeToken) {
                                                    mIsTokenUpdated = true;
                                                    mCachedFakeToken.setToken(fakeToken.getToken());
                                                    mCachedFakeToken.setExpired(fakeToken.isExpired());
                                                }
                                            });
                                }
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FakeThing>() {
                    @Override
                    public void call(FakeThing fakeThing) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        String token = mCachedFakeToken.getToken();
                        if (mIsTokenUpdated) {
                            token += "(" + getString(R.string.updated) + ")";
                        }
                        mDataTextView.setText(getString(R.string.got_token_and_data, token, fakeThing.getId(), fakeThing.getName()));
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
