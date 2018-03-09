package com.ph.shake.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ph.lib.BaseActivity;
import com.ph.lib.injector.ContentView;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;
import com.ph.shake.ui.activity.home.HomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 */

@ContentView(R.layout.activity_login)
@Presenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<LoginPresenter, ILoginView> implements ILoginView {
    // 邮箱的正则表达式
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    TextInputLayout userNameWrapper;
    TextInputLayout pwdWrapper;

    EditText etUserName;
    EditText etPwd;

    @Override
    protected void init() {
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        userNameWrapper = findViewById(R.id.username_wrapper);
        pwdWrapper = findViewById(R.id.pwd_wrapper);
        etUserName = findViewById(R.id.username);
        etPwd = findViewById(R.id.pwd);
    }

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
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
