package com.gao.android.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.gao.android.R;
import com.gao.android.http.ApiService;
import com.gao.android.http.HttpCallBack;
import com.gao.android.http.HttpResponse;
import com.gao.android.http.RetrofitClient;
import com.gao.android.model.UpdateInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by GaoMatrix on 2016/9/5.
 */
public class RetrofitActivity extends BaseActivity {
    private static final String TAG = "RetrofitActivity";

    @BindView(R.id.Top250Movie)
    Button Top250Movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Top250Movie)
    public void onClick() {
        Call<HttpResponse<UpdateInfo>> updateCall = RetrofitClient.getClient(ApiService.class).getUpdateInfo();
        /*updateCall.enqueue(new Callback<HttpResponse<UpdateInfo>>() {
            @Override
            public void onResponse(Call<HttpResponse<UpdateInfo>> call, Response<HttpResponse<UpdateInfo>> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<HttpResponse<UpdateInfo>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });*/
        updateCall.enqueue(new HttpCallBack<HttpResponse<UpdateInfo>>() {
            @Override
            public void onSuccess(HttpResponse<UpdateInfo> updateInfoHttpResponse) {
                Log.d(TAG, "onSuccess: " + updateInfoHttpResponse.toString());
            }

            @Override
            public void onFailure(int code, String message) {
                Log.e(TAG, "onFailure: code:" + code + ", message: " + message);
            }
        });
    }
}
