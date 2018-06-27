package com.itislevel.lyl.mvp.model.http.request;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/29.10:16
 * path:com.itislevel.lyl.mvp.model.http.request.RegistRequest
 **/
public class RegistRequest extends LYLRequest {
    private String username;
    private String phone;
    private String password;
    private String randcode;
    private String recommendphone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandcode() {
        return randcode;
    }

    public void setRandcode(String randcode) {
        this.randcode = randcode;
    }

    public String getRecommendphone() {
        return recommendphone;
    }

    public void setRecommendphone(String recommendphone) {
        this.recommendphone = recommendphone;
    }
}
