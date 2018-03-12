package com.ph.shake.ui.activity.login;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ph.lib.BaseActivity;
import com.ph.lib.injector.LayoutId;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;
import com.ph.shake.ui.fragment.forget.ForgetFragment;
import com.ph.shake.ui.fragment.login.LoginFragment;
import com.ph.shake.ui.fragment.register.RegisterFragment;


/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 */

@LayoutId(R.layout.activity_login)
@Presenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<LoginPresenter, ILoginView> {

    Fragment[] fragments = new Fragment[]{new ForgetFragment(), new LoginFragment(), new RegisterFragment()};

    ViewPager viewPager;

    @Override
    protected void init() {
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            initViewPager();
        }
    }


    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }
        });
        viewPager.setCurrentItem(1);
    }

    public void transFragment(int index) {
        viewPager.setCurrentItem(index, true);
    }

}