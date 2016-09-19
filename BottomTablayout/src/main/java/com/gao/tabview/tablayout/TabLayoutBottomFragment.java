package com.gao.tabview.tablayout;

import android.app.Activity;
import android.os.Bundle;

import com.gao.tabview.R;

/**
 * Created by GaoMatrix on 2016/9/19.
 * 底部Switch Fragment 只能点击tab切换
 */
public class TabLayoutBottomFragment extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_bottom_fragment);
    }
}
