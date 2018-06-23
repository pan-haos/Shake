package com.ph.lib.mvp;

/**
 * 项目： Shake
 * Programmer: 潘浩
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

    boolean isAttach();

}
