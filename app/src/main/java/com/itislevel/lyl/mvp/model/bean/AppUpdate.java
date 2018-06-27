package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-10.15:47
 * path:com.itislevel.lyl.mvp.model.bean.AppUpdate
 **/
public class AppUpdate {

    /**
     * appurl : http://app.yobangyo.com/download/app-armeabi-v7a-release.apk
     * version : 1.0
     */

    private String appurl;
    private String version;

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
