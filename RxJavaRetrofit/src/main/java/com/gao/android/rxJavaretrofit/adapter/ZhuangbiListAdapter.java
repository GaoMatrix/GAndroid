package com.gao.android.rxjavaretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.model.ZhuangbiImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GaoMatrix on 2016/10/21.
 */
public class ZhuangbiListAdapter extends RecyclerView.Adapter {
    private List<ZhuangbiImage> mZhuangbiImageList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        ZhuangbiImage zhuangbiImage = mZhuangbiImageList.get(position);
        Glide.with(holder.itemView.getContext()).load(zhuangbiImage.getImage_url()).into(debounceViewHolder.mImageView);
        debounceViewHolder.mTextView.setText(zhuangbiImage.getDescription());
    }

    @Override
    public int getItemCount() {
        return mZhuangbiImageList == null ? 0 : mZhuangbiImageList.size();
    }

    public void setImages(List<ZhuangbiImage> imageList) {
        this.mZhuangbiImageList = imageList;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;
        @BindView(R.id.textView)
        TextView mTextView;

        DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
