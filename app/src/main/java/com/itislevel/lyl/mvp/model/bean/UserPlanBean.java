package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\7 0007 10:38
 */
public class UserPlanBean {

    /**
     * list : [{"periodendtime":"2019-07-06","periods":12,"tradeid":12,"periodlimit":"256.00","status":"0","shopname":"德良方保健食品","companyname":"贵州德良方保健食品有限公司","merchantid":1,"goodsname":"柳条姐X1,苗黄金X1","createdtime":1530786681000,"tradersname":"莫大庸","tradenum":"20180703010330018552311","tradersphone":"18823852027","periodstarttime":"2018-08-06","perperiodlimit":"21.33"}]
     * allRow : 1
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
         * periodendtime : 2019-07-06
         * periods : 12
         * tradeid : 12
         * periodlimit : 256.00
         * status : 0
         * shopname : 德良方保健食品
         * companyname : 贵州德良方保健食品有限公司
         * merchantid : 1
         * goodsname : 柳条姐X1,苗黄金X1
         * createdtime : 1530786681000
         * tradersname : 莫大庸
         * tradenum : 20180703010330018552311
         * tradersphone : 18823852027
         * periodstarttime : 2018-08-06
         * perperiodlimit : 21.33
         */

        private String periodendtime;
        private int periods;
        private int tradeid;
        private String periodlimit;
        private String status;
        private String shopname;
        private String companyname;
        private int merchantid;
        private String goodsname;
        private long createdtime;
        private String tradersname;
        private String tradenum;
        private String tradersphone;
        private String periodstarttime;
        private String perperiodlimit;

        public String getPeriodendtime() {
            return periodendtime;
        }

        public void setPeriodendtime(String periodendtime) {
            this.periodendtime = periodendtime;
        }

        public int getPeriods() {
            return periods;
        }

        public void setPeriods(int periods) {
            this.periods = periods;
        }

        public int getTradeid() {
            return tradeid;
        }

        public void setTradeid(int tradeid) {
            this.tradeid = tradeid;
        }

        public String getPeriodlimit() {
            return periodlimit;
        }

        public void setPeriodlimit(String periodlimit) {
            this.periodlimit = periodlimit;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public int getMerchantid() {
            return merchantid;
        }

        public void setMerchantid(int merchantid) {
            this.merchantid = merchantid;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public String getTradersname() {
            return tradersname;
        }

        public void setTradersname(String tradersname) {
            this.tradersname = tradersname;
        }

        public String getTradenum() {
            return tradenum;
        }

        public void setTradenum(String tradenum) {
            this.tradenum = tradenum;
        }

        public String getTradersphone() {
            return tradersphone;
        }

        public void setTradersphone(String tradersphone) {
            this.tradersphone = tradersphone;
        }

        public String getPeriodstarttime() {
            return periodstarttime;
        }

        public void setPeriodstarttime(String periodstarttime) {
            this.periodstarttime = periodstarttime;
        }

        public String getPerperiodlimit() {
            return perperiodlimit;
        }

        public void setPerperiodlimit(String perperiodlimit) {
            this.perperiodlimit = perperiodlimit;
        }
    }
}
