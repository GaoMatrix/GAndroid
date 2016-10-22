package com.gao.android.rxjavaretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gao.android.rxjavaretrofit.R;
import com.gao.android.rxjavaretrofit.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GaoMatrix on 2016/10/22.
 */
public class ItemListAdapter extends RecyclerView.Adapter {
    private List<Item> mItemList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        Item item = mItemList.get(position);
        Glide.with(holder.itemView.getContext()).load(item.getImageUrl()).into(debounceViewHolder.mImageView);
        debounceViewHolder.mTextView.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    public void setItemList(List<Item> itemList) {
        this.mItemList = itemList;
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
