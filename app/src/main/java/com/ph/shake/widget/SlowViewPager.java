package com.ph.shake.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.Scroller;

import com.ph.shake.transform.AnimateTransForm;
import com.ph.shake.transform.ZoomOutTransformer;

import java.lang.reflect.Field;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-12
 */

public class SlowViewPager extends ViewPager {

    private Scroller mScroller;

    public SlowViewPager(Context context) {
        super(context);
    }

    public SlowViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScroller(context);
        initAnimation();
    }

    private void initAnimation() {
        this.setPageTransformer(true, new ZoomOutTransformer());
    }

    private void initScroller(Context context) {
        mScroller = new SlowScroller(context);
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(this, mScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
