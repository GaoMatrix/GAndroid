package com.gao.android.rxjavaretrofit.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.Util.GankBeautyResultToGankBeauty;
import com.gao.android.rxjavaretrofit.adapter.GankBeautyListAdapter;
import com.gao.android.rxjavaretrofit.model.GankBeauty;
import com.gao.android.rxjavaretrofit.network.Network;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by GaoMatrix on 2016/10/22.
 */
public class MapFragment extends Fragment {

    @BindView(R.id.previousPageButton)
    AppCompatButton mPreviousPageButton;
    @BindView(R.id.nextPageButton)
    AppCompatButton mNextPageButton;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.pageIndexTextView)
    TextView mPageIndexTextView;

    private int mPage = 1;
    private GankBeautyListAdapter mAdapter = new GankBeautyListAdapter();
    private Subscription mSubscription;

    private Observer<List<GankBeauty>> mObserver = new Observer<List<GankBeauty>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<GankBeauty> gankBeauties) {
            mSwipeRefreshLayout.setRefreshing(false);
            mPageIndexTextView.setText(getString(R.string.page_with_number, mPage));
            mAdapter.setGankBeautyList(gankBeauties);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);
        return view;
    }

    @OnClick({R.id.previousPageButton, R.id.nextPageButton, R.id.recyclerView, R.id.swipeRefreshLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previousPageButton:
                mPage--;
                loadPage(mPage);
                if (mPage == 1) {
                    mPreviousPageButton.setEnabled(false);
                }
                break;
            case R.id.nextPageButton:
                mPage++;
                loadPage(mPage);
                if (mPage == 2) {
                    mPreviousPageButton.setEnabled(true);
                }
                break;
        }
    }

    private void loadPage(int page) {
        mSwipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        mSubscription = Network.getGankApi()
                .getBeautyList(10, page)
                .map(GankBeautyResultToGankBeauty.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    protected void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
