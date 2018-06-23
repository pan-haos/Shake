package com.ph.shake.ui.fragment.register;


import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import butterknife.OnClick;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-12
 */
@LayoutId(R.layout.fragment_register)
@Presenter(RegisterPresenter.class)
public class RegisterFragment extends BaseFragment<RegisterPresenter, IRegisterView> implements IRegisterView {

    TextInputLayout userNameWrapper;

    TextInputLayout pwdWrapper;

    EditText etUserName;

    EditText etPwd;

    private ProgressDialog progressDialog;

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
        //用户名 校验不通过
        if (!isUserName(userName)) {
            userNameWrapper.setError("用户名长度需要大于6位");
        } else {
            userNameWrapper.setErrorEnabled(false);
        }

        // pwd 校验不通过
        if (!isPwd(pwd)) {
            pwdWrapper.setError("密码长度不得小于6位");
        } else {
            pwdWrapper.setErrorEnabled(false);
        }

        //校验均通过 presenter连接
        if (isUserName(userName) && isPwd(pwd)) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("加载中...");
            progressDialog.show();
            mPresenter.register(userName, pwd);
        }
    }

    private boolean isUserName(String userName) {
        return userName.trim().toString().length() >= 6;
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
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        //切换到登录界面的fragment
        LoginActivity activity = (LoginActivity) getActivity();
        activity.transFragment(1);
        activity.setValue(etUserName.getText().toString().trim(), etPwd.getText().toString().trim());
    }

    @Override
    public void showFailRegister(String result) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        AlertDialog showDialog = new AlertDialog.Builder(getContext())
                .setNegativeButton("确定", null)
                .setMessage(result)
                .create();
        showDialog.show();
    }
}
