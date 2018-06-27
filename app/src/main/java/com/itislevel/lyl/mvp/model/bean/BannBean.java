package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\4\11 0011.
 */

public class BannBean {

    /**
     * logo : 1736BCC7-FC86-4B19-9019-2A7543FA2CD2.png
     * name : 143213
     * aid : 1
     */

    private String logo;
    private String name;
    private int aid;

    public BannBean(String logo, String name, int aid) {
        this.logo = logo;
        this.name = name;
        this.aid = aid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
}
