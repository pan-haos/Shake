package com.ph.shake.ui.fragment.login;

import android.view.View;

import com.ph.lib.BaseFragment;
import com.ph.lib.injector.ContentView;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-9
 */

@ContentView(R.layout.fragment_login)
@Presenter(LoginPresenter.class)
public class LoginFragment extends BaseFragment<LoginPresenter, ILoginView> {

    @Override
    protected void firstInit(View view) {

    }


}
