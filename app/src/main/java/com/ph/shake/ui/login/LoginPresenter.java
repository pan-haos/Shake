package com.ph.shake.ui.login;

import android.util.Log;

import com.ph.lib.mvp.Presenter;

import static android.content.ContentValues.TAG;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 */

public class LoginPresenter extends Presenter<ILoginView> implements LoginContract {
    @Override
    public void login() {
        Log.e(TAG, "login: 登錄");
    }

    @Override
    public void register() {

    }
}
