package com.itislevel.lyl.mvp.model.bean;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 16:10
 * 修改人:itisi
 * 修改时间: 2017/7/7 16:10
 * 修改内容:itisi
 * *********************
 */

public class MeiZiBean {

    /**
     * _id : 595ed766421aa90ca209c407
     * createdAt : 2017-07-07T08:35:50.172Z
     * desc : 7-7
     * publishedAt : 2017-07-07T12:14:57.685Z
     * source : chrome
     * type : 福利
     * url : https://ws1.sinaimg.cn/large/610dc034ly1fhb0t7ob2mj20u011itd9.jpg
     * used : true
     * who : daimajia
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private int height; //自定义的属性 图片的高度
    private int itemType; //自定义的属性 布局类型

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
