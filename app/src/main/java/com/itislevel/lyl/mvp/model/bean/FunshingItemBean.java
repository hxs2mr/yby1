package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/15.18:13
 * path:com.itislevel.lyl.mvp.model.bean.FunshingItemBean
 **/
public class FunshingItemBean extends AbstractExpandableItem<FunshingCommentItemBean> implements MultiItemEntity {

    private int id;
    private int userid;
    private String username;
    private String nickname;
    private Object addressid;
    private String content;
    private String imge;
    private Object video;
    private String status;
    private long createdtime;
    private Object useridfabulous;
    private int fabulousnumber;
    private String ispoint;
    private Object parentid;
    private Object parentname;
    private Object comment;
    String imgurl;
    private int img_w;
    private int img_h;
    private String isfollow;
    private List<String> nmpointlist;
    private List<FunshingCommentItemBean> shmlist;
    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public int getImg_w() {
        return img_w;
    }

    public void setImg_w(int img_w) {
        this.img_w = img_w;
    }

    public int getImg_h() {
        return img_h;
    }

    public void setImg_h(int img_h) {
        this.img_h = img_h;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getIspoint() {
        return ispoint;
    }

    public void setIspoint(String ispoint) {
        this.ispoint = ispoint;
    }

    public List<String> getNmpointlist() {
        return nmpointlist;
    }

    public void setNmpointlist(List<String> nmpointlist) {
        this.nmpointlist = nmpointlist;
    }

    public List<FunshingCommentItemBean> getShmlist() {
        return shmlist;
    }

    public void setShmlist(List<FunshingCommentItemBean> shmlist) {
        this.shmlist = shmlist;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Object getAddressid() {
        return addressid;
    }

    public void setAddressid(Object addressid) {
        this.addressid = addressid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
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

    public Object getUseridfabulous() {
        return useridfabulous;
    }

    public void setUseridfabulous(Object useridfabulous) {
        this.useridfabulous = useridfabulous;
    }

    public int getFabulousnumber() {
        return fabulousnumber;
    }

    public void setFabulousnumber(int fabulousnumber) {
        this.fabulousnumber = fabulousnumber;
    }


    public Object getParentid() {
        return parentid;
    }

    public void setParentid(Object parentid) {
        this.parentid = parentid;
    }

    public Object getParentname() {
        return parentname;
    }

    public void setParentname(Object parentname) {
        this.parentname = parentname;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }


    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
