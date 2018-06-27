package com.itislevel.lyl.mvp.model.http.request;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/27.17:07
 * path:com.itislevel.lyl.mvp.model.http.request.LoginRequest
 **/
public class LoginRequest extends LYLRequest {

    private String name;

    /**
     * 用户密码
     */
    private String password;
    /**
     * 验证码
     */
    private String randcode;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
