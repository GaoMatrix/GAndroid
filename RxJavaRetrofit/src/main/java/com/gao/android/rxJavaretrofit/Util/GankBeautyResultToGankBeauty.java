package com.gao.android.rxjavaretrofit.Util;

import com.gao.android.rxjavaretrofit.model.GankBeauty;
import com.gao.android.rxjavaretrofit.model.GankBeautyResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by GaoMatrix on 2016/10/22.
 */
public class GankBeautyResultToGankBeauty implements Func1<GankBeautyResult, List<GankBeauty>> {
    private static GankBeautyResultToGankBeauty sInstance = new GankBeautyResultToGankBeauty();

    private GankBeautyResultToGankBeauty() {
    }

    public static GankBeautyResultToGankBeauty getInstance() {
        return sInstance;
    }

    @Override
    public List<GankBeauty> call(GankBeautyResult gankBeautyResult) {
        List<GankBeauty> gankBeautyList = gankBeautyResult.getGankBeautyList();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty : gankBeautyList) {
            try {
                Date date = inputFormat.parse(gankBeauty.getCreatedAt());
                gankBeauty.setDesc(outputFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
                gankBeauty.setDesc("unknown date");
            }

        }
        return gankBeautyList;
    }
}
