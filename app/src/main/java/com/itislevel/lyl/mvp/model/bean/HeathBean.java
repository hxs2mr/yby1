package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\6\13 0013.
 */

public class HeathBean {

    /**
     * istop : 0
     * reliable : null
     * id : 76
     * pointnum : 0
     * logo : 87B739F8-FCB4-4894-B469-A2793457E305.png
     * title : 擦亮眼，教你识别、使用常见家庭名贵中药材
     * status : 1
     * createdtime : 1528856735000
     * looknum : 0
     * nosense : null
     * publisher : 甚好
     */

    private int istop;
    private Object reliable;
    private int id;
    private int pointnum;
    private String logo;
    private String title;
    private int status;
    private long createdtime;
    private int looknum;
    private Object nosense;
    private String publisher;
    private String static_htm_url;

    public HeathBean(int id, int pointnum, String logo, String title, long createdtime, String publisher,String static_htm_url,Object nosense,int looknum) {
        this.id = id;
        this.pointnum = pointnum;
        this.logo = logo;
        this.title = title;
        this.createdtime = createdtime;
        this.publisher = publisher;
        this.static_htm_url = static_htm_url;
        this.nosense = nosense;
        this.looknum = looknum;
    }

    public String getStatic_htm_url() {
        return static_htm_url;
    }

    public void setStatic_htm_url(String static_htm_url) {
        this.static_htm_url = static_htm_url;
    }

    public int getIstop() {
        return istop;
    }

    public void setIstop(int istop) {
        this.istop = istop;
    }

    public Object getReliable() {
        return reliable;
    }

    public void setReliable(Object reliable) {
        this.reliable = reliable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointnum() {
        return pointnum;
    }

    public void setPointnum(int pointnum) {
        this.pointnum = pointnum;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }

    public int getLooknum() {
        return looknum;
    }

    public void setLooknum(int looknum) {
        this.looknum = looknum;
    }

    public Object getNosense() {
        return nosense;
    }

    public void setNosense(Object nosense) {
        this.nosense = nosense;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
