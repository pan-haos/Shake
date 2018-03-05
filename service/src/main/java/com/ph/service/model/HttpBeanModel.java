package com.ph.service.model;

import com.ph.lib.mvp.Callback;
import com.ph.service.bean.Bean;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:51
 * 被Bean包含的Model
 */
public abstract class HttpBeanModel<T> extends HttpModel<Bean<T>> {

    @Override
    protected void handlerBody(Callback callback, Bean<T> body) {
        int code = body.getCode();
        if (code == 0) {
            String message = body.getMessage();
            callback.onFail(message);
        } else {
            T content = body.getContent();
            callback.onSuccess(content);
        }
    }

}
