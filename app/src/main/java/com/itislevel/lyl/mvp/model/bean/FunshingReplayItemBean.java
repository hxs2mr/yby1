package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/15.18:13
 * path:com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean
 **/
public class FunshingReplayItemBean implements MultiItemEntity {


    /**
     * id : 174
     * shareid : 12
     * userid : 3
     * touserid : 4
     * username : null
     * nickname : itisi
     * tonickname : joke
     * parentid : 151
     * parentname : null
     * comment : 我们都在家
     * fabulous : 1
     * status : 1
     * createdtime : 1513332068000
     * comments1 : null
     */

    private int id;
    private int shareid;
    private int userid;
    private int touserid;
    private Object username;
    private String observer;
    private Object answerer;
    private String parentid;
    private Object parentname;
    private String comment;
    private String fabulous;
    private String status;
    private Object createdtime;
    private Object comments1;
    private String fabuzheid;//发布者id

    public String getFabuzheid() {
        return fabuzheid;
    }

    public void setFabuzheid(String fabuzheid) {
        this.fabuzheid = fabuzheid;
    }

    @Override
    public int getItemType() {
        return 2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShareid() {
        return shareid;
    }

    public void setShareid(int shareid) {
        this.shareid = shareid;
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

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }


    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public Object getAnswerer() {
        return answerer;
    }

    public void setAnswerer(Object answerer) {
        this.answerer = answerer;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Object getParentname() {
        return parentname;
    }

    public void setParentname(Object parentname) {
        this.parentname = parentname;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Object createdtime) {
        this.createdtime = createdtime;
    }

    public Object getComments1() {
        return comments1;
    }

    public void setComments1(Object comments1) {
        this.comments1 = comments1;
    }

    public FunshingReplayItemBean(){

    }
    public FunshingReplayItemBean(int id, int shareid, int userid, int touserid, String observer, String answerer, String parentid, String comment, String fabulous, Object createdtime) {
        this.id = id;
        this.shareid = shareid;
        this.userid = userid;
        this.touserid = touserid;
        this.observer = observer;
        this.answerer = answerer;
        this.parentid = parentid;
        this.comment = comment;
        this.fabulous = fabulous;
        this.createdtime = createdtime;
    }
}
