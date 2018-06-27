package com.itislevel.lyl.mvp.model.http.exception;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 10:55
 * 修改人:itisi
 * 修改时间: 2017/7/6 10:55
 * 修改内容:itisi
 * *********************
 */

public class ApiException extends Exception {
    private int code;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
