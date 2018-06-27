package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:收到的祝福语
 * user:itisi
 * date:2017/12/28.13:42
 * path:com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean
 **/
public class FamilyBlessListRecevieBean {

    /**
     * list : [{"wishid":7,"userid":0,"feteid":0,"wishes":"8","wishtime":1513307433000,
     * "nickname":"Coco"},{"wishid":6,"userid":0,"feteid":0,"wishes":"7",
     * "wishtime":1513307413000,"nickname":"Coco"},{"wishid":5,"userid":0,"feteid":0,
     * "wishes":"6","wishtime":1513307385000,"nickname":null},{"wishid":2,"userid":0,"feteid":0,
     * "wishes":"3","wishtime":1513245598000,"nickname":"Coco"}]
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
         * wishid : 7
         * userid : 0
         * feteid : 0
         * wishes : 8
         * wishtime : 1513307433000
         * nickname : Coco
         */

        private int wishid;
        private int userid;
        private int feteid;
        private String wishes;
        private long wishtime;
        private String nickname;

        public int getWishid() {
            return wishid;
        }

        public void setWishid(int wishid) {
            this.wishid = wishid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getFeteid() {
            return feteid;
        }

        public void setFeteid(int feteid) {
            this.feteid = feteid;
        }

        public String getWishes() {
            return wishes;
        }

        public void setWishes(String wishes) {
            this.wishes = wishes;
        }

        public long getWishtime() {
            return wishtime;
        }

        public void setWishtime(long wishtime) {
            this.wishtime = wishtime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
