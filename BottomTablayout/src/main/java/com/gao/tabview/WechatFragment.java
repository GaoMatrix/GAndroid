package com.gao.tabview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gao.tabview.material.MaterialDialogActivity;
import com.gao.tabview.material.MaterialDrawerActivity;
import com.gao.tabview.photoview.PhotoViewActivity;
import com.gao.tabview.photoview.ViewPagerActivity;
import com.gao.tabview.swipebacklayout.SwipeBackLayoutActivity;
import com.gao.tabview.tablayout.SegmentTabActivity;
import com.gao.tabview.tablayout.SlidingTabActivity;
import com.gao.tabview.tablayout.TabLayoutBottomFragmentActivity;
import com.gao.tabview.tablayout.TabLayoutBottomViewPagerActivity;

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
    @BindView(R.id.slidingTab)
    Button mSlidingTab;
    @BindView(R.id.segmentTab)
    Button mSegmentTab;
    @BindView(R.id.photoView)
    Button mPhotoView;
    @BindView(R.id.photoViewViewPager)
    Button mPhotoViewViewPager;
    @BindView(R.id.swipeBackLayout)
    Button mSwipeBackLayout;

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

    @OnClick({R.id.materialDialogs, R.id.materialDrawer, R.id.bottomViewPager,
            R.id.bottomFragment, R.id.slidingTab, R.id.segmentTab, R.id.photoView,
            R.id.photoViewViewPager, R.id.swipeBackLayout})
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
                Intent intent2 = new Intent(getActivity(), TabLayoutBottomViewPagerActivity.class);
                startActivity(intent2);
                break;
            case R.id.bottomFragment:
                Intent intent3 = new Intent(getActivity(), TabLayoutBottomFragmentActivity.class);
                startActivity(intent3);
                break;
            case R.id.slidingTab:
                Intent intent4 = new Intent(getActivity(), SlidingTabActivity.class);
                startActivity(intent4);
                break;
            case R.id.segmentTab:
                Intent intent5 = new Intent(getActivity(), SegmentTabActivity.class);
                startActivity(intent5);
                break;
            case R.id.photoView:
                Intent intent6 = new Intent(getActivity(), PhotoViewActivity.class);
                startActivity(intent6);
                break;
            case R.id.photoViewViewPager:
                Intent intent7 = new Intent(getActivity(), ViewPagerActivity.class);
                startActivity(intent7);
                break;
            case R.id.swipeBackLayout:
                Intent intent8 = new Intent(getActivity(), SwipeBackLayoutActivity.class);
                startActivity(intent8);
                break;
        }
    }

}
