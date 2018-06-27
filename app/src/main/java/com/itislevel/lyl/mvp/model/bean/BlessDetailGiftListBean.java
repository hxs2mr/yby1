package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-06.15:01
 * path:com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean
 **/
public class BlessDetailGiftListBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * ordernum : null
         * moduleid : 0
         * modelename : null
         * buyuserid : 0
         * receiveuserid : 0
         * payfee : null
         * ispay : 0
         * isdel : 0
         * goodsbody : null
         * paytime : null
         * createdtime : 1515124461297
         * nickname : null
         * tonickname : null
         * goodsid : 2
         * imgurl : 5E9DF916-4739-4443-BB25-0454B8EC763D.png
         * count : 1
         * goodsname : 礼品测试2
         */

        private Object ordernum;
        private int moduleid;
        private Object modelename;
        private int buyuserid;
        private int receiveuserid;
        private Object payfee;
        private String ispay;
        private String isdel;
        private Object goodsbody;
        private Object paytime;
        private long createdtime;
        private Object nickname;
        private Object tonickname;
        private int goodsid;
        private String imgurl;
        private int count;
        private String goodsname;

        public Object getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(Object ordernum) {
            this.ordernum = ordernum;
        }

        public int getModuleid() {
            return moduleid;
        }

        public void setModuleid(int moduleid) {
            this.moduleid = moduleid;
        }

        public Object getModelename() {
            return modelename;
        }

        public void setModelename(Object modelename) {
            this.modelename = modelename;
        }

        public int getBuyuserid() {
            return buyuserid;
        }

        public void setBuyuserid(int buyuserid) {
            this.buyuserid = buyuserid;
        }

        public int getReceiveuserid() {
            return receiveuserid;
        }

        public void setReceiveuserid(int receiveuserid) {
            this.receiveuserid = receiveuserid;
        }

        public Object getPayfee() {
            return payfee;
        }

        public void setPayfee(Object payfee) {
            this.payfee = payfee;
        }

        public String getIspay() {
            return ispay;
        }

        public void setIspay(String ispay) {
            this.ispay = ispay;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public Object getGoodsbody() {
            return goodsbody;
        }

        public void setGoodsbody(Object goodsbody) {
            this.goodsbody = goodsbody;
        }

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
            this.paytime = paytime;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getTonickname() {
            return tonickname;
        }

        public void setTonickname(Object tonickname) {
            this.tonickname = tonickname;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }
    }
}
