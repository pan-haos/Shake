package com.ph.connection;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-8
 */

public interface OnReceiver {

    /**
     * 连接打开时
     *
     * @param msg
     */
    void onOpen(String msg);

    /**
     * 收到消息时
     *
     * @param msg
     */
    void onMessage(String msg);

    /**
     * 关闭时
     *
     * @param msg
     */
    void onClose(String msg);

    /**
     * 失败时
     *
     * @param msg
     * @param e
     */
    void onFail(String msg, Throwable e);
}
