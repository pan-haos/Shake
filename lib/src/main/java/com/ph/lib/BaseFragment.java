package com.ph.lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph.lib.injector.PresenterInjector;
import com.ph.lib.injector.ViewInjector;
import com.ph.lib.mvp.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 */
public class BaseFragment<T extends IPresenter<V>, V> extends Fragment {

    protected T mPresenter;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 从内存缓存中取
        View view = Cache.get(this.getClass().getName());
        if (view == null) {
            view = ViewInjector.inject(this, container, inflater);
            unbinder = ButterKnife.bind(this, view);
            Cache.put(this.getClass().getName(), view);
            mPresenter = (T) PresenterInjector.inject(this);
            mPresenter.attach((V) this);
            firstInit(view);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            refreshInit(view);
        }
        return view;
    }

    /**
     * 第一次view为空时加载
     * 如果只在第一次加载时调用重写该方法
     *
     * @param view
     */
    protected void firstInit(View view) {
    }

    /**
     * 缓存onCreateView时加载
     * 如果每次界面刷新时都要调用重写该方法
     * @param view
     */
    private void refreshInit(View view) {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //移除内存缓存
        String name = getClass().getName();
        if (Cache.get(name) != null) {
            Log.e(TAG, "onDestroy: 内存缓存移除了" + name);
            Cache.remove(name);
        }
        //移除ButterKnife绑定关系
        if (unbinder != null) {
            unbinder.unbind();
        }
        //移除与presenter的绑定关系
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

}
