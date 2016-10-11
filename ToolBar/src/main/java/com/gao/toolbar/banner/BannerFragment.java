package com.gao.toolbar.banner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gao.toolbar.App;
import com.gao.toolbar.R;
import com.gao.toolbar.banner.engine.Engine;
import com.gao.toolbar.banner.model.BannerModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.transformer.TransitionEffect;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GaoMatrix on 2016/9/22.
 */
public class BannerFragment extends Fragment implements BGABanner.OnItemClickListener, BGABanner.Adapter {

    @BindView(R.id.tv_main_get_item_count)
    TextView mTvMainGetItemCount;
    @BindView(R.id.tv_main_get_current_item)
    TextView mTvMainGetCurrentItem;
    @BindView(R.id.tv_main_load_one_item)
    TextView mTvMainLoadOneItem;
    @BindView(R.id.tv_main_load_two_item)
    TextView mTvMainLoadTwoItem;
    @BindView(R.id.tv_main_load_three_item)
    TextView mTvMainLoadThreeItem;
    @BindView(R.id.tv_main_load_five_item)
    TextView mTvMainLoadFiveItem;
    @BindView(R.id.tv_main_select_page_one)
    TextView mTvMainSelectPageOne;
    @BindView(R.id.tv_main_select_page_two)
    TextView mTvMainSelectPageTwo;
    @BindView(R.id.tv_main_select_page_three)
    TextView mTvMainSelectPageThree;
    @BindView(R.id.tv_main_select_page_four)
    TextView mTvMainSelectPageFour;
    @BindView(R.id.tv_main_select_page_five)
    TextView mTvMainSelectPageFive;
    @BindView(R.id.tv_main_cube)
    TextView mTvMainCube;
    @BindView(R.id.tv_main_depth)
    TextView mTvMainDepth;
    @BindView(R.id.tv_main_flip)
    TextView mTvMainFlip;
    @BindView(R.id.tv_main_rotate)
    TextView mTvMainRotate;
    @BindView(R.id.tv_main_alpha)
    TextView mTvMainAlpha;
    @BindView(R.id.banner_main_default)
    BGABanner mBannerMainDefault;
    @BindView(R.id.banner_main_cube)
    BGABanner mBannerMainCube;
    @BindView(R.id.banner_main_accordion)
    BGABanner mBannerMainAccordion;
    @BindView(R.id.banner_main_flip)
    BGABanner mBannerMainFlip;
    @BindView(R.id.banner_main_rotate)
    BGABanner mBannerMainRotate;
    @BindView(R.id.banner_main_alpha)
    BGABanner mBannerMainAlpha;
    @BindView(R.id.banner_main_zoomFade)
    BGABanner mBannerMainZoomFade;
    @BindView(R.id.banner_main_fade)
    BGABanner mBannerMainFade;
    @BindView(R.id.banner_main_zoomCenter)
    BGABanner mBannerMainZoomCenter;
    @BindView(R.id.banner_main_zoom)
    BGABanner mBannerMainZoom;
    @BindView(R.id.banner_main_stack)
    BGABanner mBannerMainStack;
    @BindView(R.id.banner_main_zoomStack)
    BGABanner mBannerMainZoomStack;
    @BindView(R.id.banner_main_depth)
    BGABanner mBannerMainDepth;

    private BGABanner mDefaultBanner;
    private BGABanner mCubeBanner;
    private BGABanner mAccordionBanner;
    private BGABanner mFlipBanner;
    private BGABanner mRotateBanner;
    private BGABanner mAlphaBanner;
    private BGABanner mZoomFadeBanner;
    private BGABanner mFadeBanner;
    private BGABanner mZoomCenterBanner;
    private BGABanner mZoomBanner;
    private BGABanner mStackBanner;
    private BGABanner mZoomStackBanner;
    private BGABanner mDepthBanner;

    private Engine mEngine;

    public static BannerFragment getInstance() {
        BannerFragment bannerFragment = new BannerFragment();
        return bannerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEngine = App.getInstance().getEngine();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banner, null);
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
        mDefaultBanner = (BGABanner) view.findViewById(R.id.banner_main_default);
        mCubeBanner = (BGABanner) view.findViewById(R.id.banner_main_cube);
        mAccordionBanner = (BGABanner) view.findViewById(R.id.banner_main_accordion);
        mFlipBanner = (BGABanner) view.findViewById(R.id.banner_main_flip);
        mRotateBanner = (BGABanner) view.findViewById(R.id.banner_main_rotate);
        mAlphaBanner = (BGABanner) view.findViewById(R.id.banner_main_alpha);
        mZoomFadeBanner = (BGABanner) view.findViewById(R.id.banner_main_zoomFade);
        mFadeBanner = (BGABanner) view.findViewById(R.id.banner_main_fade);
        mZoomCenterBanner = (BGABanner) view.findViewById(R.id.banner_main_zoomCenter);
        mZoomBanner = (BGABanner) view.findViewById(R.id.banner_main_zoom);
        mStackBanner = (BGABanner) view.findViewById(R.id.banner_main_stack);
        mZoomStackBanner = (BGABanner) view.findViewById(R.id.banner_main_zoomStack);
        mDepthBanner = (BGABanner) view.findViewById(R.id.banner_main_depth);
    }

    private void initListener() {
        mDefaultBanner.setOnItemClickListener(this);
        mCubeBanner.setOnItemClickListener(this);
    }

    private void initData() {
        initData(mDefaultBanner, 1);
        initData(mCubeBanner, 2);
        initData(mAccordionBanner, 3);
        initData(mFlipBanner, 4);
        initData(mRotateBanner, 5);
        initData(mAlphaBanner, 6);
        initData(mZoomFadeBanner, 3);
        initData(mFadeBanner, 4);
        initData(mZoomCenterBanner, 5);
        initData(mZoomBanner, 6);
        initData(mStackBanner, 3);
        initData(mZoomStackBanner, 4);
        initData(mDepthBanner, 5);
    }

    private void initData(final BGABanner banner, int count) {
        mEngine.fetchItemsWithItemCount(count).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();

                banner.setAdapter(BannerFragment.this);
                banner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
        Toast.makeText(App.getInstance(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Glide.with(getActivity().getApplicationContext())
                .load(model)
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .into((ImageView) view);
    }

    // TODO: 2016/9/22  点击事件出现空指针
    @OnClick({R.id.tv_main_get_item_count, R.id.tv_main_get_current_item, R.id.tv_main_load_one_item, R.id.tv_main_load_two_item, R.id.tv_main_load_three_item, R.id.tv_main_load_five_item, R.id.tv_main_select_page_one, R.id.tv_main_select_page_two, R.id.tv_main_select_page_three, R.id.tv_main_select_page_four, R.id.tv_main_select_page_five, R.id.tv_main_cube, R.id.tv_main_depth, R.id.tv_main_flip, R.id.tv_main_rotate, R.id.tv_main_alpha})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_main_select_page_one:
                mDefaultBanner.setCurrentItem(0);
                break;
            case R.id.tv_main_select_page_two:
                mDefaultBanner.setCurrentItem(1);
                break;
            case R.id.tv_main_select_page_three:
                mDefaultBanner.setCurrentItem(2);
                break;
            case R.id.tv_main_select_page_four:
                mDefaultBanner.setCurrentItem(3);
                break;
            case R.id.tv_main_select_page_five:
                mDefaultBanner.setCurrentItem(4);
                break;
            case R.id.tv_main_get_item_count:
                Toast.makeText(App.getInstance(), "广告条总页数为 " + mDefaultBanner.getItemCount(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_main_get_current_item:
                Toast.makeText(App.getInstance(), "广告当前索引位置为 " + mDefaultBanner.getCurrentItem(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_main_load_one_item:
                initData(mDefaultBanner, 1);
                break;
            case R.id.tv_main_load_two_item:
                initData(mDefaultBanner, 2);
                break;
            case R.id.tv_main_load_three_item:
                initData(mDefaultBanner, 3);
                break;
            case R.id.tv_main_load_five_item:
                initData(mDefaultBanner, 5);
                break;
            case R.id.tv_main_cube:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Cube);
                break;
            case R.id.tv_main_depth:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Depth);
                break;
            case R.id.tv_main_flip:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Flip);
                break;
            case R.id.tv_main_rotate:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Rotate);
                break;
            case R.id.tv_main_alpha:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Alpha);
                break;
            default:
                break;
        }
    }
}
