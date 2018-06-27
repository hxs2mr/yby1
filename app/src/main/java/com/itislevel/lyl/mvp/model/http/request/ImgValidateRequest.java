package com.itislevel.lyl.mvp.model.http.request;

/**
 * desc:图片验证码
 * user:itisi
 * date:2017/11/28.18:07
 * path:com.itislevel.lyl.mvp.model.http.request.ImgValidateRequest
 **/
public class ImgValidateRequest extends LYLRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
