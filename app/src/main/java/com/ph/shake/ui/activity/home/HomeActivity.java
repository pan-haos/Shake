package com.ph.shake.ui.activity.home;

import com.ph.lib.BaseActivity;
import com.ph.lib.injector.ContentView;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-9
 */
@ContentView(R.layout.activity_login)
@Presenter(HomePresenter.class)
public class HomeActivity extends BaseActivity<HomePresenter, IHomeView> {

    @Override
    protected void init() {

    }
}
