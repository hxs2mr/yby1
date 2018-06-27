package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-20.10:21
 * path:com.itislevel.lyl.mvp.model.bean.SpecialTypeBean
 **/
public class SpecialOrderDetailBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements MultiItemEntity {
        /**
         * ordernum : 20180124010070010462107
         * buyuserid : 7
         * sellerid : 0
         * status : 101
         * iscomment : 0
         * trademode : 1
         * isrefund : 0
         * goodsbody : null
         * mailfee : null
         * payfee : null
         * ispay : 0
         * isdel : 0
         * issettle : 0
         * paytime : null
         * sendouttime : null
         * receivetime : null
         * refundtime : null
         * settletime : null
         * finishtime : null
         * goodsid : 9
         * price : 0.01
         * count : 5
         * goodsname : 毛巾
         * imgurl : 7355D8C2-9F7C-40E9-AF30-B3AB8AE1BC4A.jpg
         * realname : 周末
         * phone : 13555555555
         * receiveaddress : 贵州省贵阳市市辖区花果园白宫
         * createdtime : 1516762013000
         * orderid
         */

        private String ordernum;
        private int buyuserid;
        private int sellerid;
        private String status;
        private String iscomment;
        private String trademode;
        private String isrefund;
        private Object goodsbody;
        private Object mailfee;
        private Object payfee;
        private String ispay;
        private String isdel;
        private String issettle;
        private Object paytime;
        private Object sendouttime;
        private Object receivetime;
        private Object refundtime;
        private Object settletime;
        private Object finishtime;
        private int goodsid;
        private String price;
        private int count;
        private String goodsname;
        private String imgurl;
        private String realname;
        private String phone;
        private String receiveaddress;
        private long createdtime;

        private int itemType;

        private String orderid;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }




        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public int getBuyuserid() {
            return buyuserid;
        }

        public void setBuyuserid(int buyuserid) {
            this.buyuserid = buyuserid;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIscomment() {
            return iscomment;
        }

        public void setIscomment(String iscomment) {
            this.iscomment = iscomment;
        }

        public String getTrademode() {
            return trademode;
        }

        public void setTrademode(String trademode) {
            this.trademode = trademode;
        }

        public String getIsrefund() {
            return isrefund;
        }

        public void setIsrefund(String isrefund) {
            this.isrefund = isrefund;
        }

        public Object getGoodsbody() {
            return goodsbody;
        }

        public void setGoodsbody(Object goodsbody) {
            this.goodsbody = goodsbody;
        }

        public Object getMailfee() {
            return mailfee;
        }

        public void setMailfee(Object mailfee) {
            this.mailfee = mailfee;
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

        public String getIssettle() {
            return issettle;
        }

        public void setIssettle(String issettle) {
            this.issettle = issettle;
        }

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
            this.paytime = paytime;
        }

        public Object getSendouttime() {
            return sendouttime;
        }

        public void setSendouttime(Object sendouttime) {
            this.sendouttime = sendouttime;
        }

        public Object getReceivetime() {
            return receivetime;
        }

        public void setReceivetime(Object receivetime) {
            this.receivetime = receivetime;
        }

        public Object getRefundtime() {
            return refundtime;
        }

        public void setRefundtime(Object refundtime) {
            this.refundtime = refundtime;
        }

        public Object getSettletime() {
            return settletime;
        }

        public void setSettletime(Object settletime) {
            this.settletime = settletime;
        }

        public Object getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(Object finishtime) {
            this.finishtime = finishtime;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
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

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReceiveaddress() {
            return receiveaddress;
        }

        public void setReceiveaddress(String receiveaddress) {
            this.receiveaddress = receiveaddress;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }
    }
}
