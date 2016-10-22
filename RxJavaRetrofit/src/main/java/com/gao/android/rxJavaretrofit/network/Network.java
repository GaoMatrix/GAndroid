package com.gao.android.rxjavaretrofit.network;

import com.gao.android.rxjavaretrofit.network.api.FakeApi;
import com.gao.android.rxjavaretrofit.network.api.GankApi;
import com.gao.android.rxjavaretrofit.network.api.ZhuangbiAPi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GaoMatrix on 2016/10/21.
 */
public class Network {
    private static ZhuangbiAPi sZhuangbiApi;
    private static GankApi sGankApi;
    private static FakeApi sFakeApi;
    private static Converter.Factory sGsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory sRxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private static OkHttpClient sOkHttpClient = buildOkHttpClient();

    private static OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置 Debug Log 模式
        builder.addInterceptor(loggingInterceptor);

        return builder.build();
    }

    public static ZhuangbiAPi getZhuangbiApi() {
        if (sZhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(sOkHttpClient)
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(sGsonConverterFactory)
                    .addCallAdapterFactory(sRxJavaCallAdapterFactory)
                    .build();
            sZhuangbiApi = retrofit.create(ZhuangbiAPi.class);
        }
        return sZhuangbiApi;
    }

    public static GankApi getGankApi() {
        if (sGankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(sOkHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(sGsonConverterFactory)
                    .addCallAdapterFactory(sRxJavaCallAdapterFactory)
                    .build();
            sGankApi = retrofit.create(GankApi.class);
        }
        return sGankApi;
    }

    public static FakeApi getFakeApi() {
        if (sFakeApi == null) {
            sFakeApi = new FakeApi();
        }
        return sFakeApi;
    }

}
