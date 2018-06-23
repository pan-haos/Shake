package com.ph.lib.mvp;

/**
 * 项目： Shake
 * Programmer: 潘浩
 */

public interface IModel {

    /**
     * model层拉取结果
     *
     * @param callback
     */
    void load(Callback callback);
}
