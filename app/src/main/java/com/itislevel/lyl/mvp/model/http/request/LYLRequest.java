package com.itislevel.lyl.mvp.model.http.request;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/27.17:03
 * path:com.itislevel.lyl.mvp.model.http.request.LYLRequest
 **/
public class LYLRequest {

    /**
     * 令牌 token
     */
    private String token="null";
    /**
     * 用户id
     */
    private String usernum="null";


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsernum() {
        return usernum;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }


}
