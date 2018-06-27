package com.itislevel.lyl.mvp.model.http.response;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/27.14:17
 * path:com.itislevel.lyl.mvp.model.http.response.LYLResponse
 **/
public class LYLResponse_Two<T> {
    private T status;
    private String msg;
    private String data;

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
