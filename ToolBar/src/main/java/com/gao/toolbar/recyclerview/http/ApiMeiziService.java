package com.gao.toolbar.recyclerview.http;

import com.gao.toolbar.recyclerview.entity.Meizi;
import com.gao.toolbar.recyclerview.http.HttpResponseMeizi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by GaoMatrix on 2016/9/5.
 */
public interface ApiMeiziService {
    /**
     * Base url.
     */
    String API_SERVER_URL = "http://gank.io/api/data/福利/10/";

    @GET("{page}")
    Call<HttpResponseMeizi<List<Meizi>>> getMeizi(@Path("page") int page);

}
