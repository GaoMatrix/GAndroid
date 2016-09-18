package com.gao.tabview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yx on 16/4/3.
 */
public class WechatFragment extends BaseFragment implements ITabClickListener {


    @BindView(R.id.showColorChooserCustomColors)
    Button mShowColorChooserCustomColors;
    @BindView(R.id.showThemed)
    Button mShowThemed;

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


    @OnClick({R.id.showColorChooserCustomColors, R.id.showThemed})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showColorChooserCustomColors:
                break;
            case R.id.showThemed:
                new MaterialDialog.Builder(getContext())
                        .title(R.string.useGoogleLocationServices)
                        .content(R.string.useGoogleLocationServicesPrompt)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .positiveColorRes(R.color.material_red_400)
                        .negativeColorRes(R.color.material_red_400)
                        .titleGravity(GravityEnum.CENTER)
                        .titleColorRes(R.color.material_red_400)
                        .contentColorRes(android.R.color.white)
                        .backgroundColorRes(R.color.material_blue_grey_800)
                        .dividerColorRes(R.color.accent)
                        .btnSelector(R.drawable.md_btn_selector_custom, DialogAction.POSITIVE)
                        .positiveColor(Color.WHITE)
                        .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
                        .theme(Theme.DARK)
                        .show();
                break;
        }
    }
}
