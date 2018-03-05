package com.ph.lib;

import android.util.LruCache;
import android.view.View;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 * 用来存储Fragment
 */

public class Cache {

    /**
     * 创建一个1/8应用程序大小的缓存用来存储Fragment
     */
    private static LruCache<String, View> mCache = new LruCache<>((int) (Runtime.getRuntime().totalMemory() / 8));

    /**
     * 添加进缓存
     *
     * @param name
     * @param view
     */
    public static void put(String name, View view) {
        mCache.put(name, view);
    }

    /**
     * 获取缓存是否存在
     *
     * @param name
     * @return
     */
    public static View get(String name) {
        return mCache.get(name);
    }


    public static void remove(String name) {
        if (mCache.get(name) != null)
            mCache.remove(name);
    }

}
