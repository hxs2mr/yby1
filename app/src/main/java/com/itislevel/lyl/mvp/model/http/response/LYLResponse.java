package com.itislevel.lyl.mvp.model.http.response;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/27.14:17
 * path:com.itislevel.lyl.mvp.model.http.response.LYLResponse
 **/
public class LYLResponse<T> {
    private int status;
    private String msg;
    private T data;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
