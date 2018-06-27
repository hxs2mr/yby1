package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-10.10:20
 * path:com.itislevel.lyl.mvp.model.bean.PayALiResultBean
 **/
public class PayALiResultBean {
    /**
     * alipay_trade_app_pay_response : {"code":"10000","msg":"Success",
     * "app_id":"2017122801294982","auth_app_id":"2017122801294982","charset":"utf-8",
     * "timestamp":"2018-01-10 10:18:33","total_amount":"0.01",
     * "trade_no":"2018011021001004220573227020","seller_id":"2088921009367845",
     * "out_trade_no":"20180110010840010181284"}
     * sign : HJ+koJXR9NfNgGIs+giZXLhjNp6sEmluzBsneh/dcLd4qNabtUtcV
     * /ttyy96P2VnX1CpBs5f827dBvMeDbqLY2PnBWKu2zXIBnydCn8QLKtOVhE7W4iALZwm+S5XpPbl7qJYM
     * /Qn9uX4Nnk2ApaCKWjhbQR/obQYFjdHmLbaxwPkICGulnO5ojZbZ1Zf53YGEqPZjeLP6orK2o16XYZ4loT8YQ5q
     * +EQCuk2FufJf2nxkCC5SSv8hiIVVpUl4/0GFWA59L5bsizccR/lyUIa
     * /fbWjO4ZRQWCEu8bKV7TTF5hlRzJFJ0AT8GD1rC5yadxN4VW26jntgK358lr1kGHeGA==
     * sign_type : RSA2
     */

    private AlipayTradeAppPayResponseBean alipay_trade_app_pay_response;
    private String sign;
    private String sign_type;

    public AlipayTradeAppPayResponseBean getAlipay_trade_app_pay_response() {
        return alipay_trade_app_pay_response;
    }

    public void setAlipay_trade_app_pay_response(AlipayTradeAppPayResponseBean
                                                         alipay_trade_app_pay_response) {
        this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public static class AlipayTradeAppPayResponseBean {
        /**
         * code : 10000
         * msg : Success
         * app_id : 2017122801294982
         * auth_app_id : 2017122801294982
         * charset : utf-8
         * timestamp : 2018-01-10 10:18:33
         * total_amount : 0.01
         * trade_no : 2018011021001004220573227020
         * seller_id : 2088921009367845
         * out_trade_no : 20180110010840010181284
         */

        private String code;
        private String msg;
        private String app_id;
        private String auth_app_id;
        private String charset;
        private String timestamp;
        private String total_amount;
        private String trade_no;
        private String seller_id;
        private String out_trade_no;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getAuth_app_id() {
            return auth_app_id;
        }

        public void setAuth_app_id(String auth_app_id) {
            this.auth_app_id = auth_app_id;
        }

        public String getCharset() {
            return charset;
        }

        public void setCharset(String charset) {
            this.charset = charset;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }
    }
}
