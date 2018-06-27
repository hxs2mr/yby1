package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/28.11:58
 * path:com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean
 **/
public class FamilySendGiftRecordBean {


    /**
     * list : [{"ordernum":"20180104010090015202109","moduleid":11,"modelename":"fete",
     * "buyuserid":1,"receiveuserid":3,"payfee":"520.00","ispay":"1","isdel":"0",
     * "paytime":1515071508000,"createdtime":null,"nickname":"itisi","goodsname":"祭品测试1",
     * "imgurl":"534067FC-8D2A-46FB-A8A0-CB6A68B094C7.png","deadname":"冯提莫1,冯提莫2","count":"1"},
     * {"ordernum":"20180104010890020030889","moduleid":13,"modelename":"fete","buyuserid":1,
     * "receiveuserid":3,"payfee":"1076.00","ispay":"1","isdel":"0","paytime":1515067455000,
     * "createdtime":null,"nickname":"itisi","goodsname":"礼品测试2",
     * "imgurl":"5E9DF916-4739-4443-BB25-0454B8EC763D.png","deadname":"冯冯冯","count":"1"},
     * {"ordernum":"20180104010890020030889","moduleid":13,"modelename":"fete","buyuserid":1,
     * "receiveuserid":3,"payfee":"1076.00","ispay":"1","isdel":"0","paytime":1515067455000,
     * "createdtime":null,"nickname":"itisi","goodsname":"礼品","imgurl":"33.png","deadname":"冯冯冯",
     * "count":"1"},{"ordernum":"20180104010890020030889","moduleid":13,"modelename":"fete",
     * "buyuserid":1,"receiveuserid":3,"payfee":"1076.00","ispay":"1","isdel":"0",
     * "paytime":1515067455000,"createdtime":null,"nickname":"itisi","goodsname":"礼品测试3",
     * "imgurl":"22212.png","deadname":"冯冯冯","count":"1"},{"ordernum":"20180104010890020030889",
     * "moduleid":13,"modelename":"fete","buyuserid":1,"receiveuserid":3,"payfee":"1076.00",
     * "ispay":"1","isdel":"0","paytime":1515067455000,"createdtime":null,"nickname":"itisi",
     * "goodsname":"礼品测试","imgurl":"3C765446-EE51-4572-99DF-BC9DFBB883F6.png","deadname":"冯冯冯",
     * "count":"1"},{"ordernum":"20180104010150015326215","moduleid":11,"modelename":"fete",
     * "buyuserid":1,"receiveuserid":3,"payfee":"305.00","ispay":"1","isdel":"0",
     * "paytime":1515060958000,"createdtime":null,"nickname":"itisi","goodsname":"祭品测试2",
     * "imgurl":"0F0C3AB9-2A4A-4D7D-8CCA-FC104241DD48.png","deadname":"冯提莫1,冯提莫2","count":"1"},
     * {"ordernum":"20180104010150015326215","moduleid":11,"modelename":"fete","buyuserid":1,
     * "receiveuserid":3,"payfee":"305.00","ispay":"1","isdel":"0","paytime":1515060958000,
     * "createdtime":null,"nickname":"itisi","goodsname":"祭品测试9",
     * "imgurl":"4A195BF5-5DAE-4078-9184-AC2C4F2EC563.png","deadname":"冯提莫1,冯提莫2","count":"1"},
     * {"ordernum":"20180103010480018422748","moduleid":11,"modelename":"fete","buyuserid":1,
     * "receiveuserid":3,"payfee":"825.00","ispay":"1","isdel":"0","paytime":1515060954000,
     * "createdtime":null,"nickname":"itisi","goodsname":"祭品测试2",
     * "imgurl":"0F0C3AB9-2A4A-4D7D-8CCA-FC104241DD48.png","deadname":"冯提莫1,冯提莫2","count":"1"},
     * {"ordernum":"20180103010480018422748","moduleid":11,"modelename":"fete","buyuserid":1,
     * "receiveuserid":3,"payfee":"825.00","ispay":"1","isdel":"0","paytime":1515060954000,
     * "createdtime":null,"nickname":"itisi","goodsname":"祭品测试9",
     * "imgurl":"4A195BF5-5DAE-4078-9184-AC2C4F2EC563.png","deadname":"冯提莫1,冯提莫2","count":"1"},
     * {"ordernum":"20180103010480018422748","moduleid":11,"modelename":"fete","buyuserid":1,
     * "receiveuserid":3,"payfee":"825.00","ispay":"1","isdel":"0","paytime":1515060954000,
     * "createdtime":null,"nickname":"itisi","goodsname":"祭品测试1",
     * "imgurl":"534067FC-8D2A-46FB-A8A0-CB6A68B094C7.png","deadname":"冯提莫1,冯提莫2","count":"4"}]
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
         * ordernum : 20180104010090015202109
         * moduleid : 11
         * modelename : fete
         * buyuserid : 1
         * receiveuserid : 3
         * payfee : 520.00
         * ispay : 1
         * isdel : 0
         * paytime : 1515071508000
         * createdtime : null
         * nickname : itisi
         * goodsname : 祭品测试1
         * imgurl : 534067FC-8D2A-46FB-A8A0-CB6A68B094C7.png
         * deadname : 冯提莫1,冯提莫2
         * count : 1
         */

        private String ordernum;
        private int moduleid;
        private String modelename;
        private int buyuserid;
        private int receiveuserid;
        private String payfee;
        private String ispay;
        private String isdel;
        private long paytime;
        private long createdtime;
        private String nickname;
        private String goodsname;
        private String imgurl;
        private String deadname;
        private String count;

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

        public String getPayfee() {
            return payfee;
        }

        public void setPayfee(String payfee) {
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

        public String getDeadname() {
            return deadname;
        }

        public void setDeadname(String deadname) {
            this.deadname = deadname;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
