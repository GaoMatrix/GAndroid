package com.gao.android.rxjavaretrofit.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.adapter.ZhuangbiListAdapter;
import com.gao.android.rxjavaretrofit.model.ZhuangbiImage;
import com.gao.android.rxjavaretrofit.network.Network;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by GaoMatrix on 2016/10/21.
 */
public class ElementaryFragment extends Fragment {

    @BindView(R.id.searchRb1)
    AppCompatRadioButton mSearchRb1;
    @BindView(R.id.searchRb2)
    AppCompatRadioButton mSearchRb2;
    @BindView(R.id.searchRb3)
    AppCompatRadioButton mSearchRb3;
    @BindView(R.id.searchRb4)
    AppCompatRadioButton mSearchRb4;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ZhuangbiListAdapter mZhuangbiListAdapter = new ZhuangbiListAdapter();
    private Subscription mSubscription;

    rx.Observer<List<ZhuangbiImage>> mObserver = new rx.Observer<List<ZhuangbiImage>>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<ZhuangbiImage> zhuangbiImages) {
            mSwipeRefreshLayout.setRefreshing(false);
            mZhuangbiListAdapter.setImages(zhuangbiImages);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elementary, container, false);

        ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mZhuangbiListAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);

        return view;
    }

    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void onTagChecked(RadioButton radioButton, boolean checked){
        if (checked) {
            unSubscribe();
            mZhuangbiListAdapter.setImages(null);
            mSwipeRefreshLayout.setRefreshing(true);
            search(radioButton.getText().toString());
        }
    }

    private void unSubscribe() {
    }

    private void search(String key) {
        mSubscription = Network.getZhuangbiApi()
                .search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @OnClick({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4, R.id.recyclerView, R.id.swipeRefreshLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchRb1:
                break;
            case R.id.searchRb2:
                break;
            case R.id.searchRb3:
                break;
            case R.id.searchRb4:
                break;
            case R.id.recyclerView:
                break;
            case R.id.swipeRefreshLayout:
                break;
        }
    }
}
