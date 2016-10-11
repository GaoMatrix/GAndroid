package com.gao.toolbar.http;

import android.util.Log;

import com.gao.android.util.TextUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpCallBack<T extends HttpResponse> implements Callback<T> {
    private String TAG = HttpCallBack.class.getSimpleName();
    private static Gson gson = new Gson(); //it is ok ?

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {                                  //Http 状态码code:[200,300）
            int responseCode = response.body().getCode();               //responseCode是api 里面定义的,进行进一步的数据和事件分发!
            if (responseCode == 0) {
                onSuccess(response.body());
            } else {
                onFailure(responseCode, response.body().getMessage());
            }
        } else {  // 一定要压倒所有的ＣＡＳＥ
            String errorBodyStr = "";
            try {
                errorBodyStr = TextUtils.convertUnicode(response.errorBody().string());
            } catch (IOException ioe) {
                Log.e("errorBodyStr ioe:", ioe.toString());
            }

            try {
                HttpResponse errorResponse = gson.fromJson(errorBodyStr, HttpResponse.class);
                if (null != errorResponse) {
                    onFailure(errorResponse.getCode(), errorResponse.getMessage());
                } else {
                    onFailure(-1, "ErrorResponse is null ");
                }
            } catch (Exception jsonException) {
                onFailure(-1, "Json 数据解析异常");
                jsonException.printStackTrace();
            }

        }//response is not Successful dispose over !

    }

    /**
     * 完善里面的各种细节
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof SocketTimeoutException) {
            //
        } else if (t instanceof ConnectException) {
            //
        } else if (t instanceof RuntimeException) {
            //
        }
        onFailure(-1, t.getMessage());                      //可能吧
    }


    public abstract void onSuccess(T t);

    public abstract void onFailure(int code, String message);


}
