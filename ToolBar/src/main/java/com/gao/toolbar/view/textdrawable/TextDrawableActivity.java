package com.gao.toolbar.view.textdrawable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gao.toolbar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/10/17.
 */
public class TextDrawableActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private DataSource mDataSource;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textdrawable);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    private void initData() {
        mDataSource = new DataSource(this);
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.toolbar, R.id.recyclerView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.recyclerView:
                break;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(TextDrawableActivity.this).inflate(R.layout.item_activity_textdrawable, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            DataItem dataItem = mDataSource.getItem(position);
            holder.imageView.setImageDrawable(dataItem.getDrawable());
            holder.textView.setText(dataItem.getLabel());
        }

        @Override
        public int getItemCount() {
            return mDataSource.getCount();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView;

            public MyViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
                textView = (TextView) view.findViewById(R.id.textView);
            }
        }
    }
}
