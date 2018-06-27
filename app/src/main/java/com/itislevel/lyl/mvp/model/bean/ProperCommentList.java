package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\8 0008.
 */

public class ProperCommentList {
    /**
     * count : 1
     * pageBean : {"list":[{"id":9,"rwid":1,"userid":39,"observer":"二狗","headurl":null,"comment":"网络","status":"1","score":8,"phone":null,"createdtime":1528361093000}],"allRow":1,"totalPage":1,"currentPage":1,"pageSize":10,"pageIndex":{"startindex":1,"endindex":1}}
     */

    private int count;
    private PageBeanBean pageBean;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PageBeanBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBeanBean pageBean) {
        this.pageBean = pageBean;
    }

    public static class PageBeanBean {
        /**
         * list : [{"id":9,"rwid":1,"userid":39,"observer":"二狗","headurl":null,"comment":"网络","status":"1","score":8,"phone":null,"createdtime":1528361093000}]
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
             * id : 9
             * rwid : 1
             * userid : 39
             * observer : 二狗
             * headurl : null
             * comment : 网络
             * status : 1
             * score : 8
             * phone : null
             * createdtime : 1528361093000
             */

            private int id;
            private int rwid;
            private int userid;
            private String observer;
            private Object headurl;
            private String comment;
            private String status;
            private int score;
            private Object phone;
            private long createdtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRwid() {
                return rwid;
            }

            public void setRwid(int rwid) {
                this.rwid = rwid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getObserver() {
                return observer;
            }

            public void setObserver(String observer) {
                this.observer = observer;
            }

            public Object getHeadurl() {
                return headurl;
            }

            public void setHeadurl(Object headurl) {
                this.headurl = headurl;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public long getCreatedtime() {
                return createdtime;
            }

            public void setCreatedtime(long createdtime) {
                this.createdtime = createdtime;
            }
        }
    }
}
