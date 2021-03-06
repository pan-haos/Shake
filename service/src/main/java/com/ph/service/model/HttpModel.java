package com.ph.service.model;


import android.util.Log;

import com.ph.lib.mvp.Callback;
import com.ph.lib.mvp.IModel;
import com.ph.service.api.Api;
import com.ph.service.api.ApiService;
import com.ph.service.implementor.Implementor;

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

    protected Implementor<T> implementor;

    public void setImplementor(Implementor<T> implementor) {
        this.implementor = implementor;
    }

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

                Log.e("ph", "onResponse: Http请求收到响应");

                if (response.isSuccessful()) {
                    T body = response.body();


                    int code = response.code();

                    boolean successful = response.isSuccessful();


                    Log.e("ph", "onResponse: body" + body + " code" + code + successful);
                    /**
                     *  这里handler的原因是因为可能需要根据服务器返回内容决定进入OnSuccess还是OnFail回调中
                     */
                    implementor.handlerBody(callback, body);
                } else {
                    int code = response.code();
                    if (code == 404) {
                        callback.onFail("找不到请求路径404");
                    }
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof IOException) {
                    callback.onFail("通信异常");
                } else if (t instanceof TimeoutException) {
                    callback.onFail("连接超时");
                } else {
                    callback.onFail("未知异常");
                }
            }
        });

    }


    /**
     * 获取Api
     *
     * @param api Retrofit 提供的api
     * @return call对象
     */
    public abstract Call<T> getCall(Api api);
}
