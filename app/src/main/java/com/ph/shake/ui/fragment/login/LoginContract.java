package com.ph.shake.ui.fragment.login;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 */

public interface LoginContract {

    /**
     * 登录
     * @param userName
     * @param pwd
     */
    void login(String userName, String pwd);

    /**
     * 注册
     */
    void register();

}
