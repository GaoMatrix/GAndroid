package com.gao.android.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.gao.android.R;
import com.gao.android.adapter.MyAdapter;
import com.gao.android.model.Bean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    private List<Bean> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        mDatas = new ArrayList<Bean>();
        Bean bean1 = new Bean("Android新技能Get 1",
                "Android打造万能的ListView和GridView适配器", "2016-09-08", "10086");
        mDatas.add(bean1);
        Bean bean2 = new Bean("Android新技能Get 2",
                "Android打造万能的ListView和GridView适配器", "2016-09-08", "10086");
        mDatas.add(bean2);
        Bean bean3 = new Bean("Android新技能Get 3",
                "Android打造万能的ListView和GridView适配器", "2016-09-08", "10086");
        mDatas.add(bean3);
        Bean bean4 = new Bean("Android新技能Get 4",
                "Android打造万能的ListView和GridView适配器", "2016-09-08", "10086");
        mDatas.add(bean4);
        Bean bean5 = new Bean("Android新技能Get 5",
                "Android打造万能的ListView和GridView适配器", "2016-09-08", "10086");
        mDatas.add(bean5);

        mAdapter = new MyAdapter(this, mDatas);
        listView.setAdapter(mAdapter);
    }
}
