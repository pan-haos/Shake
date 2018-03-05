package com.ph.lib.mvp;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 *
 * @param <T> 接受数据成功返回类型
 * @param <R>
 */

public interface Callback<T, R> {

    void onSuccess(T result);

    void onFail(R result);

}
