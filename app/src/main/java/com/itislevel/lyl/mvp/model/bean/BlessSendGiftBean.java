package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-03.17:11
 * path:com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean
 **/
public class BlessSendGiftBean {


    /**
     * list : [{"ordernum":null,"moduleid":0,"modelename":null,"buyuserid":1,"receiveuserid":1,
     * "payfee":null,"ispay":"0","isdel":"0","goodsbody":null,"paytime":1515060964000,
     * "createdtime":1515067241402,"nickname":"itisi","tonickname":null,
     * "imgurl":"BBE831F7-EF42-4152-ADC5-A1050E2CA6E0.png","count":1},{"ordernum":null,
     * "moduleid":0,"modelename":null,"buyuserid":1,"receiveuserid":1,"payfee":null,"ispay":"0",
     * "isdel":"0","goodsbody":null,"paytime":1515060964000,"createdtime":1515067241406,
     * "nickname":"itisi","tonickname":null,"imgurl":"6C8E7719-AF9E-43BC-97E2-38758213763C.png",
     * "count":1},{"ordernum":null,"moduleid":0,"modelename":null,"buyuserid":1,
     * "receiveuserid":1,"payfee":null,"ispay":"0","isdel":"0","goodsbody":null,
     * "paytime":1515060964000,"createdtime":1515067241407,"nickname":"itisi","tonickname":null,
     * "imgurl":"534067FC-8D2A-46FB-A8A0-CB6A68B094C7.png","count":2},{"ordernum":null,
     * "moduleid":0,"modelename":null,"buyuserid":5,"receiveuserid":1,"payfee":null,"ispay":"0",
     * "isdel":"0","goodsbody":null,"paytime":1515060961000,"createdtime":1515067241407,
     * "nickname":"tomcat","tonickname":null,"imgurl":"BBE831F7-EF42-4152-ADC5-A1050E2CA6E0.png",
     * "count":1},{"ordernum":null,"moduleid":0,"modelename":null,"buyuserid":5,
     * "receiveuserid":1,"payfee":null,"ispay":"0","isdel":"0","goodsbody":null,
     * "paytime":1515060961000,"createdtime":1515067241407,"nickname":"tomcat","tonickname":null,
     * "imgurl":"6C8E7719-AF9E-43BC-97E2-38758213763C.png","count":1},{"ordernum":null,
     * "moduleid":0,"modelename":null,"buyuserid":5,"receiveuserid":1,"payfee":null,"ispay":"0",
     * "isdel":"0","goodsbody":null,"paytime":1515060961000,"createdtime":1515067241407,
     * "nickname":"tomcat","tonickname":null,"imgurl":"534067FC-8D2A-46FB-A8A0-CB6A68B094C7.png",
     * "count":2}]
     * allRow : 3
     * totalPage : 1
     * currentPage : 1
     * pageSize : 10
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
         * ordernum : null
         * moduleid : 0
         * modelename : null
         * buyuserid : 1
         * receiveuserid : 1
         * payfee : null
         * ispay : 0
         * isdel : 0
         * goodsbody : null
         * paytime : 1515060964000
         * createdtime : 1515067241402
         * nickname : itisi
         * tonickname : null
         * imgurl : BBE831F7-EF42-4152-ADC5-A1050E2CA6E0.png
         * count : 1
         */

        private String ordernum;
        private int moduleid;
        private String modelename;
        private int buyuserid;
        private int receiveuserid;
        private Object payfee;
        private String ispay;
        private String isdel;
        private String goodsbody;
        private long paytime;
        private long createdtime;
        private String nickname;
        private String tonickname;
        private String imgurl;
        private int count;
        private String goodsname;


        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public int getModuleid() {
            return moduleid;
        }

        public void setModuleid(int moduleid) {
            this.moduleid = moduleid;
        }

        public String getModelename() {
            return modelename;
        }

        public void setModelename(String modelename) {
            this.modelename = modelename;
        }

        public int getBuyuserid() {
            return buyuserid;
        }

        public void setBuyuserid(int buyuserid) {
            this.buyuserid = buyuserid;
        }

        public int getReceiveuserid() {
            return receiveuserid;
        }

        public void setReceiveuserid(int receiveuserid) {
            this.receiveuserid = receiveuserid;
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

        public String getGoodsbody() {
            return goodsbody;
        }

        public void setGoodsbody(String goodsbody) {
            this.goodsbody = goodsbody;
        }

        public long getPaytime() {
            return paytime;
        }

        public void setPaytime(long paytime) {
            this.paytime = paytime;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTonickname() {
            return tonickname;
        }

        public void setTonickname(String tonickname) {
            this.tonickname = tonickname;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
