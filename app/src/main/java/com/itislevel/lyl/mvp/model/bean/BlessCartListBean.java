package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017-12-28.17:26
 * path:com.itislevel.lyl.mvp.model.bean.BlessCartListBean
 **/
public class BlessCartListBean {

    private List<ShopcartlistBean> shopcartlist;

    public List<ShopcartlistBean> getShopcartlist() {
        return shopcartlist;
    }

    public void setShopcartlist(List<ShopcartlistBean> shopcartlist) {
        this.shopcartlist = shopcartlist;
    }

    public static class ShopcartlistBean {
        /**
         * goodsid : 1211
         * cateid : 1
         * price : 1
         * count : 1
         * goodsname : bbb
         * imgurl : xxxx.png
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


        private boolean isSelect=false;

        private String mailfee;

        public String getMailfee() {
            return mailfee;
        }

        public void setMailfee(String mailfee) {
            this.mailfee = mailfee;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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
