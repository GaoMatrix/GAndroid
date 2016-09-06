package com.gao.android.activity;

import android.os.Bundle;
import android.widget.Button;

import com.gao.android.R;
import com.gao.android.retrofit.Contributor;
import com.gao.android.retrofit.RetrofitApi;
import com.gao.android.retrofit.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by GaoMatrix on 2016/9/5.
 */
public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.getContributors)
    Button getContributors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.getContributors)
    public void onClick() {
        Retrofit retrofit = RetrofitClient.getRetrofit();
        RetrofitApi repo = retrofit.create(RetrofitApi.class);
        String userName = "square";
        String userRepo = "retrofit";
        Call<ResponseBody> call = repo.contributorsBySimpleGetCall(userName, userRepo);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Logger.json(response.body().toString());
                    Gson gson = new Gson();
                    ArrayList<Contributor> contributorsList = gson.fromJson(response.body().string(), new TypeToken<List<Contributor>>(){}.getType());
                    for (Contributor contributor : contributorsList){
                        Logger.d("login : " + contributor.getLogin());
                        Logger.d("contributions : " + contributor.getContributions()+"");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
