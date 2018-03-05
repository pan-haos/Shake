package com.ph.lib.mvp;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 */

public interface IModel {

    /**
     * model层拉取结果
     *
     * @param callback
     */
    void load(Callback callback);
}
