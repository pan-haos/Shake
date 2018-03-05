package com.ph.lib.mvp;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 */

public interface IPresenter<V> {

    /**
     * 与View绑定
     *
     * @param view
     */
    void attach(V view);

    /**
     * 与view解绑定
     */
    void detach();

    /**
     * 获取到View
     *
     * @return
     */
    V get();

}
