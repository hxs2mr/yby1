package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-20.10:21
 * path:com.itislevel.lyl.mvp.model.bean.SpecialTypeBean
 **/
public class SpecialGiftListBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * goodsid : 9
         * goodsname : 毛巾
         * sellerid : 1 买家id
         * cateid : 0
         * stock : 0
         * sellerprice : 0.01
         * freeprice : 0.00
         * mailfee : null
         * status : 0
         * type : 1
         * isdel : 0
         * logourl : 7355D8C2-9F7C-40E9-AF30-B3AB8AE1BC4A.jpg
         * sales : 0
         * attribute : null
         * imggroup : 7355D8C2-9F7C-40E9-AF30-B3AB8AE1BC4A.jpg
         * goodsrem : 日化测试

         * ontime : null
         * offtime : null
         * createdtime : 1516421284078
         */

        private int goodsid;
        private String goodsname;
        private int sellerid;
        private int cateid;
        private int stock;
        private String sellerprice;
        private String freeprice;
        private Object mailfee;
        private String status;
        private String type;
        private String isdel;
        private String logourl;
        private int sales;
        private Object attribute;
        private String imggroup;
        private String goodsrem;
        private Object ontime;
        private Object offtime;
        private long createdtime;

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public int getCateid() {
            return cateid;
        }

        public void setCateid(int cateid) {
            this.cateid = cateid;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getSellerprice() {
            return sellerprice;
        }

        public void setSellerprice(String sellerprice) {
            this.sellerprice = sellerprice;
        }

        public String getFreeprice() {
            return freeprice;
        }

        public void setFreeprice(String freeprice) {
            this.freeprice = freeprice;
        }

        public Object getMailfee() {
            return mailfee;
        }

        public void setMailfee(Object mailfee) {
            this.mailfee = mailfee;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public String getLogourl() {
            return logourl;
        }

        public void setLogourl(String logourl) {
            this.logourl = logourl;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public Object getAttribute() {
            return attribute;
        }

        public void setAttribute(Object attribute) {
            this.attribute = attribute;
        }

        public String getImggroup() {
            return imggroup;
        }

        public void setImggroup(String imggroup) {
            this.imggroup = imggroup;
        }

        public String getGoodsrem() {
            return goodsrem;
        }

        public void setGoodsrem(String goodsrem) {
            this.goodsrem = goodsrem;
        }

        public Object getOntime() {
            return ontime;
        }

        public void setOntime(Object ontime) {
            this.ontime = ontime;
        }

        public Object getOfftime() {
            return offtime;
        }

        public void setOfftime(Object offtime) {
            this.offtime = offtime;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }
    }
}
