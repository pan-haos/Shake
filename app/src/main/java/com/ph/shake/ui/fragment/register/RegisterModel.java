package com.ph.shake.ui.fragment.register;

import com.ph.service.api.Api;
import com.ph.service.bean.Result;
import com.ph.service.model.HttpCommonModel;

import retrofit2.Call;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-6-13  下午7:07
 */
public class RegisterModel extends HttpCommonModel {
    private String userName;
    private String pwd;

    public RegisterModel(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    @Override
    public Call<Result> getCall(Api api) {
        return api.register(userName, pwd);
    }

}
