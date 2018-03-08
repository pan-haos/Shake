package com.ph.shake.ui.login;

import com.ph.lib.BaseActivity;
import com.ph.lib.injector.ContentView;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 */

@ContentView(R.layout.activity_main)
@Presenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<LoginPresenter, ILoginView> {

    @Override
    protected void init() {
        mPresenter.login();
    }
}
