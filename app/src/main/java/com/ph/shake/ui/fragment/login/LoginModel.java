package com.ph.shake.ui.fragment.login;

import android.util.Log;

import com.ph.service.api.Api;
import com.ph.service.bean.Result;
import com.ph.service.model.HttpCommonModel;

import retrofit2.Call;

import static android.content.ContentValues.TAG;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-5-22  下午4:26
 * 登录model
 */
public class LoginModel extends HttpCommonModel {

    private String userName;
    private String pwd;

    public LoginModel(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    @Override
    public Call<Result> getCall(Api api) {
        Log.e(TAG, "getCall: 发出网络qingiqu");
        return api.login(userName, pwd);
    }
}
