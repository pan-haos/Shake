package com.ph.lib.injector;


import com.ph.lib.BaseActivity;
import com.ph.lib.BaseFragment;
import com.ph.lib.factory.MVPFactory;
import com.ph.lib.mvp.IPresenter;

/**
 * 项目： Shake
 * Programmer: 潘浩
 */

public class PresenterInjector {

    /**
     * 获取activity中的presenter
     *
     * @param activity activity
     * @return activity 中的presenter
     */
    public static IPresenter inject(BaseActivity activity) {
        Presenter annotation = activity.getClass().getAnnotation(Presenter.class);
        if (annotation == null) {
            throw new NullPointerException("activity's annotation presenter can't be null");
        }
        Class<? extends com.ph.lib.mvp.Presenter> value = annotation.value();
        IPresenter presenter = MVPFactory.createPresenter(value);
        return presenter;
    }

    /**
     * 获取fragment中的presenter
     *
     * @param fragment fragment
     * @return 返回结果
     */
    public static IPresenter inject(BaseFragment fragment) {
        Presenter annotation = fragment.getClass().getAnnotation(Presenter.class);
        if (annotation == null) {
            throw new NullPointerException("fragment's annotation presenter can't be null");
        }
        Class<? extends com.ph.lib.mvp.Presenter> value = annotation.value();
        IPresenter presenter = MVPFactory.createPresenter(value);
        return presenter;
    }


}

