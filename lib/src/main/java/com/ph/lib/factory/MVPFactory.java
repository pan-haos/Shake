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
     * @param <V>
     * @return
     */
    public static <V> IPresenter createPresenter(Class<? extends Presenter<V>> source) {
        try {
            Presenter<V> presenter = source.newInstance();
            return presenter;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("can't create a presenter by source");
    }


}
