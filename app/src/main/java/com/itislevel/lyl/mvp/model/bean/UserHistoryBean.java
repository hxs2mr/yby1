package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\7 0007 12:13
 */
public class UserHistoryBean {

    /**
     * list : [{"gettime":1530866567000,"tradeid":12,"status":"2","createdtime":1530786681000,"tradersname":"莫大庸","cashbacklastdate":"2019-03-16","periodid":99,"shopname":"德良方保健食品","tradenum":"20180703010330018552311","tradersphone":"18823852027","perperiodlimit":"21.33","cashbackdate":"2019-03-06","merchantid":1},{"gettime":1530866567000,"tradeid":12,"status":"3","createdtime":1530786681000,"tradersname":"莫大庸","cashbacklastdate":"2019-04-16","periodid":100,"shopname":"德良方保健食品","tradenum":"20180703010330018552311","tradersphone":"18823852027","perperiodlimit":"21.33","cashbackdate":"2019-04-06","merchantid":1},{"gettime":1530866567000,"tradeid":12,"status":"3","createdtime":1530786681000,"tradersname":"莫大庸","cashbacklastdate":"2019-05-16","periodid":101,"shopname":"德良方保健食品","tradenum":"20180703010330018552311","tradersphone":"18823852027","perperiodlimit":"21.33","cashbackdate":"2019-05-06","merchantid":1},{"gettime":1530866567000,"tradeid":12,"status":"2","createdtime":1530786681000,"tradersname":"莫大庸","cashbacklastdate":"2019-06-16","periodid":102,"shopname":"德良方保健食品","tradenum":"20180703010330018552311","tradersphone":"18823852027","perperiodlimit":"21.33","cashbackdate":"2019-06-06","merchantid":1},{"gettime":1530866567000,"tradeid":12,"status":"2","createdtime":1530786681000,"tradersname":"莫大庸","cashbacklastdate":"2019-07-16","periodid":103,"shopname":"德良方保健食品","tradenum":"20180703010330018552311","tradersphone":"18823852027","perperiodlimit":"21.33","cashbackdate":"2019-07-06","merchantid":1}]
     * allRow : 5
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
         * gettime : 1530866567000
         * tradeid : 12
         * status : 2
         * createdtime : 1530786681000
         * tradersname : 莫大庸
         * cashbacklastdate : 2019-03-16
         * periodid : 99
         * shopname : 德良方保健食品
         * tradenum : 20180703010330018552311
         * tradersphone : 18823852027
         * perperiodlimit : 21.33
         * cashbackdate : 2019-03-06
         * merchantid : 1
         */

        private long gettime;
        private int tradeid;
        private String status;
        private long createdtime;
        private String tradersname;
        private String cashbacklastdate;
        private int periodid;
        private String shopname;
        private String tradenum;
        private String tradersphone;
        private String perperiodlimit;
        private String cashbackdate;
        private int merchantid;

        public long getGettime() {
            return gettime;
        }

        public void setGettime(long gettime) {
            this.gettime = gettime;
        }

        public int getTradeid() {
            return tradeid;
        }

        public void setTradeid(int tradeid) {
            this.tradeid = tradeid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getCashbacklastdate() {
            return cashbacklastdate;
        }

        public void setCashbacklastdate(String cashbacklastdate) {
            this.cashbacklastdate = cashbacklastdate;
        }

        public int getPeriodid() {
            return periodid;
        }

        public void setPeriodid(int periodid) {
            this.periodid = periodid;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
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

        public String getPerperiodlimit() {
            return perperiodlimit;
        }

        public void setPerperiodlimit(String perperiodlimit) {
            this.perperiodlimit = perperiodlimit;
        }

        public String getCashbackdate() {
            return cashbackdate;
        }

        public void setCashbackdate(String cashbackdate) {
            this.cashbackdate = cashbackdate;
        }

        public int getMerchantid() {
            return merchantid;
        }

        public void setMerchantid(int merchantid) {
            this.merchantid = merchantid;
        }
    }
}
