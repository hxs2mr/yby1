package com.itislevel.lyl.mvp.model.http.request;

/**
 * desc:短信验证码
 * user:itisi
 * date:2017/11/28.18:07
 * path:com.itislevel.lyl.mvp.model.http.request.ImgValidateRequest
 **/
public class SMSValidateRequest extends LYLRequest {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
