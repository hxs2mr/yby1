package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/27.21:17
 * path:com.itislevel.lyl.mvp.model.bean.BlessCommentBean
 **/
public class BlessCommentBean {

    /**
     * id : 8
     * nickname : itisi
     * status : 1
     * createdtime : 1514380583000
     * userid : 1
     * touserid : 0
     * parentid : 11
     * parentname :
     * happyid : 11
     * comment : çæ´»å¦æï¼äºä¸é«å
     * fabulous : 0
     */

    private int id;
    private String observer;
    private String status;
    private long createdtime;
    private int userid;
    private int touserid;
    private String answerer;

    private String parentid;
    private String parentname;
    private int happyid;
    private String comment;
    private String fabulous;

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getAnswerer() {
        return answerer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public int getHappyid() {
        return happyid;
    }

    public void setHappyid(int happyid) {
        this.happyid = happyid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFabulous() {
        return fabulous;
    }

    public void setFabulous(String fabulous) {
        this.fabulous = fabulous;
    }
}
