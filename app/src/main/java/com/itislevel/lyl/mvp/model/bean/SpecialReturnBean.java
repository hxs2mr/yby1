package com.itislevel.lyl.mvp.model.bean;

import java.util.Date;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-25.16:33
 * path:com.itislevel.lyl.mvp.model.bean.SpecialReturnBean
 **/
public class SpecialReturnBean {

    /**
     * list : [{"ordernum":"20180124010020017006002","buyuserid":1,"goodsid":4,"count":0,
     * "goodsname":"花露水","imgurl":null,"refundfee":null,"applytime":"2018-01-25 16:22:34.0",
     * "applyrem":null,"orderid":"78","price":"0.01",
     * "logourl":"3FBF3FEC-8D27-43AD-B428-5CC41CAB2B13.png","attribute":null,
     * "imge":"9E59126A-1B22-431F-A7E9-62CC2E29D61D.png, 5B6F5C0A-6A89-4480-AF63-AC0DBC994F6D
     * .png","refundreason":null,"refundexplain":null,"operator":null,"reftype":"203",
     * "status":"201","checkrem":null,"agreerem":null,"refundrem":null,"agreeapplytime":null,
     * "refuseapplytime":null,"agreetime":null,"finishtime":null}]
     * allRow : 1
     * totalPage : 1
     * currentPage : 1
     * pageSize : 20
     * pageIndex : {"startindex":1,"endindex":1}
     */

    private int allRow;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    private PageIndexBean pageIndex;
    private List<ListBean> list;

    public int getAllRow() {
        return allRow;
    }

    public void setAllRow(int allRow) {
        this.allRow = allRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageIndexBean getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(PageIndexBean pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class PageIndexBean {
        /**
         * startindex : 1
         * endindex : 1
         */

        private int startindex;
        private int endindex;

        public int getStartindex() {
            return startindex;
        }

        public void setStartindex(int startindex) {
            this.startindex = startindex;
        }

        public int getEndindex() {
            return endindex;
        }

        public void setEndindex(int endindex) {
            this.endindex = endindex;
        }
    }

    public static class ListBean {
        /**
         * ordernum : 20180124010020017006002
         * buyuserid : 1
         * goodsid : 4
         * count : 0
         * goodsname : 花露水
         * imgurl : null
         * refundfee : null
         * applytime : 2018-01-25 16:22:34.0
         * applyrem : null
         * orderid : 78
         * price : 0.01
         * logourl : 3FBF3FEC-8D27-43AD-B428-5CC41CAB2B13.png
         * attribute : null
         * imge : 9E59126A-1B22-431F-A7E9-62CC2E29D61D.png, 5B6F5C0A-6A89-4480-AF63-AC0DBC994F6D.png
         * refundreason : null
         * refundexplain : null
         * operator : null
         * reftype : 203
         * status : 201
         * checkrem : null
         * agreerem : null
         * refundrem : null
         * agreeapplytime : null
         * refuseapplytime : null
         * agreetime : null
         * finishtime : null
         */

        private String ordernum;
        private int buyuserid;
        private int goodsid;
        private int count;
        private String goodsname;
        private Object imgurl;
        private Object refundfee;
        private long applytime;
        private Object applyrem;
        private String orderid;
        private String price;
        private String logourl;
        private Object attribute;
        private String imge;
        private Object refundreason;
        private Object refundexplain;
        private Object operator;
        private String reftype;
        private String status;
        private Object checkrem;
        private Object agreerem;
        private Object refundrem;
        private Object agreeapplytime;
        private Object refuseapplytime;
        private Object agreetime;
        private Object finishtime;

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

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
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

        public Object getImgurl() {
            return imgurl;
        }

        public void setImgurl(Object imgurl) {
            this.imgurl = imgurl;
        }

        public Object getRefundfee() {
            return refundfee;
        }

        public void setRefundfee(Object refundfee) {
            this.refundfee = refundfee;
        }

        public long getApplytime() {
            return applytime;
        }

        public void setApplytime(long applytime) {
            this.applytime = applytime;
        }

        public Object getApplyrem() {
            return applyrem;
        }

        public void setApplyrem(Object applyrem) {
            this.applyrem = applyrem;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLogourl() {
            return logourl;
        }

        public void setLogourl(String logourl) {
            this.logourl = logourl;
        }

        public Object getAttribute() {
            return attribute;
        }

        public void setAttribute(Object attribute) {
            this.attribute = attribute;
        }

        public String getImge() {
            return imge;
        }

        public void setImge(String imge) {
            this.imge = imge;
        }

        public Object getRefundreason() {
            return refundreason;
        }

        public void setRefundreason(Object refundreason) {
            this.refundreason = refundreason;
        }

        public Object getRefundexplain() {
            return refundexplain;
        }

        public void setRefundexplain(Object refundexplain) {
            this.refundexplain = refundexplain;
        }

        public Object getOperator() {
            return operator;
        }

        public void setOperator(Object operator) {
            this.operator = operator;
        }

        public String getReftype() {
            return reftype;
        }

        public void setReftype(String reftype) {
            this.reftype = reftype;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getCheckrem() {
            return checkrem;
        }

        public void setCheckrem(Object checkrem) {
            this.checkrem = checkrem;
        }

        public Object getAgreerem() {
            return agreerem;
        }

        public void setAgreerem(Object agreerem) {
            this.agreerem = agreerem;
        }

        public Object getRefundrem() {
            return refundrem;
        }

        public void setRefundrem(Object refundrem) {
            this.refundrem = refundrem;
        }

        public Object getAgreeapplytime() {
            return agreeapplytime;
        }

        public void setAgreeapplytime(Object agreeapplytime) {
            this.agreeapplytime = agreeapplytime;
        }

        public Object getRefuseapplytime() {
            return refuseapplytime;
        }

        public void setRefuseapplytime(Object refuseapplytime) {
            this.refuseapplytime = refuseapplytime;
        }

        public Object getAgreetime() {
            return agreetime;
        }

        public void setAgreetime(Object agreetime) {
            this.agreetime = agreetime;
        }

        public Object getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(Object finishtime) {
            this.finishtime = finishtime;
        }
    }
}
