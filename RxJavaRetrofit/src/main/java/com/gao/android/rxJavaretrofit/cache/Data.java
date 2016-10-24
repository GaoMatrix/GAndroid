package com.gao.android.rxjavaretrofit.cache;

import android.support.annotation.IntDef;

import com.gao.android.diskcache.acache.ACache;
import com.gao.android.rxjavaretrofit.App;
import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.Util.GankBeautyResultToGankBeauty;
import com.gao.android.rxjavaretrofit.model.GankBeauty;
import com.gao.android.rxjavaretrofit.network.Network;
import com.gao.android.util.ListUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by GaoMatrix on 2016/10/24.
 */
public class Data {
    private static String DATA_FILE_NAME = "data.db";

    private static Data sInstance;

    private static final int DATA_SOURCE_MEMORY = 1;
    private static final int DATA_SOURCE_DISK = 2;
    private static final int DATA_SOURCE_NETWORK = 3;

    @IntDef({DATA_SOURCE_DISK, DATA_SOURCE_MEMORY, DATA_SOURCE_NETWORK})
    @interface DataSource {
    }

    private int mDataSource;

    /**
     * BehaviorSubject<T> extends Subject<T, T>
     * Subject Represents an object that is both an Observable and an Observer.
     */
    private BehaviorSubject<List<GankBeauty>> mCache;

    private ACache mDiskCache;

    private Data() {
        mDiskCache = ACache.get(App.getInstance().getApplicationContext());
    }

    public static synchronized Data getInstance() {
        if (sInstance == null) {
            sInstance = new Data();
        }
        return sInstance;
    }

    private void setDataSource(@DataSource int dataSource) {
        this.mDataSource = dataSource;
    }

    public String getDataSourceText() {
        int dataSourceTextRes;
        switch (mDataSource) {
            case DATA_SOURCE_MEMORY:
                dataSourceTextRes = R.string.data_source_memory;
                break;
            case DATA_SOURCE_DISK:
                dataSourceTextRes = R.string.data_source_disk;
                break;
            case DATA_SOURCE_NETWORK:
                dataSourceTextRes = R.string.data_source_network;
                break;
            default:
                dataSourceTextRes = R.string.data_source_network;
        }
        return App.getInstance().getString(dataSourceTextRes);
    }

    public Subscription subscribeData(Observer<List<GankBeauty>> observer) {
        if (mCache == null) {
            mCache = BehaviorSubject.create();
            Observable.create(new Observable.OnSubscribe<List<GankBeauty>>() {

                @Override
                public void call(Subscriber<? super List<GankBeauty>> subscriber) {
                    List<GankBeauty> itemList = getDataFromDisk(DATA_FILE_NAME);
                    if (ListUtils.isEmpty(itemList)) {
                        Logger.d("Load data from network");
                        setDataSource(DATA_SOURCE_NETWORK);
                        loadFromNetwork();
                    } else {
                        Logger.d("Load data from disk");
                        setDataSource(DATA_SOURCE_DISK);
                        subscriber.onNext(itemList);
                    }
                }
            })
                    .subscribeOn(Schedulers.io())
                    .subscribe(mCache);
        } else {
            Logger.d("Load data from memory.");
            setDataSource(DATA_SOURCE_MEMORY);
        }
        return mCache.observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    private void loadFromNetwork() {
        Network.getGankApi()
                .getBeautyList(100, 1)
                .subscribeOn(Schedulers.io())
                .map(GankBeautyResultToGankBeauty.getInstance())
                .doOnNext(new Action1<List<GankBeauty>>() {
                    @Override
                    public void call(List<GankBeauty> gankBeauties) {
                        Logger.d("Cache data to disk.");
                        String gsonStr = new Gson().toJson(gankBeauties);
                        mDiskCache.put(DATA_FILE_NAME, gsonStr);
                    }
                })
                .subscribe(new Action1<List<GankBeauty>>() {
                    @Override
                    public void call(List<GankBeauty> gankBeauties) {
                        Logger.d("Get data from network.");
                        mCache.onNext(gankBeauties);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private List<GankBeauty> getDataFromDisk(String key) {
        Logger.d("getDataFromDisk");
        List<GankBeauty> itemList = new Gson().fromJson(mDiskCache.getAsString(key), new TypeToken<List<GankBeauty>>() {
        }.getType());
        return itemList;
    }

    public void clearMemoryCache() {
        Logger.d("clearMemoryCache");
        mCache = null;
    }

    public void clearMemoryAndDiskCache() {
        Logger.d("clearMemoryAndDiskCache");
        clearMemoryCache();
        mDiskCache.remove(DATA_FILE_NAME);
    }
}
