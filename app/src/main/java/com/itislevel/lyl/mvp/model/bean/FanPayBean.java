package com.itislevel.lyl.mvp.model.bean;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\4 0004 18:31
 */
public class FanPayBean {
    String total_paces="";

    public FanPayBean(String total_paces) {
        this.total_paces = total_paces;
    }

    public String getTotal_paces() {
        return total_paces;
    }

    public void setTotal_paces(String total_paces) {
        this.total_paces = total_paces;
    }
}
