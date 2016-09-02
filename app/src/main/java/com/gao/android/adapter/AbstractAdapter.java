package com.gao.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {
    protected Context mContext;

    protected List<T> mList;

    protected LayoutInflater mLayoutInflater;

    public AbstractAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        this.mList = list;
    }

    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (null != mList) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public abstract View getView(int position, View convertView,
            ViewGroup parent);
}
