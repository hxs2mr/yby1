package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\5\16 0016.
 */

public class CFTabBean {

    /**
     * id : 7
     * catename : 健康
     */

    private int id;
    private String catename;

    public CFTabBean(int id, String catename) {
        this.id = id;
        this.catename = catename;
    }

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
}
