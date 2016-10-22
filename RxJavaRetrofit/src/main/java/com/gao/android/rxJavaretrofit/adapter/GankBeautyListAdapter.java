package com.gao.android.rxjavaretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.model.GankBeauty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GaoMatrix on 2016/10/22.
 */
public class GankBeautyListAdapter extends RecyclerView.Adapter {
    private List<GankBeauty> mGankBeautyList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        GankBeauty gankBeauty = mGankBeautyList.get(position);
        Glide.with(holder.itemView.getContext()).load(gankBeauty.getUrl()).into(debounceViewHolder.mImageView);
        debounceViewHolder.mTextView.setText(gankBeauty.getDesc());
    }

    @Override
    public int getItemCount() {
        return mGankBeautyList == null ? 0 : mGankBeautyList.size();
    }

    public void setGankBeautyList(List<GankBeauty> gankBeautyList) {
        this.mGankBeautyList = gankBeautyList;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;
        @BindView(R.id.textView)
        TextView mTextView;

        DebounceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
