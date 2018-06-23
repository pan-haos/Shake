package com.ph.lib.injector;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 项目： Shake
 * Programmer: 潘浩
 * 注入內容视图
 */

public class ViewInjector {


    /**
     * 注入activity的setContentView
     *
     * @param activity
     */
    public static void inject(Activity activity) {
        LayoutId annotation = activity.getClass().getAnnotation(LayoutId.class);
        if (annotation == null) {
            throw new NullPointerException("activity must have LayoutId annotation");
        }
        int value = annotation.value();
        if (value == -1 || value == 0) {
            throw new IllegalStateException("activity res value can't be 0 or -1");
        }
        activity.setContentView(value);
    }


    /**
     * 注入fragment inflate的View中
     *
     * @param fragment
     * @param container
     * @param inflater  @return
     */
    public static View inject(Fragment fragment, ViewGroup container, LayoutInflater inflater) {
        LayoutId annotation = fragment.getClass().getAnnotation(LayoutId.class);
        if (annotation == null) {
            throw new IllegalStateException("fragment must have LayoutId annotation");
        }
        int value = annotation.value();
        if (value == -1 || value == 0) {
            throw new IllegalStateException("fragment res value can't be 0 or -1");
        }
        return inflater.inflate(value, null);
    }


}
