package com.gao.android.http;

import android.graphics.Movie;

import com.gao.android.model.Subject;
import com.gao.android.model.UpdateInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by GaoMatrix on 2016/9/5.
 */
public interface ApiService {
    /**
     * Base url.
     */
    String API_SERVER_URL = "https://api.douban.com/v2/movie/";

    @GET("http://192.168.12.7/launcher_apks.json")
    Call<HttpResponse<UpdateInfo>> getUpdateInfo();

    @GET("top250")
    Call<Movie> getTopMovie1(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<Movie> getTopMovie2(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResponse<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
