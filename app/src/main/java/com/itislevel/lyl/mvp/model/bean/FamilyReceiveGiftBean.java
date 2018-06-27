package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:收到的礼物
 * user:itisi
 * date:2017/12/28.14:02
 * path:com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean
 **/
public class FamilyReceiveGiftBean {

    /**
     * list : [{"ordernum":"20171226010680017074768","moduleid":4,"modelename":"祭祀",
     * "buyuserid":5,"receiveuserid":3,"payfee":"655.0","ispay":"1","isdel":"0",
     * "paytime":1514279317000,"createdtime":null,"nickname":"tomcat","goodsname":"19",
     * "imgurl":"11","deadname":"张三","count":"6"},{"ordernum":"20171226010680017074768",
     * "moduleid":4,"modelename":"祭祀","buyuserid":5,"receiveuserid":3,"payfee":"655.0",
     * "ispay":"1","isdel":"0","paytime":1514279317000,"createdtime":null,"nickname":"tomcat",
     * "goodsname":"12","imgurl":"11","deadname":"张三","count":"5"}]
     * allRow : 6
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

    public int getAllRow()  {
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
         * ordernum : 20171226010680017074768
         * moduleid : 4
         * modelename : 祭祀
         * buyuserid : 5
         * receiveuserid : 3
         * payfee : 655.0
         * ispay : 1
         * isdel : 0
         * paytime : 1514279317000
         * createdtime : null
         * nickname : tomcat
         * goodsname : 19
         * imgurl : 11
         * deadname : 张三
         * count : 6
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
        private String buyusername;


        public ListBean(String buyusername, String goodsname, String imgurl,    long createdtime) {
            this.buyusername = buyusername;
            this.goodsname = goodsname;
            this.imgurl = imgurl;
            this.createdtime = createdtime;
        }

        public String getBuyusername() {
            return buyusername;
        }

        public void setBuyusername(String buyusername) {
            this.buyusername = buyusername;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
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
