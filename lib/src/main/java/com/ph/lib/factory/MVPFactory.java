package com.ph.lib.factory;

import com.ph.lib.mvp.IPresenter;
import com.ph.lib.mvp.Presenter;


/**
 * 项目： Shake
 * Programmer: 潘浩
 * 创建工厂类
 */

public class MVPFactory {

    /**
     * 创建Presenter对象
     *
     * @param source
     * @return
     */
    public static <V> IPresenter<V> createPresenter(Class<? extends Presenter> source) {
        IPresenter<V> presenter;
        try {
            presenter = source.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException("can't init presenter");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("can't init presenter");
        }
        return presenter;
    }


}
