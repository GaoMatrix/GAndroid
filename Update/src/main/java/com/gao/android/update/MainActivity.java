package com.gao.android.update;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.dialogUpdate)
    Button mDialogUpdate;
    @BindView(R.id.notificationUpdate)
    Button mNotificationUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dialogUpdate, R.id.notificationUpdate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialogUpdate: {
                UpdateHelper updateHelper = new UpdateHelper.Builder(this)
                        .checkUrl("http://192.168.12.7/launcher_apks.json")
                        .isAutoInstall(true) //设置为false需在下载完手动点击安装;默认值为true，下载后自动安装。
                        .build();
                updateHelper.check();
            }
                break;
            case R.id.notificationUpdate:
                break;
        }
    }
}
