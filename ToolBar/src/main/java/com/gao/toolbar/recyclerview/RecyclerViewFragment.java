package com.gao.toolbar.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gao.toolbar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/10/11.
 */
public class RecyclerViewFragment extends Fragment {
    @BindView(R.id.linear1)
    Button mLinear1;
    @BindView(R.id.liner2)
    Button mLiner2;
    @BindView(R.id.grid)
    Button mGrid;
    @BindView(R.id.staggered)
    Button mStaggered;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, null);
        initView(view);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View view) {

    }

    private void initListener() {

    }

    private void initData() {
    }

    @OnClick({R.id.linear1, R.id.liner2, R.id.grid, R.id.staggered})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear1:
                break;
            case R.id.liner2:
                break;
            case R.id.grid:
                break;
            case R.id.staggered:
                break;
        }
    }
}
