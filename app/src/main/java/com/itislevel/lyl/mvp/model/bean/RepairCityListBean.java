package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class RepairCityListBean {

    /**
     * id : 520102
     * s_name : 南明
     * t_name : null
     * e_name : null
     * pid : 0
     */

    private int id;
    private String s_name;
    private Object t_name;
    private Object e_name;
    private int pid;
    /**
     * catename : 综合维修工
     * sign : null
     * createdtime : 1528255024569
     */

    private String catename;
    private Object sign;
    private long createdtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public Object getT_name() {
        return t_name;
    }

    public void setT_name(Object t_name) {
        this.t_name = t_name;
    }

    public Object getE_name() {
        return e_name;
    }

    public void setE_name(Object e_name) {
        this.e_name = e_name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
