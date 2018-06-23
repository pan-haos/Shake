package com.ph.service.implementor;

import com.ph.lib.mvp.Callback;
import com.ph.service.bean.Result;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 * <p>
 * 被Bean包裹的Implementor处理类
 */

public class BeanImplementor<T> implements Implementor<Result<T>> {

    @Override
    public void handlerBody(Callback callback, Result<T> body) {
        int code = body.getCode();
        //请求成功
        if (code == 0) {
            callback.onSuccess(body.getContent());
        } else {
            callback.onFail(body.getMessage());
        }
    }

}
