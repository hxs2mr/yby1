package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\17 0017.
 */

public class CFPinBean {


    /**
     * list : [{"status":"1","headimg":"D27C965B-BAC5-4C39-8034-139A827E30EC.jpeg","userid":39,"touserid":0,"parentid":"0","observer":"pmg123456","id":13,"writingDate":"20分钟前","createdtime":1526553387000,"answerer":"","useridfabulous":null,"comment":"勾画未来美好世界美好","fabulousnumber":0,"fabulous":0,"flatid":14},{"status":"1","headimg":"56B057C6-A2D5-4AFF-BE5D-4B3E2A609316.jpg","userid":41,"touserid":0,"parentid":"0","observer":"哈哈哈","id":12,"writingDate":"1小时前","createdtime":1526550597000,"answerer":"","useridfabulous":null,"comment":"喜欢喜欢","fabulousnumber":0,"fabulous":0,"flatid":14}]
     * allRow : 5
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
         * pointnum : 0
         * status : 1
         * headimg : 56B057C6-A2D5-4AFF-BE5D-4B3E2A609316.jpg
         * userid : 41
         * touserid : 0
         * parentid : 0
         * observer : 哈哈哈
         * id : 19
         * writingDate : 4分钟前
         * createdtime : 1526556521000
         * answerer :
         * useridfabulous : null
         * ispoint : 0
         * comment : 具体
         * fabulousnumber : 0
         * fabulous : 0
         * flatid : 14
         */

        private String pointnum;
        private String status;
        private String headimg;
        private int userid;
        private int touserid;
        private String parentid;
        private String observer;
        private int id;
        private String writingDate;
        private long createdtime;
        private String answerer;
        private Object useridfabulous;
        private String ispoint;
        private String comment;
        private int fabulousnumber;
        private int fabulous;
        private int flatid;

        public String getPointnum() {
            return pointnum;
        }

        public void setPointnum(String pointnum) {
            this.pointnum = pointnum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getTouserid() {
            return touserid;
        }

        public void setTouserid(int touserid) {
            this.touserid = touserid;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getObserver() {
            return observer;
        }

        public void setObserver(String observer) {
            this.observer = observer;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWritingDate() {
            return writingDate;
        }

        public void setWritingDate(String writingDate) {
            this.writingDate = writingDate;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public String getAnswerer() {
            return answerer;
        }

        public void setAnswerer(String answerer) {
            this.answerer = answerer;
        }

        public Object getUseridfabulous() {
            return useridfabulous;
        }

        public void setUseridfabulous(Object useridfabulous) {
            this.useridfabulous = useridfabulous;
        }

        public String getIspoint() {
            return ispoint;
        }

        public void setIspoint(String ispoint) {
            this.ispoint = ispoint;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getFabulousnumber() {
            return fabulousnumber;
        }

        public void setFabulousnumber(int fabulousnumber) {
            this.fabulousnumber = fabulousnumber;
        }

        public int getFabulous() {
            return fabulous;
        }

        public void setFabulous(int fabulous) {
            this.fabulous = fabulous;
        }

        public int getFlatid() {
            return flatid;
        }

        public void setFlatid(int flatid) {
            this.flatid = flatid;
        }
    }
}
