package com.ph.shake.ui.fragment.register;

import com.ph.lib.mvp.Callback;
import com.ph.lib.mvp.IModel;
import com.ph.lib.mvp.Presenter;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-12
 */

public class RegisterPresenter extends Presenter<IRegisterView> implements RegisterContract {

    @Override
    public void register(String userName, String pwd) {
        IModel model = new RegisterModel(userName, pwd);
        model.load(new Callback<String, String>() {
            @Override
            public void onSuccess(String result) {
                if (isAttach()) {
                    mView.get().goHome();
                }
            }

            @Override
            public void onFail(String result) {
                if(isAttach()){
                    mView.get().showFailRegister(result);
                }
            }
        });
    }
}
