package com.mao.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private UMShareAPI mShareAPI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mWXLogin = (Button) findViewById(R.id.wxLogin);
        Button mWXLogout = (Button) findViewById(R.id.wxLogout);
        mWXLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxLogin();
            }
        });
        mWXLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxLogout();
            }
        });
        mShareAPI = UMShareAPI.get(this);
    }

    private void wxLogout() {
        Log.d(TAG, "wxLogout: ");
        SHARE_MEDIA platform = SHARE_MEDIA.WEIXIN;
        /**begin invoke umeng api**/
        mShareAPI.deleteOauth(MainActivity.this, platform, umdelAuthListener);
    }

    private void wxLogin() {
        Log.d(TAG, "wxLogin: ");
        SHARE_MEDIA platform = SHARE_MEDIA.WEIXIN;
        // doOauth接口返回的授权信息
        mShareAPI.doOauthVerify(MainActivity.this, platform, umAuthListener);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data != null) {
                Log.d(TAG, "onComplete: platform: " + platform);
                Log.d(TAG, "onComplete: action: " + action);
                Log.d(TAG, "onComplete: data: " + data.toString());
                Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                //调用getplatforminfo接口来去除返回值
                mShareAPI.getPlatformInfo(MainActivity.this, platform, umAuthMesListener);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT);
        }
    };

    private UMAuthListener umAuthMesListener = new UMAuthListener() {

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT);

            String m = share_media.toString();
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                //再次遍历拿出你需要的参数
                Log.d(TAG, "onComplete: entry: " + entry.toString());
                // unionid=oGfXpwD9TnQStXhPeR-0aY1rQ48Q
                // province=山东
                // gender=1
                // screen_name=Gao
                // openid=oCDCXwzpEnl_gbQKTQutfptDRdoI
                // language=zh_CN
                // profile_image_url=http://wx.qlogo.cn/mmopen/Q3auHgzwzM42gaUERJgrialk5Dg8JHqYsTlZ4Tof70Oibx0MxLNhISxzNmobyF19G6bzE4XxQoIORAmiaoZu3iacuw/0
                // country=中国
                // city=日照
            }


        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    /** delauth callback interface**/
    private UMAuthListener umdelAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "delete Authorize succeed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "delete Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "delete Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }
}
