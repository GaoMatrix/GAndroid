package com.gao.toolbar.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gao.toolbar.R;
import com.gao.toolbar.view.textdrawable.TextDrawableActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/10/17.
 */
public class ViewFragment extends Fragment {

    @BindView(R.id.textDrawable)
    Button mTextDrawable;

    public static ViewFragment getInstance() {
        ViewFragment viewFragment = new ViewFragment();
        return viewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.textDrawable)
    public void onClick() {
        Intent intent = new Intent(getActivity(), TextDrawableActivity.class);
        startActivity(intent);
    }
}
