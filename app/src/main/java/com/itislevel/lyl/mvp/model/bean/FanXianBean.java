package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\4 0004 15:33
 */
public class FanXianBean {

    /**
     * list : [{"status":"3","periodid":1,"tradenum":"2018020509120123654","perperiodlimit":"80","cashbackdate":"2018-08-01"}]
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
         * status : 3
         * periodid : 1
         * tradenum : 2018020509120123654
         * perperiodlimit : 80
         * cashbackdate : 2018-08-01
         */

        private String status;
        private int periodid;
        private String tradenum;
        private String perperiodlimit;
        private String cashbackdate;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getPeriodid() {
            return periodid;
        }

        public void setPeriodid(int periodid) {
            this.periodid = periodid;
        }

        public String getTradenum() {
            return tradenum;
        }

        public void setTradenum(String tradenum) {
            this.tradenum = tradenum;
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
    }
}
