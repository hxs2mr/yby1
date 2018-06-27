package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\4\11 0011.
 */

public class ModularBean {
    private int id;
    private String icon;
    private String catename;

    public ModularBean(int id, String icon, String catename) {
        this.id = id;
        this.icon = icon;
        this.catename = catename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }
}
