package com.ph.service.image;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 */

public class ImageLoader {

    public static void loadImage(Activity activity, ImageView imageView,String url){
        Glide.with(activity)
                .load(url)
                .into(imageView)
                .clearOnDetach();
    }
}
