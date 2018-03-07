package com.ph.service.api;

import com.ph.service.bean.Bean;
import com.ph.service.bean.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:09
 */
public interface Api {

    @FormUrlEncoded
    @POST("/app/user")
    Call<Bean<User>> getUserName();


    @FormUrlEncoded
    @POST("/app/user")
    Call<Bean<User>> getMap(@FieldMap Map<String, String> map);

}
