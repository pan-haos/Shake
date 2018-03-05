package com.ph.lib.injector;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.ph.lib.BaseActivity;
import com.ph.lib.BaseFragment;
import com.ph.lib.factory.MVPFactory;
import com.ph.lib.mvp.IPresenter;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 */

public class PresenterInjector {

    public static <V> IPresenter<V> inject(BaseActivity activity) {
        Presenter annotation = activity.getClass().getAnnotation(Presenter.class);
        if (annotation == null) {
            throw new NullPointerException("activity's annotation presenter can't be null");
        }
        Class<? extends com.ph.lib.mvp.Presenter> value = annotation.value();
        if (value == null) {
            throw new NullPointerException("presenter can't be null");
        }
        IPresenter presenter = MVPFactory.createPresenter(value);
        return presenter;
    }

    public static <V> IPresenter<V> inject(BaseFragment fragment) {
        Presenter annotation = fragment.getClass().getAnnotation(Presenter.class);
        if (annotation == null) {
            throw new NullPointerException("fragment's annotation presenter can't be null");
        }
        Class<? extends com.ph.lib.mvp.Presenter> value = annotation.value();
        if (value == null) {
            throw new NullPointerException("presenter can't be null");
        }
        IPresenter presenter = MVPFactory.createPresenter(value);
        return presenter;
    }

}

