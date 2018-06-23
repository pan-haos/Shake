package com.ph.shake.ui.fragment.login;


import com.ph.lib.mvp.Callback;
import com.ph.lib.mvp.IModel;
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
    public void login(String userName, String pwd) {
        IModel model = new LoginModel(userName, pwd);
        model.load(new Callback<String,String>() {
            @Override
            public void onSuccess(String result) {
                //当网络请求结束后仍然attach界面
                if(isAttach()){
                    mView.get().goHome();
                }
            }

            @Override
            public void onFail(String result) {
                if(isAttach()){
                    mView.get().showLoginFail(result);
                }
            }
        });


    }

    @Override
    public void register() {
        if (isAttach()) {

        }
    }


}
