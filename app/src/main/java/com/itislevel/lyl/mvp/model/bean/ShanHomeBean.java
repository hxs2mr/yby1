package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\4 0004 14:57
 */
public class ShanHomeBean {

    /**
     * balance : 12551.00
     * pageBean : {"list":[{"balance":"12551.00","periodendtime":"2019-01-06","periods":6,"tradeid":3,"status":"0","periodlimit":"256.00","shopname":"德良方保健食品","tradenum":"20180703010330018552300","periodstarttime":"2018-08-06","perperiodlimit":"42.67","merchantid":1}],"allRow":0,"totalPage":0,"currentPage":1,"pageSize":10,"pageIndex":{"startindex":1,"endindex":0}}
     */
    private String balance;
    private PageBeanBean pageBean;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public PageBeanBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBeanBean pageBean) {
        this.pageBean = pageBean;
    }

    public static class PageBeanBean {
        /**
         * list : [{"balance":"12551.00","periodendtime":"2019-01-06","periods":6,"tradeid":3,"status":"0","periodlimit":"256.00","shopname":"德良方保健食品","tradenum":"20180703010330018552300","periodstarttime":"2018-08-06","perperiodlimit":"42.67","merchantid":1}]
         * allRow : 0
         * totalPage : 0
         * currentPage : 1
         * pageSize : 10
         * pageIndex : {"startindex":1,"endindex":0}
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
             * endindex : 0
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
             * balance : 12551.00
             * periodendtime : 2019-01-06
             * periods : 6
             * tradeid : 3
             * status : 0
             * periodlimit : 256.00
             * shopname : 德良方保健食品
             * tradenum : 20180703010330018552300
             * periodstarttime : 2018-08-06
             * perperiodlimit : 42.67
             * merchantid : 1
             */

            private String balance;
            private String periodendtime;
            private int periods;
            private int tradeid;
            private String status;
            private String periodlimit;
            private String shopname;
            private String tradenum;
            private String periodstarttime;
            private String perperiodlimit;
            private int merchantid;

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPeriodlimit() {
                return periodlimit;
            }

            public void setPeriodlimit(String periodlimit) {
                this.periodlimit = periodlimit;
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

            public int getMerchantid() {
                return merchantid;
            }

            public void setMerchantid(int merchantid) {
                this.merchantid = merchantid;
            }
        }
    }
}
