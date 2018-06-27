package com.itislevel.lyl.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018\5\4 0004.
 */

public class BaseGiftBean {
    /**
     * goodsname : 白蛋糕
     * buyuserid : 1
     * count : 1
     * goodsid : 1
     * nickname : 老沟
     * imgurl : 9ED75A1E-6DC9-4EF4-9C73-E136892D563D.png
     */


    private String goodsname;
    private int buyuserid;
    private int count;
    private int goodsid;
    @SerializedName("nickname")
    private String nicknameX;
    @SerializedName("imgurl")
    private String imgurlX;

    public BaseGiftBean(String goodsname, int buyuserid, int count, int goodsid, String nicknameX, String imgurlX) {
        this.goodsname = goodsname;
        this.buyuserid = buyuserid;
        this.count = count;
        this.goodsid = goodsid;
        this.nicknameX = nicknameX;
        this.imgurlX = imgurlX;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public int getBuyuserid() {
        return buyuserid;
    }

    public void setBuyuserid(int buyuserid) {
        this.buyuserid = buyuserid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public String getNicknameX() {
        return nicknameX;
    }

    public void setNicknameX(String nicknameX) {
        this.nicknameX = nicknameX;
    }

    public String getImgurlX() {
        return imgurlX;
    }

    public void setImgurlX(String imgurlX) {
        this.imgurlX = imgurlX;
    }
}
