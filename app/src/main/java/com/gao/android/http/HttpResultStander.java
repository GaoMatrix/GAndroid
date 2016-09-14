package com.gao.android.http;

/**
 * Created by GaoMatrix on 2016/9/6.
 */
public class HttpResultStander<T> {
    public int code;
    public String msg;
    public T data;
    public long count;
    public long page;

    @Override
    public String toString() {
        return "HttpResultStander{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", count=" + count +
                ", page=" + page +
                '}';
    }
}

