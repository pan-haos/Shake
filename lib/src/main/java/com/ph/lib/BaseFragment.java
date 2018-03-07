package com.ph.lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph.lib.factory.MVPFactory;
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
public class BaseFragment<P extends IPresenter<V>, V> extends Fragment {

    protected IPresenter<V> mPresenter;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 从内存缓存中取
        View view = Cache.get(this.getClass().getName());
        if (view == null) {
            view = ViewInjector.inject(this, inflater);
            Cache.put(this.getClass().getName(), view);
            unbinder = ButterKnife.bind(this, view);
            mPresenter = PresenterInjector.inject(this);
            firstInit(view);
        }
        refreshInit(view);
        return view;
    }

    /**
     * 第一次view为空时加载
     * @param view
     */
    protected void firstInit(View view) {}

    /**
     * 每次onCreateView时加载
     * @param view
     */
    private void refreshInit(View view) {}


    @Override
    public void onDestroy() {
        super.onDestroy();
        //移除内存缓存
        String name = getClass().getName();
        if (Cache.get(name) != null) {
            Cache.remove(name);
        }
        //移除Butterknife绑定
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}
