package com.ph.lib.mvp;

import java.lang.ref.WeakReference;
import java.nio.MappedByteBuffer;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-5
 * 基本Presenter基类
 */

public class Presenter<V> implements IPresenter<V> {

    protected WeakReference<V> mView;

    @Override
    public void attach(V view) {
        mView = new WeakReference<V>(view);
    }

    @Override
    public void detach() {
        if (mView != null) {
            if (mView.get() != null) {
                mView.clear();
            }
            mView = null;
        }
    }

    @Override
    public V get() {
        return mView != null && mView.get() != null ? mView.get() : null;
    }

}
