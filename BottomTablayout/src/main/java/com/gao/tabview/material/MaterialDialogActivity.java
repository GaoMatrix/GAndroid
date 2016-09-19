package com.gao.tabview.material;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.gao.tabview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/9/19.
 */
public class MaterialDialogActivity extends Activity {

    @BindView(R.id.showColorChooserCustomColors)
    Button mShowColorChooserCustomColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.showColorChooserCustomColors)
    public void onClick() {
        new MaterialDialog.Builder(this)
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
    }
}
