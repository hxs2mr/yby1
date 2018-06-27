package com.itislevel.lyl.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-12.11:51
 * path:com.itislevel.lyl.mvp.model.bean.WeiXinPayTestBean
 **/
public class WeiXinPayTestBean {


    /**
     * orderinfo : {"appid":"wx9428227f6e4cf4c2","noncestr":"ZkJnwvwJ9REclqAVCa4WN2uOqWby34pV",
     * "package":"Sign=WXPay","partnerid":"1496536982","prepayid":null,
     * "sign":"20318BD7660095CDD45CC5DD25F3BCFC","timestamp":1515736330485}
     */

    private OrderinfoBean orderinfo;

    public OrderinfoBean getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(OrderinfoBean orderinfo) {
        this.orderinfo = orderinfo;
    }

    public static class OrderinfoBean {
        /**
         * appid : wx9428227f6e4cf4c2
         * noncestr : ZkJnwvwJ9REclqAVCa4WN2uOqWby34pV
         * package : Sign=WXPay
         * partnerid : 1496536982
         * prepayid : null
         * sign : 20318BD7660095CDD45CC5DD25F3BCFC
         * timestamp : 1515736330485
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private Object prepayid;
        private String sign;
        private long timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public Object getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(Object prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}
