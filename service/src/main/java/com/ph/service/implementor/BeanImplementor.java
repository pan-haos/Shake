package com.ph.service.implementor;

import com.ph.lib.mvp.Callback;
import com.ph.service.bean.Bean;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 * <p>
 * 被Bean包裹的Implementor处理类
 */

public class BeanImplementor<T> implements Implementor<Bean<T>> {

    @Override
    public void handlerBody(Callback callback, Bean<T> body) {
        int code = body.getCode();
        if (code == 0) {
            callback.onFail(body.getMessage());
        } else {
            callback.onSuccess(body.getContent());
        }
    }

}
