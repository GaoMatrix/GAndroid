package com.gao.android.rxjavaretrofit.network.api;

import com.gao.android.rxjavaretrofit.model.ZhuangbiImage;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by GaoMatrix on 2016/10/21.
 */
public interface ZhuangbiAPi {
    @GET("search")
    Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
