package com.ph.service.model;

import com.ph.service.bean.Result;
import com.ph.service.implementor.CommonImplementor;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-7
 * <p>
 * 正常类model
 */

public abstract class HttpCommonModel extends HttpModel<Result> {

    public HttpCommonModel() {
        this.implementor = new CommonImplementor();
    }
}
