package com.ph.shake.ui.fragment.register;


import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ph.lib.BaseFragment;
import com.ph.lib.injector.LayoutId;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;
import com.ph.shake.ui.activity.login.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.OnClick;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-12
 */
@LayoutId(R.layout.fragment_register)
@Presenter(RegisterPresenter.class)
public class RegisterFragment extends BaseFragment<RegisterPresenter, IRegisterView> {
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

    @OnClick(R.id.register_btn)
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

        }
    }

    @OnClick(R.id.register_login_account)
    public void login(View view) {
        LoginActivity activity = (LoginActivity) getActivity();
        activity.transFragment(1);
    }

    @OnClick(R.id.register_forget_pwd)
    public void forgetPwd(View view) {
        LoginActivity activity = (LoginActivity) getActivity();
        activity.transFragment(0);
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

}
