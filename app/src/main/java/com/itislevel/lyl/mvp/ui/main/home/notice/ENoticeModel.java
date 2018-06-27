package com.itislevel.lyl.mvp.ui.main.home.notice;

/**
 * Created by zhaocheng on 2017/1/17.
 */

public class ENoticeModel {
    private String noticeTitle1;
    private  String noticeTitle2;

    public ENoticeModel(String noticeTitle1, String noticeTitle2) {
        this.noticeTitle1 = noticeTitle1;
        this.noticeTitle2 = noticeTitle2;
    }

    public String getNoticeTitle1() {
        return noticeTitle1;
    }

    public void setNoticeTitle1(String noticeTitle1) {
        this.noticeTitle1 = noticeTitle1;
    }

    public String getNoticeTitle2() {
        return noticeTitle2;
    }

    public void setNoticeTitle2(String noticeTitle2) {
        this.noticeTitle2 = noticeTitle2;
    }
}
