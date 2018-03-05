package com.ph.service.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:09
 */
public class ApiService {

    private static final ApiService API_SERVICE = new ApiService();

    private Api Api;

    private ApiService() {

        /**
         * 构建OkHttp Client
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        /**
         * 构建Retrofit 客户端
         */
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .client(client)
                .build();

        /**
         * 构建APi供外部调用
         */
        Api = retrofit.create(Api.class);
    }

    public static ApiService getInstance() {
        return API_SERVICE;
    }

    public Api getApi() {
        return Api;
    }


}
