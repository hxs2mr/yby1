package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class CityBean {
    String s_name;
    int id;

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CityBean(String s_name, int id) {
        this.s_name = s_name;
        this.id = id;
    }
}
