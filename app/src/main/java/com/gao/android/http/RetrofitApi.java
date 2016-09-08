package com.gao.android.http;

import android.graphics.Movie;

import com.gao.android.model.HttpResult;
import com.gao.android.model.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by GaoMatrix on 2016/9/5.
 */
public interface RetrofitApi {
    /**
     * Base url.
     */
    String API_SERVER_URL = "https://api.douban.com/v2/movie/";

    @GET("top250")
    Call<Movie> getTopMovie1(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<Movie> getTopMovie2(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
