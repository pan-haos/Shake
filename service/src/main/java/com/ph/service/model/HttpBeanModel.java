package com.ph.service.model;

import com.ph.service.bean.Bean;
import com.ph.service.implementor.CommonImplementor;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:51
 * <p>
 * 被Bean包含的Model
 * <p>
 * 直接使用BeanImplementor 做桥接
 */
public abstract class HttpBeanModel<T> extends HttpModel<Bean<T>> {

    public HttpBeanModel() {
        this.implementor = new CommonImplementor<>();
    }

}
