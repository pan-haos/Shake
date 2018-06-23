package com.ph.lib.mvp;

/**
 * 项目： Shake
 * Programmer: 潘浩
 *
 * @param <T> 接受数据成功返回类型
 * @param <R> 接受数据失败返回类型
 */

public interface Callback<T, R> {

    /**
     * 成功回调
     *
     * @param result 回调结果
     */
    void onSuccess(T result);

    /**
     * 失败hudiiao
     *
     * @param result 回调结果
     */
    void onFail(R result);

}
