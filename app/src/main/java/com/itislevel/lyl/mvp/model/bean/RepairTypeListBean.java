package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class RepairTypeListBean {

    /**
     * id : 1
     * catename : 综合维修工
     * sign : null
     * createdtime : 1528255024569
     */

    private int id;
    private String catename;
    private Object sign;
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

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }
}
