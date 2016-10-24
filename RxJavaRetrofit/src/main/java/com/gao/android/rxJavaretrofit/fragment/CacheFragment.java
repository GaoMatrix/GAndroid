package com.gao.android.rxjavaretrofit.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.adapter.GankBeautyListAdapter;
import com.gao.android.rxjavaretrofit.cache.Data;
import com.gao.android.rxjavaretrofit.model.GankBeauty;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;

/**
 * Created by GaoMatrix on 2016/10/24.
 */
public class CacheFragment extends Fragment {

    @BindView(R.id.loadingTimeTextView)
    AppCompatTextView mLoadingTimeTextView;
    @BindView(R.id.getDataButton)
    AppCompatButton mGetDataButton;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.clearMemoryButton)
    AppCompatButton mClearMemoryButton;
    @BindView(R.id.clearMemoryDiskButton)
    AppCompatButton mClearMemoryDiskButton;

    private GankBeautyListAdapter mAdapter = new GankBeautyListAdapter();
    private Subscription mSubscription;
    private long mStartLoadingTime;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cache, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @OnClick({R.id.getDataButton, R.id.clearMemoryButton, R.id.clearMemoryDiskButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getDataButton:
                getData();
                break;
            case R.id.clearMemoryButton:
                mAdapter.setGankBeautyList(null);
                Data.getInstance().clearMemoryCache();
                Toast.makeText(getActivity(), R.string.memory_cache_cleared, Toast.LENGTH_SHORT).show();
                break;
            case R.id.clearMemoryDiskButton:
                mAdapter.setGankBeautyList(null);
                Data.getInstance().clearMemoryAndDiskCache();
                Toast.makeText(getActivity(), R.string.memory_and_disk_cache_cleared, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getData() {
        mSwipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        mStartLoadingTime = System.currentTimeMillis();
        mSubscription = Data.getInstance()
                .subscribeData(new Observer<List<GankBeauty>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<GankBeauty> gankBeauties) {
                        Logger.d("Load finished", gankBeauties);
                        Logger.d(gankBeauties);
                        mSwipeRefreshLayout.setRefreshing(false);
                        int loadingTime = (int) (System.currentTimeMillis() - mStartLoadingTime);
                        mLoadingTimeTextView
                                .setText(getString(R.string.loading_time_and_source, loadingTime, Data.getInstance().getDataSourceText()));
                        mAdapter.setGankBeautyList(gankBeauties);
                    }
                });
    }

    private void unSubscribe() {
    }
}
