package com.ph.service.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:09
 */
public class ApiService {

    private static final ApiService API_SERVICE = new ApiService();

    private static final String IP = "http://192.168.1.101:80";

    private Api Api;

    private ApiService() {

        /**
         * 添加OkHttp log日志拦截器
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        /**
         * 构建OkHttp Client
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        /**
         * 构建Retrofit 客户端
         */
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IP)
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
