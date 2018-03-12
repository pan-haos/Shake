package com.ph.shake.ui.fragment.register;

import android.util.Log;

import com.ph.lib.mvp.Presenter;

import static android.content.ContentValues.TAG;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-12
 */

public class RegisterPresenter extends Presenter<IRegisterView> implements RegisterContract {

    @Override
    public void register() {
        if (isAttach()) {
            Log.e(TAG, "register: 注册");
        }
    }
}
