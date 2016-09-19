package com.gao.tabview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gao.tabview.material.MaterialDialogActivity;
import com.gao.tabview.material.MaterialDrawerActivity;
import com.gao.tabview.tablayout.TabLayoutBottomFragment;
import com.gao.tabview.tablayout.TabLayoutBottomViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yx on 16/4/3.
 */
public class WechatFragment extends BaseFragment implements ITabClickListener {

    @BindView(R.id.materialDialogs)
    Button mMaterialDialogs;
    @BindView(R.id.materialDrawer)
    Button mMaterialDrawer;
    @BindView(R.id.bottomViewPager)
    Button mBottomViewPager;
    @BindView(R.id.bottomFragment)
    Button mBottomFragment;

    @Override
    public void fetchData() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onMenuItemClick() {

    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }

    @OnClick({R.id.materialDialogs, R.id.materialDrawer, R.id.bottomViewPager, R.id.bottomFragment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.materialDialogs:
                Intent intent = new Intent(getActivity(), MaterialDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.materialDrawer:
                Intent intent1 = new Intent(getActivity(), MaterialDrawerActivity.class);
                startActivity(intent1);
                break;
            case R.id.bottomViewPager:
                Intent intent2 = new Intent(getActivity(), TabLayoutBottomViewPager.class);
                startActivity(intent2);
                break;
            case R.id.bottomFragment:
                Intent intent3 = new Intent(getActivity(), TabLayoutBottomFragment.class);
                startActivity(intent3);
                break;
        }
    }

}
