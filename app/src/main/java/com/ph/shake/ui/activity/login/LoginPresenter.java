package com.ph.shake.ui.activity.login;


import android.util.Log;

import com.ph.lib.mvp.Presenter;


/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 */

public class LoginPresenter extends Presenter<ILoginView> implements LoginContract {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    @Override
    public void login() {
        if (isAttach()) {
            Log.e(TAG, "login:登录中 ");
        }
    }

    @Override
    public void register() {
        if (isAttach()) {

        }
    }


}
