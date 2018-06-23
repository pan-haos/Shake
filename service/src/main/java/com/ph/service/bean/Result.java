package com.ph.service.bean;

import android.support.annotation.Nullable;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-5  下午11:51
 */
public class Result<T> {
    private int code;

    private String message;

    @Nullable
    private T content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
