package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-20.10:21
 * path:com.itislevel.lyl.mvp.model.bean.SpecialTypeBean
 **/
public class SpecialGiftByIdBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * goodsname : 香皂
         * imggroup : 7815804B-34F7-44CF-B59F-56F7600B2564.png
         * logourl : 7815804B-34F7-44CF-B59F-56F7600B2564.png
         * sellerprice : 0.01
         * goodsrem : 香皂测试
         * sellerid : 1
         */

        private String goodsname;
        private String imggroup;
        private String logourl;
        private String sellerprice;
        private String goodsrem;
        private int sellerid;

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getImggroup() {
            return imggroup;
        }

        public void setImggroup(String imggroup) {
            this.imggroup = imggroup;
        }

        public String getLogourl() {
            return logourl;
        }

        public void setLogourl(String logourl) {
            this.logourl = logourl;
        }

        public String getSellerprice() {
            return sellerprice;
        }

        public void setSellerprice(String sellerprice) {
            this.sellerprice = sellerprice;
        }

        public String getGoodsrem() {
            return goodsrem;
        }

        public void setGoodsrem(String goodsrem) {
            this.goodsrem = goodsrem;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }
    }
}
