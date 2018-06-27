package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public class CatelistBean {
    /**
     * id : 1
     * catename : 综合维修工
     * sign : null
     * createdtime : null
     */

    private int id;
    private String catename;
    private Object sign;
    private Object createdtime;

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

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public Object getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Object createdtime) {
        this.createdtime = createdtime;
    }
}
