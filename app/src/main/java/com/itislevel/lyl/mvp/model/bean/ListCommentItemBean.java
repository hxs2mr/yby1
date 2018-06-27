package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class ListCommentItemBean {
 /**
 * id : 1
 * createdtime : 1524818030000
 * userid : 39
 * dynamicid : 1
 * touserid : 0
 * answerer :
 * parentid :
 * comment : 哈哈哈有意思吗
 * observer : pmg123456
 * fabulous : 0
 */

private int id;
    private long createdtime;
    private int userid;
    private int dynamicid;
    private int touserid;
    private String answerer;
    private String parentid;
    private String comment;
    private String observer;
    private int fabulous;

    public ListCommentItemBean(int id, long createdtime, int userid, int dynamicid, int touserid, String answerer, String parentid, String comment, String observer, int fabulous) {
        this.id = id;
        this.createdtime = createdtime;
        this.userid = userid;
        this.dynamicid = dynamicid;
        this.touserid = touserid;
        this.answerer = answerer;
        this.parentid = parentid;
        this.comment = comment;
        this.observer = observer;
        this.fabulous = fabulous;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getDynamicid() {
        return dynamicid;
    }

    public void setDynamicid(int dynamicid) {
        this.dynamicid = dynamicid;
    }

    public int getTouserid() {
        return touserid;
    }

    public void setTouserid(int touserid) {
        this.touserid = touserid;
    }

    public String getAnswerer() {
        return answerer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public int getFabulous() {
        return fabulous;
    }

    public void setFabulous(int fabulous) {
        this.fabulous = fabulous;
    }
}
