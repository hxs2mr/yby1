package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017-12-28.17:24
 * path:com.itislevel.lyl.mvp.model.bean.BlessGiftBean
 **/
public class BlessGiftBean {
    private List<GiftBean> list;

    public List<GiftBean> getList() {
        return list;
    }

    public void setList(List<GiftBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * giftname : 礼品测试
         * imgurl : 3C765446-EE51-4572-99DF-BC9DFBB883F6.png
         * firstcateid : 16
         * sellprice : 550
         * giftid
         */

        private String giftname;
        private String imgurl;
        private int firstcateid;
        private String sellprice;
        private int giftid;

        public int getGiftid() {
            return giftid;
        }

        public void setGiftid(int giftid) {
            this.giftid = giftid;
        }

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getFirstcateid() {
            return firstcateid;
        }

        public void setFirstcateid(int firstcateid) {
            this.firstcateid = firstcateid;
        }

        public String getSellprice() {
            return sellprice;
        }

        public void setSellprice(String sellprice) {
            this.sellprice = sellprice;
        }
    }
}
