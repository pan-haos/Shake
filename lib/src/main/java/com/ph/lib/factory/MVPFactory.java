package com.ph.lib.factory;

import com.ph.lib.mvp.IPresenter;
import com.ph.lib.mvp.Presenter;


/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 * 创建工厂类
 */

public class MVPFactory {

    /**
     * 创建Presenter对象
     *
     * @param source
     * @return
     */
    public static IPresenter createPresenter(Class<? extends Presenter> source) {
        try {
            Presenter presenter = source.newInstance();
            return presenter;
        } catch (InstantiationException e) {
            throw new IllegalStateException("can't init presenter");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("can't init presenter");
        }
    }


}
