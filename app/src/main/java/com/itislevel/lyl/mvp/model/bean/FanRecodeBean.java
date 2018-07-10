package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\4 0004 15:23
 */
public class FanRecodeBean {

    /**
     * list : [{"status":"1","createdtime":1530614510000,"ordernum":"20180703010360018415136","trademode":"1","paymethod":"3","payfeetime":1530614510000,"payfee":"0.10","merchantid":1},{"status":"1","createdtime":1530614958000,"ordernum":"20180703010400018491940","trademode":"1","paymethod":"3","payfeetime":1530614958000,"payfee":"0.50","merchantid":1},{"status":"1","createdtime":1530614981000,"ordernum":"20180703010850018494285","trademode":"1","paymethod":"3","payfeetime":1530614981000,"payfee":"100.01","merchantid":1},{"status":"1","createdtime":1530614995000,"ordernum":"20180703010850018495685","trademode":"1","paymethod":"3","payfeetime":1530614995000,"payfee":"9900.00","merchantid":1},{"status":"1","createdtime":1530672339000,"ordernum":"20180704010840010454084","trademode":"1","paymethod":"3","payfeetime":1530672339000,"payfee":"0.39","merchantid":1},{"status":"1","createdtime":1530672328000,"ordernum":"20180704010990010452999","trademode":"1","paymethod":"3","payfeetime":1530672328000,"payfee":"2000.00","merchantid":1}]
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
         * status : 1
         * createdtime : 1530614510000
         * ordernum : 20180703010360018415136
         * trademode : 1
         * paymethod : 3
         * payfeetime : 1530614510000
         * payfee : 0.10
         * merchantid : 1
         */

        private String status;
        private long createdtime;
        private String ordernum;
        private String trademode;
        private String paymethod;
        private long payfeetime;
        private String payfee;
        private int merchantid;

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

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getTrademode() {
            return trademode;
        }

        public void setTrademode(String trademode) {
            this.trademode = trademode;
        }

        public String getPaymethod() {
            return paymethod;
        }

        public void setPaymethod(String paymethod) {
            this.paymethod = paymethod;
        }

        public long getPayfeetime() {
            return payfeetime;
        }

        public void setPayfeetime(long payfeetime) {
            this.payfeetime = payfeetime;
        }

        public String getPayfee() {
            return payfee;
        }

        public void setPayfee(String payfee) {
            this.payfee = payfee;
        }

        public int getMerchantid() {
            return merchantid;
        }

        public void setMerchantid(int merchantid) {
            this.merchantid = merchantid;
        }
    }
}
