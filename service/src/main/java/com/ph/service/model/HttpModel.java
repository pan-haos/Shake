package com.ph.service.model;


import com.ph.lib.mvp.Callback;
import com.ph.lib.mvp.IModel;
import com.ph.service.api.Api;
import com.ph.service.api.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:09
 */
public abstract class HttpModel<T> implements IModel {

    @Override
    public void load(final Callback callback) {
        Api api = ApiService.getInstance().getApi();
        /**
         * 获取Call
         */
        Call<T> call = getCall(api);

        /**
         * 发送请求
         */
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                T body = response.body();
                handlerBody(callback, body);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof IOException) {
                    callback.onFail("IO Exception");
                } else if (t instanceof TimeoutException) {
                    callback.onFail("连接超时～");
                } else {
                    callback.onFail("未知异常");
                }
            }
        });

    }

    /**
     * 处理Body请求
     *
     * @param callback
     * @param body
     */
    protected abstract void handlerBody(Callback callback, T body);

    /**
     * 获取Api
     * @param api
     * @return
     */
    protected abstract Call<T> getCall(Api api);
}
