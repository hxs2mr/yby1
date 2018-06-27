package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\5\30 0030.
 */

public class ComplaintTypeBean {

    /**
     * id : 1
     * catename : 维修服务
     * sign : 1
     * createdtime : 1527564608000
     */

    private int id;
    private String catename;
    private String sign;
    private long createdtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }
}
