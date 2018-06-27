package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/14.16:31
 * path:com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean
 **/
public class DynamicCommnetAddBean {

    /**
     * id : 140
     * status : 1
     * nickname : itisi
     * createdtime : 1513318248000
     * userid : 3
     * touserid : 4
     * shareid : 12
     * parentid : 52
     * comment : 999
     * tonickname : joke
     * fabulous : 1
     */
    private int id;
    private String status;
    private String observer;//nickname
    private long createdtime;
    private int userid;
    private int touserid;
    private int shareid;
    private String parentid;
    private String comment;
    private String answerer;//tonickname
    private int fabulous;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
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

    public int getTouserid() {
        return touserid;
    }

    public void setTouserid(int touserid) {
        this.touserid = touserid;
    }

    public int getShareid() {
        return shareid;
    }

    public void setShareid(int shareid) {
        this.shareid = shareid;
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

    public String getAnswerer() {
        return answerer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public int getFabulous() {
        return fabulous;
    }

    public void setFabulous(int fabulous) {
        this.fabulous = fabulous;
    }
}
