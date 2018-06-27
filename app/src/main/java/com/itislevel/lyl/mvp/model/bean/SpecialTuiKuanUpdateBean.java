package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-20.10:21
 * path:com.itislevel.lyl.mvp.model.bean.SpecialTypeBean
 **/
public class SpecialTuiKuanUpdateBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * goodsname : 花露水
         * refundfee : 0.01
         * imge : 9E59126A-1B22-431F-A7E9-62CC2E29D61D.png, 5B6F5C0A-6A89-4480-AF63-AC0DBC994F6D.png
         * logourl : 3FBF3FEC-8D27-43AD-B428-5CC41CAB2B13.png
         * goodsid : 4
         * refundreason : 7天无理由退换货
         * ordernum : 20180124010020017006002
         * orderid : 78
         * refundexplain : 无理由
         */

        private String goodsname;
        private String refundfee;
        private String imge;
        private String logourl;
        private int goodsid;
        private String refundreason;
        private String ordernum;
        private int orderid;
        private String refundexplain;

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getRefundfee() {
            return refundfee;
        }

        public void setRefundfee(String refundfee) {
            this.refundfee = refundfee;
        }

        public String getImge() {
            return imge;
        }

        public void setImge(String imge) {
            this.imge = imge;
        }

        public String getLogourl() {
            return logourl;
        }

        public void setLogourl(String logourl) {
            this.logourl = logourl;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public String getRefundreason() {
            return refundreason;
        }

        public void setRefundreason(String refundreason) {
            this.refundreason = refundreason;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getRefundexplain() {
            return refundexplain;
        }

        public void setRefundexplain(String refundexplain) {
            this.refundexplain = refundexplain;
        }
    }
}
