package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\31 0031.
 */

public class ComSearchBean {

    /**
     * list : [{"id":1,"managerid":5,"vid":"122123","cateid":1,"realname":"尼玛","phone":"18823852027","content":"来吧","villagename":"去","catename":"吃屎","imgs":"1.jsp","villageaddress":"尼玛的法定","createdtime":1527644959000},{"id":2,"managerid":5,"vid":"122123","cateid":1,"realname":"尼玛","phone":"18823852027","content":"来吧","villagename":"去","catename":"吃屎","imgs":"1.jsp","villageaddress":"尼玛的法定","createdtime":1527644967000},{"id":3,"managerid":5,"vid":"122123","cateid":1,"realname":"尼玛","phone":"18823852027","content":"来吧","villagename":"去","catename":"吃屎","imgs":"1.jsp","villageaddress":"尼玛的法定","createdtime":1527645501000}]
     * allRow : 3
     * totalPage : 1
     * currentPage : 1
     * pageSize : 5
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
         * id : 1
         * managerid : 5
         * vid : 122123
         * cateid : 1
         * realname : 尼玛
         * phone : 18823852027
         * content : 来吧
         * villagename : 去
         * catename : 吃屎
         * imgs : 1.jsp
         * villageaddress : 尼玛的法定
         * createdtime : 1527644959000
         */

        private int id;
        private int managerid;
        private String vid;
        private int cateid;
        private String realname;
        private String phone;
        private String content;
        private String villagename;
        private String catename;
        private String imgs;
        private String villageaddress;
        private long createdtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getManagerid() {
            return managerid;
        }

        public void setManagerid(int managerid) {
            this.managerid = managerid;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public int getCateid() {
            return cateid;
        }

        public void setCateid(int cateid) {
            this.cateid = cateid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVillagename() {
            return villagename;
        }

        public void setVillagename(String villagename) {
            this.villagename = villagename;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getVillageaddress() {
            return villageaddress;
        }

        public void setVillageaddress(String villageaddress) {
            this.villageaddress = villageaddress;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }
    }
}
