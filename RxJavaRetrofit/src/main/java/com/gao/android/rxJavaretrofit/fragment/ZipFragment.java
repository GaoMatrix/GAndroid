package com.gao.android.rxjavaretrofit.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.Util.GankBeautyResultToGankBeauty;
import com.gao.android.rxjavaretrofit.adapter.ItemListAdapter;
import com.gao.android.rxjavaretrofit.model.GankBeauty;
import com.gao.android.rxjavaretrofit.model.Item;
import com.gao.android.rxjavaretrofit.model.ZhuangbiImage;
import com.gao.android.rxjavaretrofit.network.Network;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by GaoMatrix on 2016/10/22.
 */
public class ZipFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ItemListAdapter mAdapter = new ItemListAdapter();
    private Subscription mSubscription;

    private Observer<List<Item>> mObserver = new Observer<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<Item> items) {
            mAdapter.setItemList(items);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zip, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mAdapter);

        getData();
        return view;
    }

    private void getData() {
        mSwipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        mSubscription = rx.Observable.zip(
                Network.getGankApi().getBeautyList(100, 1).map(GankBeautyResultToGankBeauty.getInstance()),
                Network.getZhuangbiApi().search("装逼"),
                new Func2<List<GankBeauty>, List<ZhuangbiImage>, List<Item>>() {
                    @Override
                    public List<Item> call(List<GankBeauty> gankBeauties, List<ZhuangbiImage> zhuangbiImages) {
                        List<Item> itemList = new ArrayList<Item>();
                        for (int i = 0; i < gankBeauties.size()/2 && i < zhuangbiImages.size(); i++) {
                            Item gankItem1 = new Item();
                            gankItem1.setDescription(gankBeauties.get(i*2).getDesc());
                            gankItem1.setImageUrl(gankBeauties.get(i*2).getUrl());
                            itemList.add(gankItem1);

                            Item gankItem2 = new Item();
                            gankItem2.setDescription(gankBeauties.get(i*2+1).getDesc());
                            gankItem2.setImageUrl(gankBeauties.get(i*2+1).getUrl());
                            itemList.add(gankItem2);

                            Item zhuangbiItem  = new Item();
                            zhuangbiItem.setDescription(zhuangbiImages.get(i).getDescription());
                            zhuangbiItem.setImageUrl(zhuangbiImages.get(i).getImage_url());
                            itemList.add(zhuangbiItem);
                        }

                        return itemList;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    private void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
