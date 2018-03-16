package com.ph.service.implementor;

import com.ph.lib.mvp.Callback;


/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 * <p>
 * 桥梁类---------起桥接作用,桥接 model 进行callback回调
 */

public interface Implementor<T> {

    /**
     * 处理body回调的情况
     *
     * @param callback
     * @param body
     */
    void handlerBody(Callback callback, T body);

}
