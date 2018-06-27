package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\15 0015.
 */

public class LetterBean {

    /**
     * list : [{"title":"美国的祭祀","fetename":null,"createdtime":1526287275000,"respectfully":"尊敬的特朗普","looknum":0,"comment":"你的航母被水母给占领了","feteid":34,"letterid":6}]
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
         * title : 美国的祭祀
         * fetename : null
         * createdtime : 1526287275000
         * respectfully : 尊敬的特朗普
         * looknum : 0
         * comment : 你的航母被水母给占领了
         * feteid : 34
         * letterid : 6
         */

        private String title;
        private Object fetename;
        private long createdtime;
        private String respectfully;
        private int looknum;
        private String comment;
        private int feteid;
        private int letterid;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getFetename() {
            return fetename;
        }

        public void setFetename(Object fetename) {
            this.fetename = fetename;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public String getRespectfully() {
            return respectfully;
        }

        public void setRespectfully(String respectfully) {
            this.respectfully = respectfully;
        }

        public int getLooknum() {
            return looknum;
        }

        public void setLooknum(int looknum) {
            this.looknum = looknum;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getFeteid() {
            return feteid;
        }

        public void setFeteid(int feteid) {
            this.feteid = feteid;
        }

        public int getLetterid() {
            return letterid;
        }

        public void setLetterid(int letterid) {
            this.letterid = letterid;
        }
    }
}
