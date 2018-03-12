package com.ph.shake.ui.fragment.forget;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.ph.lib.BaseFragment;
import com.ph.lib.injector.LayoutId;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;
import com.ph.shake.ui.activity.login.LoginActivity;

import butterknife.OnClick;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-12
 */

@LayoutId(R.layout.fragment_forget)
@Presenter(ForgetPresenter.class)
public class ForgetFragment extends BaseFragment<ForgetPresenter, IForgetView> {

    TextInputLayout userNameWrapper;
    TextInputLayout pwdWrapper;
    EditText etUserName;
    EditText etPwd;

    @Override
    protected void firstInit(View view) {
        userNameWrapper = view.findViewById(R.id.username_wrapper);
        pwdWrapper = view.findViewById(R.id.pwd_wrapper);
        etUserName = view.findViewById(R.id.username);
        etPwd = view.findViewById(R.id.pwd);
    }


    @OnClick(R.id.forget_btn)
    public void onClick(View view) {

    }

    @OnClick(R.id.forget_login_account)
    public void login(View view) {
        LoginActivity activity = (LoginActivity) getActivity();
        activity.transFragment(1);
    }

    @OnClick(R.id.forget_register_account)
    public void register(View view) {
        LoginActivity activity = (LoginActivity) getActivity();
        activity.transFragment(2);
    }

}
