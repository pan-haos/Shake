package com.ph.service.model;

import com.ph.lib.mvp.Callback;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-6  上午12:04
 */
public abstract class BeanModel<T> extends HttpModel<T> {

    @Override
    protected void handlerBody(Callback callback, T body) {
        callback.onSuccess(body);
    }
}
