package com.gao.toolbar.recyclerview;

import android.content.Intent;
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
    @BindView(R.id.linear)
    Button mLinear;
    @BindView(R.id.grid)
    Button mGrid;
    @BindView(R.id.staggered)
    Button mStaggered;

    public static RecyclerViewFragment getInstance() {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        return recyclerViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.linear, R.id.grid, R.id.staggered})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear:
                Intent linearIntent = new Intent(getActivity(), LinearLayoutActivity.class);
                startActivity(linearIntent);
                break;
            case R.id.grid:
                Intent gridIntent = new Intent(getActivity(), GridLayoutActivity.class);
                startActivity(gridIntent);
                break;
            case R.id.staggered:
                Intent staggeredIntent = new Intent(getActivity(), StaggeredLayoutActivity.class);
                startActivity(staggeredIntent);
                break;
        }
    }
}
