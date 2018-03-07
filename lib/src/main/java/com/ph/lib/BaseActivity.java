package com.ph.lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ph.lib.injector.PresenterInjector;
import com.ph.lib.injector.ViewInjector;
import com.ph.lib.mvp.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 */

public class BaseActivity<T, V> extends AppCompatActivity {

    protected IPresenter<V> mPresenter;

    private Unbinder bind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 绑定setContentView
         */
        ViewInjector.inject(this);

        /**
         * 绑定Fragment
         */
        bind = ButterKnife.bind(this);

        /**
         * 创建并绑定到Presenter
         */
        mPresenter = PresenterInjector.inject(this);
        mPresenter.attach((V) this);

        /**
         *程序入口
         */
        init();
    }

    /**
     * Activity启动入口，需要则重写
     */
    protected void init() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除bind的绑定
        if (bind != null) {
            bind.unbind();
        }
        //解除presenter的绑定
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
