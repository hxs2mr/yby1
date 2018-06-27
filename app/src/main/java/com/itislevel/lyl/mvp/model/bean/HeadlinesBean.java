package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\4\11 0011.
 */

public class HeadlinesBean {
    private int id;
    private int cateid;
    private String title;
    private String catename;

    public HeadlinesBean(int id, int cateid, String title, String catename) {
        this.id = id;
        this.cateid = cateid;
        this.title = title;
        this.catename = catename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }
}
