package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:祝福语
 * user:itisi
 * date:2017/12/28.11:15
 * path:com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean
 **/
public class FamilyBlessListBean {


    /**
     * list : [{"wishid":26,"userid":1,"feteid":19,"wishes":"逝者安息，流芳百世。",
     * "wishtime":1515833406000,"nickname": "itisi"},{"wishid":25,"userid":3,"feteid":19,
     * "wishes":"慎终追远，不忘先人。","wishtime":1515751099000,"nickname":"Coco"},{"wishid":24,"userid":3,
     * "feteid":19,"wishes":"逝者已矣，节哀顺变。","wishtime":1515751020000,"nickname":"Coco"},
     * {"wishid":23,"userid":3,"feteid":19,"wishes":"慎终追远，不忘先人。","wishtime":1515750991000,
     * "nickname":"Coco"},{"wishid":22,"userid":3,"feteid":19,"wishes":"逝者安息，流芳百世。",
     * "wishtime":1515750965000,"nickname":"Coco"},{"wishid":21,"userid":3,"feteid":19,
     * "wishes":"斯人已去，心中犹念。","wishtime":1515746551000,"nickname":"Coco"}]
     * allRow : 12
     * totalPage : 1
     * currentPage : 1
     * pageSize : 9999
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
         * wishid : 26
         * userid : 1
         * feteid : 19
         * wishes : 逝者安息，流芳百世。
         * wishtime : 1515833406000
         * nickname : itisi
         */

        private int wishid;
        private int userid;
        private int feteid;
        private String wishes;
        private long wishtime;
        private String nickname;
        private String buyusername;
        /**
         * type : 1
         */
        private String type;


        public String getBuyusername() {
            return buyusername;
        }

        public void setBuyusername(String buyusername) {
            this.buyusername = buyusername;
        }

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
