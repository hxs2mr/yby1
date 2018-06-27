package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017-12-29.11:29
 * path:com.itislevel.lyl.mvp.model.bean.AliPayBean
 **/
public class AliPayBean {


    /**
     * order : alipay_sdk=alipayTest-sdk-java-dynamicVersionNo&app_id=2017122801294982&biz_content
     * =%7B%22body%22%3A%22%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A
     * %2220171226010680017074768%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C
     * %22subject%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A
     * %2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipayTest
     * .trade.app.pay&notify_url=%E5%BC%82%E6%AD%A5%E9%80%9A%E7%9F%A5%E5%9C%B0%E5%9D%80&sign
     * =iRPeWSXKcrIk3j3qgV6fQ8hDEgvO6pDp7esx%2BLPkVrdHzxPxDcsnMPVY
     * %2B8HmOv3qa5RQWNjR24Rpc6MMRmEpxYfG9%2FKD73O37XUjaIHaAgJj590F
     * %2Fk4cKTDSLEAN0slxodp9NbOPC3zUUCY6WOaJdUxAm8yuh0WgBJ2gWR8zcIQ2gnznVwB
     * %2FtDtUqKXzeVnibXfqRIaklYlvOszoJdSxYzp6pf7CYd1j5ZRNFRKP8IL%2FryL
     * %2BivvQKMmqKUVJ4Q2Y60l4Fw1rKlwbMLlgwcTRbIjdmJTrOfCAoWZk3JN
     * %2B6oLyICUGczZpQtwTsT9eXI8Le3qLYR793PGxLBoNlDhW4Q%3D%3D&sign_type=RSA2&timestamp=2017-12
     * -29+11%3A38%3A00&version=1.0
     */

    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
