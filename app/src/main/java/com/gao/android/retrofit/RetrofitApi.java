package com.gao.android.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by GaoMatrix on 2016/9/5.
 */
public interface RetrofitApi {
    /**
     * Base url.
     */
    String API_SERVER_URL = "https://api.github.com/";

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);
}
