package com.itislevel.lyl.mvp.model.bean;

import java.util.Date;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-11.13:53
 * path:com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean
 **/
public class PropertyRecordBean {

    /**
     * pageBean : {"list":[{"ordernum":"20180119010560015570156","userid":1,
     * "usernum":"NO1516348597","username":"勾昌俊","phone":"18302562524","status":1,"type":1,
     * "paytype":3,"payfee":"0.01","estatearea":"恒达小区","payunit":"贵州友邦有网络科技",
     * "payfeetime":"2018-01-19 19:44:04","payfeebegintime":"2018-01-19",
     * "payfeefinishtime":"2018-04-19"},{"ordernum":"20180119010920015570292","userid":1,
     * "usernum":"NO1516348597","username":"勾昌俊","phone":"18302562524","status":1,"type":2,
     * "paytype":1,"payfee":"0.01","estatearea":"恒达小区","payunit":"贵州友邦有网络科技",
     * "payfeetime":"2018-01-19 21:26:33","payfeebegintime":"2018-01-19",
     * "payfeefinishtime":"2018-02-19"}],"allRow":2,"totalPage":1,"currentPage":1,"pageSize":10,
     * "pageIndex":{"startindex":1,"endindex":1}}
     */

    private PageBeanBean pageBean;

    public PageBeanBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBeanBean pageBean) {
        this.pageBean = pageBean;
    }

    public static class PageBeanBean {
        /**
         * list : [{"ordernum":"20180119010560015570156","userid":1,"usernum":"NO1516348597",
         * "username":"勾昌俊","phone":"18302562524","status":1,"type":1,"paytype":3,
         * "payfee":"0.01","estatearea":"恒达小区","payunit":"贵州友邦有网络科技","payfeetime":"2018-01-19
         * 19:44:04","payfeebegintime":"2018-01-19","payfeefinishtime":"2018-04-19"},
         * {"ordernum":"20180119010920015570292","userid":1,"usernum":"NO1516348597",
         * "username":"勾昌俊","phone":"18302562524","status":1,"type":2,"paytype":1,
         * "payfee":"0.01","estatearea":"恒达小区","payunit":"贵州友邦有网络科技","payfeetime":"2018-01-19
         * 21:26:33","payfeebegintime":"2018-01-19","payfeefinishtime":"2018-02-19"}]
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
             * ordernum : 20180119010560015570156
             * userid : 1
             * usernum : NO1516348597
             * username : 勾昌俊
             * phone : 18302562524
             * status : 1
             * type : 1
             * paytype : 3
             * payfee : 0.01
             * estatearea : 恒达小区
             * payunit : 贵州友邦有网络科技
             * payfeetime : 2018-01-19 19:44:04
             * payfeebegintime : 2018-01-19
             * payfeefinishtime : 2018-04-19
             */

            private String ordernum;
            private int userid;
            private String usernum;
            private String username;
            private String phone;
            private int status;
            private int type;
            private int paytype;
            private String payfee;
            private String estatearea;
            private String payunit;
            private String payfeetime;
            private Date payfeebegintime;
            private Date payfeefinishtime;

            public String getOrdernum() {
                return ordernum;
            }

            public void setOrdernum(String ordernum) {
                this.ordernum = ordernum;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getUsernum() {
                return usernum;
            }

            public void setUsernum(String usernum) {
                this.usernum = usernum;
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

            public String getPayfee() {
                return payfee;
            }

            public void setPayfee(String payfee) {
                this.payfee = payfee;
            }

            public String getEstatearea() {
                return estatearea;
            }

            public void setEstatearea(String estatearea) {
                this.estatearea = estatearea;
            }

            public String getPayunit() {
                return payunit;
            }

            public void setPayunit(String payunit) {
                this.payunit = payunit;
            }

            public String getPayfeetime() {
                return payfeetime;
            }

            public void setPayfeetime(String payfeetime) {
                this.payfeetime = payfeetime;
            }

            public Date getPayfeebegintime() {
                return payfeebegintime;
            }

            public void setPayfeebegintime(Date payfeebegintime) {
                this.payfeebegintime = payfeebegintime;
            }

            public Date getPayfeefinishtime() {
                return payfeefinishtime;
            }

            public void setPayfeefinishtime(Date payfeefinishtime) {
                this.payfeefinishtime = payfeefinishtime;
            }
        }
    }
}
