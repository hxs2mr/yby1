package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-13.16:21
 * path:com.itislevel.lyl.mvp.model.bean.CartUpdateBean
 **/
public class CartUpdateBean {

    private List<ShopcartlistBean> shopcartlist;

    public List<ShopcartlistBean> getShopcartlist() {
        return shopcartlist;
    }

    public void setShopcartlist(List<ShopcartlistBean> shopcartlist) {
        this.shopcartlist = shopcartlist;
    }

    public static class ShopcartlistBean {
        /**
         * goodsid : 11
         * cateid : 17
         * price : 0.02
         * count : 1
         * goodsname : 花开富贵
         * imgurl : E3B9486A-78D6-4ED4-BBF8-899859FB3D7C.png
         * attribute : null
         * goodsrem : null
         */

        private int goodsid;
        private int cateid;
        private String price;
        private int count;
        private String goodsname;
        private String imgurl;
        private Object attribute;
        private Object goodsrem;

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public int getCateid() {
            return cateid;
        }

        public void setCateid(int cateid) {
            this.cateid = cateid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public Object getAttribute() {
            return attribute;
        }

        public void setAttribute(Object attribute) {
            this.attribute = attribute;
        }

        public Object getGoodsrem() {
            return goodsrem;
        }

        public void setGoodsrem(Object goodsrem) {
            this.goodsrem = goodsrem;
        }
    }
}
