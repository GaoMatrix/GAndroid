package com.gao.android.rxjavaretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GaoMatrix on 2016/10/22.
 */
public class GankBeautyResult {
    private boolean error;
    private @SerializedName("results")
    List<GankBeauty> gankBeautyList;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankBeauty> getGankBeautyList() {
        return gankBeautyList;
    }

    public void setGankBeautyList(List<GankBeauty> gankBeautyList) {
        this.gankBeautyList = gankBeautyList;
    }
}
