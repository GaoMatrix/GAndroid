package com.gao.tabview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gao.tabview.Activity.MaterialDialogActivity;
import com.gao.tabview.Activity.MaterialDrawerActivity;

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

    @OnClick({R.id.materialDialogs, R.id.materialDrawer})
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
        }
    }
}
