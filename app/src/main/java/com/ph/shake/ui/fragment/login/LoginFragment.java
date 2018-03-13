package com.ph.shake.ui.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ph.lib.BaseFragment;
import com.ph.lib.injector.LayoutId;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;
import com.ph.shake.ui.activity.home.HomeActivity;
import com.ph.shake.ui.activity.home.IHomeView;
import com.ph.shake.ui.activity.login.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import butterknife.OnClick;


/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-9
 */

@LayoutId(R.layout.fragment_login)
@Presenter(LoginPresenter.class)
public class LoginFragment extends BaseFragment<LoginPresenter, ILoginView> implements ILoginView {

    // 邮箱的正则表达式
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

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

    @OnClick(R.id.login_btn)
    public void onClick(View view) {
        hideKeyboard();
        String userName = etUserName.getText().toString();
        String pwd = etPwd.getText().toString();
        //email 校验不通过
        if (!isEmail(userName)) {
            userNameWrapper.setError("用户名非邮箱");
        } else {
            userNameWrapper.setErrorEnabled(false);
        }

        // pwd 校验不通过
        if (!isPwd(pwd)) {
            pwdWrapper.setError("密码长度不得小于6位");
        } else {
            pwdWrapper.setErrorEnabled(false);
        }

        //校验均通过
        if (isEmail(userName) && isPwd(pwd)) {
            mPresenter.login();
        }

    }

    @OnClick(R.id.login_change_pwd)
    public void changPwd(View view) {
        LoginActivity activity = (LoginActivity) getActivity();
        activity.transFragment(0);
    }

    @OnClick(R.id.login_register)
    public void register(View view) {
        LoginActivity activity = (LoginActivity) getActivity();
        activity.transFragment(2);
    }


    public boolean isEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPwd(String pwd) {
        return pwd.trim().length() >= 6;
    }

    /**
     * 隐藏虚拟键盘
     */
    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void goHome() {
        Intent intent = new Intent(this.getContext(), HomeActivity.class);
        startActivity(intent);
    }
}
