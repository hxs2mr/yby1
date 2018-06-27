package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public class GiftBean {

    /**
     * giftid : 1
     * firstcateid : 13
     * giftname : 白蛋糕
     * sellprice : 0.01
     * imgurl : 9ED75A1E-6DC9-4EF4-9C73-E136892D563D.png
     */

    private int giftid;
    private int firstcateid;
    private String giftname;
    private String sellprice;
    private String imgurl;
    private boolean isOnclick = true;

    public boolean isOnclick() {
        return isOnclick;
    }

    public void setOnclick(boolean onclick) {
        isOnclick = onclick;
    }

    public int getGiftid() {
        return giftid;
    }

    public void setGiftid(int giftid) {
        this.giftid = giftid;
    }

    public int getFirstcateid() {
        return firstcateid;
    }

    public void setFirstcateid(int firstcateid) {
        this.firstcateid = firstcateid;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
