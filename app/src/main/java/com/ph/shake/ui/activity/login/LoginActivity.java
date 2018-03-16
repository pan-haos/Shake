package com.ph.shake.ui.activity.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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


    private Fragment[] fragments = new Fragment[]{new ForgetFragment(), new LoginFragment(), new RegisterFragment()};

    private ViewPager viewPager;

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

        //获取权限
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ph", "onDestroy: homeactivity");
    }
}