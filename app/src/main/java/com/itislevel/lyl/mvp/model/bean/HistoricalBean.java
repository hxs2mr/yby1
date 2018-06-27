package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\20 0020.
 */

public class HistoricalBean {

    /**
     * list : [{"billid":37,"usernum":null,"userid":0,"managerid":0,"vid":null,"provinceid":0,"cityid":0,"countyid":0,"username":"勾昌俊","phone":"18302562524","status":1,"type":1,"paytype":1,"paymethod":"0","trademode":"2","payfee":"0.10","remark":null,"payfeebegintime":"2018-06-19","payfeefinishtime":"2018-07-19","payfeetime":null,"createdtime":null},{"billid":38,"usernum":null,"userid":0,"managerid":0,"vid":null,"provinceid":0,"cityid":0,"countyid":0,"username":"勾昌俊","phone":"18302562524","status":1,"type":2,"paytype":1,"paymethod":"0","trademode":"2","payfee":"0.08","remark":null,"payfeebegintime":"2018-06-19","payfeefinishtime":"2018-07-19","payfeetime":null,"createdtime":null}]
     * allRow : 2
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
         * billid : 37
         * usernum : null
         * userid : 0
         * managerid : 0
         * vid : null
         * provinceid : 0
         * cityid : 0
         * countyid : 0
         * username : 勾昌俊
         * phone : 18302562524
         * status : 1
         * type : 1
         * paytype : 1
         * paymethod : 0
         * trademode : 2
         * payfee : 0.10
         * remark : null
         * payfeebegintime : 2018-06-19
         * payfeefinishtime : 2018-07-19
         * payfeetime : null
         * createdtime : null
         */

        private int billid;
        private Object usernum;
        private int userid;
        private int managerid;
        private Object vid;
        private int provinceid;
        private int cityid;
        private int countyid;
        private String username;
        private String phone;
        private int status;
        private int type;
        private int paytype;
        private String paymethod;
        private String trademode;
        private String payfee;
        private Object remark;
        private String payfeebegintime;
        private String payfeefinishtime;
        private Object payfeetime;
        private Object createdtime;

        public int getBillid() {
            return billid;
        }

        public void setBillid(int billid) {
            this.billid = billid;
        }

        public Object getUsernum() {
            return usernum;
        }

        public void setUsernum(Object usernum) {
            this.usernum = usernum;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getManagerid() {
            return managerid;
        }

        public void setManagerid(int managerid) {
            this.managerid = managerid;
        }

        public Object getVid() {
            return vid;
        }

        public void setVid(Object vid) {
            this.vid = vid;
        }

        public int getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(int provinceid) {
            this.provinceid = provinceid;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public int getCountyid() {
            return countyid;
        }

        public void setCountyid(int countyid) {
            this.countyid = countyid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public String getPaymethod() {
            return paymethod;
        }

        public void setPaymethod(String paymethod) {
            this.paymethod = paymethod;
        }

        public String getTrademode() {
            return trademode;
        }

        public void setTrademode(String trademode) {
            this.trademode = trademode;
        }

        public String getPayfee() {
            return payfee;
        }

        public void setPayfee(String payfee) {
            this.payfee = payfee;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getPayfeebegintime() {
            return payfeebegintime;
        }

        public void setPayfeebegintime(String payfeebegintime) {
            this.payfeebegintime = payfeebegintime;
        }

        public String getPayfeefinishtime() {
            return payfeefinishtime;
        }

        public void setPayfeefinishtime(String payfeefinishtime) {
            this.payfeefinishtime = payfeefinishtime;
        }

        public Object getPayfeetime() {
            return payfeetime;
        }

        public void setPayfeetime(Object payfeetime) {
            this.payfeetime = payfeetime;
        }

        public Object getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(Object createdtime) {
            this.createdtime = createdtime;
        }
    }
}
