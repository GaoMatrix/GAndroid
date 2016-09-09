package com.gao.android.fragment;

import android.app.Fragment;

import com.gao.android.application.BaseApplication;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by GaoMatrix on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
