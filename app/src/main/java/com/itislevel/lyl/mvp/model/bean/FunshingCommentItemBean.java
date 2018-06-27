package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/15.18:13
 * path:com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean
 **/
public class FunshingCommentItemBean extends AbstractExpandableItem<FunshingReplayItemBean> implements MultiItemEntity {

    private int id;
    private int shareid;
    private int userid;
    private int touserid;
    private String username;
    private String observer;
    private Object answerer;
    private String parentid;
    private String parentname;
    private String comment;
    private String fabulous;
    private String status;
    private Object createdtime;
    private String fabuzheid;//发布者id
    private List<FunshingReplayItemBean> comments1;

    public String getFabuzheid() {
        return fabuzheid;
    }

    public void setFabuzheid(String fabuzheid) {
        this.fabuzheid = fabuzheid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public List<FunshingReplayItemBean> getComments1() {
        return comments1;
    }

    public void setComments1(List<FunshingReplayItemBean> comments1) {
        this.comments1 = comments1;
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

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
