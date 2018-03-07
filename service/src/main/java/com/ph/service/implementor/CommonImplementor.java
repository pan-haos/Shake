package com.ph.service.implementor;

import com.ph.lib.mvp.Callback;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 * <p>
 * 正常的类直接进行回调
 */

public class CommonImplementor<T> implements Implementor<T> {

    @Override
    public void handlerBody(Callback callback, T body) {
        callback.onSuccess(body);
    }

}
