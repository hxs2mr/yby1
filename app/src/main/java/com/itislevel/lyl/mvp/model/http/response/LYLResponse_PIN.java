package com.itislevel.lyl.mvp.model.http.response;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/27.14:17
 * path:com.itislevel.lyl.mvp.model.http.response.LYLResponse
 **/
public class LYLResponse_PIN<T> {
    private int status;
    private T msg;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
