package com.ph.service.implementor;

import com.ph.lib.mvp.Callback;
import com.ph.service.bean.Result;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 * <p>
 * 定义的通信协议数据必然是Result或者被包含在Result的内部之中。
 * <p>
 * 这里的CommonImplementor代表的是Result中的content为null。
 * <p>
 * code值 代表：
 * <p>
 * 0：请求成功
 * -1：请求失败
 */

public class CommonImplementor implements Implementor<Result> {

    @Override
    public void handlerBody(Callback callback, Result body) {
        int code = body.getCode();
        //请求成功
        if (code == 0) {
            callback.onSuccess(body.getMessage());
        } else {
            callback.onFail(body.getMessage());
        }
    }
}
