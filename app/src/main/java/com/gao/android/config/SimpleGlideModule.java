package com.gao.android.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;
import com.orhanobut.logger.Logger;

/**
 * Created by GaoMatrix on 2016/9/3.
 * Glide的全局配置
 * Glide modules are an abstract way of globally changing the way Glide behaves
 * https://futurestud.io/blog/glide-customize-glide-with-modules
 */
public class SimpleGlideModule implements GlideModule{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//        builder.setMemoryCache(MemoryCache memoryCache)
//                .setBitmapPool(BitmapPool bitmapPool)
//                .setDiskCache(DiskCache.Factory diskCacheFactory)
//                .setDiskCacheService(ExecutorService service)
//                .setResizeService(ExecutorService service)
//                .setDecodeFormat(DecodeFormat decodeFormat)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        Logger.d("Glide applyOptions");
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
