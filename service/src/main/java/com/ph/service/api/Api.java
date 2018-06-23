package com.ph.service.api;

import com.ph.service.bean.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:09
 */
public interface Api {


    @FormUrlEncoded
    @POST("app/login")
    Call<Result> login(@Field("userName") String userName, @Field("password") String pwd);

    @FormUrlEncoded
    @POST("app/register")
    Call<Result> register(@Field("userName") String userName, @Field("password") String pwd);


}
