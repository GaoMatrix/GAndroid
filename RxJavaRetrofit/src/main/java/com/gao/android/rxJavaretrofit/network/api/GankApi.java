package com.gao.android.rxjavaretrofit.network.api;

import com.gao.android.rxjavaretrofit.model.GankBeautyResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by GaoMatrix on 2016/10/21.
 */
public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeautyList(@Path("number") int number, @Path("page") int page);
}
