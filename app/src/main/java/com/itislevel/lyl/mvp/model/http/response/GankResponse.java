package com.itislevel.lyl.mvp.model.http.response;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 10:47
 * 修改人:itisi
 * 修改时间: 2017/7/6 10:47
 * 修改内容:itisi
 * *********************
 */

public class GankResponse<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
