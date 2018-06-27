package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-03.17:11
 * path:com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean
 **/
public class BlessReceiveYuBean {

    /**
     * list : [{"wishid":18,"receiveuserid":0,"userid":3,"happyid":11,"wishes":"test123",
     * "wishtime":1515045511000,"tonickname":null,"nickname":"Coco"},{"wishid":16,
     * "receiveuserid":0,"userid":3,"happyid":11,"wishes":"2","wishtime":1514886081000,
     * "tonickname":null,"nickname":"Coco"},{"wishid":4,"receiveuserid":0,"userid":1,
     * "happyid":11,"wishes":"生活如意，事业高升","wishtime":1514864225000,"tonickname":null,
     * "nickname":"itisi"}]
     * allRow : 7
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
         * wishid : 18
         * receiveuserid : 0
         * userid : 3
         * happyid : 11
         * wishes : test123
         * wishtime : 1515045511000
         * tonickname : null
         * nickname : Coco
         */

        private int wishid;
        private int receiveuserid;
        private int userid;
        private int happyid;
        private String wishes;
        private long wishtime;
        private Object tonickname;
        private String nickname;

        public int getWishid() {
            return wishid;
        }

        public void setWishid(int wishid) {
            this.wishid = wishid;
        }

        public int getReceiveuserid() {
            return receiveuserid;
        }

        public void setReceiveuserid(int receiveuserid) {
            this.receiveuserid = receiveuserid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getHappyid() {
            return happyid;
        }

        public void setHappyid(int happyid) {
            this.happyid = happyid;
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

        public Object getTonickname() {
            return tonickname;
        }

        public void setTonickname(Object tonickname) {
            this.tonickname = tonickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
