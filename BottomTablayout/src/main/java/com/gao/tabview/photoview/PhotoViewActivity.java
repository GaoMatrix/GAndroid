package com.gao.tabview.photoview;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.gao.tabview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by GaoMatrix on 2016/9/19.
 */
public class PhotoViewActivity extends Activity {
    @BindView(R.id.iv_photo)
    PhotoView mPhotoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        ButterKnife.bind(this);

        // Any implementation of ImageView can be used!
        mPhotoView = (PhotoView) findViewById(R.id.iv_photo);

        // Set the Drawable displayed
        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        mPhotoView.setImageDrawable(bitmap);
    }

    @OnClick(R.id.iv_photo)
    public void onClick() {
    }
}
