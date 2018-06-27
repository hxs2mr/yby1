package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.mvp.model.bean.FamilyListBean
 **/
public class FamilyFollowListBean {
    /**
     * list : [{"feteid":6,"userid":0,"provid":1234,"provincename":"è´µå·\u009e","cityid":12345,
     * "cityname":"è´µé\u0098³","status":"1","type":null,"deadname":"å¼ ä¸\u0089",
     * "relation":null,"fetename":"æ\u009d\u008eå\u009b\u009b","imgestr":"beijin.jsp,xiangkuang
     * .jsp.touxiang.jsp","wishesnum":0,"looknum":0,"sacrificenum":0,"createdtime":1514430055000}]
     * allRow : 6
     * totalPage : 6
     * currentPage : 1
     * pageSize : 1
     * pageIndex : {"startindex":1,"endindex":6}
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
         * endindex : 6
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
         * feteid : 6
         * userid : 0
         * provid : 1234
         * provincename : è´µå·
         * cityid : 12345
         * cityname : è´µé³
         * status : 1
         * type : null
         * deadname : å¼ ä¸
         * relation : null
         * fetename : æå
         * imgestr : beijin.jsp,xiangkuang.jsp.touxiang.jsp
         * wishesnum : 0
         * looknum : 0
         * sacrificenum : 0
         * createdtime : 1514430055000
         */

        private int feteid;
        private int userid;
        private int provid;
        private String provincename;
        private int cityid;
        private String cityname;
        private String status;
        private Object type;
        private String deadname;
        private Object relation;
        private String fetename;
        private String imgestr;
        private int wishesnum;
        private int looknum;
        private int sacrificenum;
        private long createdtime;
        /**
         * musicname : http://app.yobangyo.com/music/1.mp3
         */
        private String musicname;
        private String nickname;
        private String imgurl;
        private String fete_share_url;

        public String getFete_share_url() {
            return fete_share_url;
        }

        public void setFete_share_url(String fete_share_url) {
            this.fete_share_url = fete_share_url;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getFeteid() {
            return feteid;
        }

        public void setFeteid(int feteid) {
            this.feteid = feteid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getProvid() {
            return provid;
        }

        public void setProvid(int provid) {
            this.provid = provid;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getDeadname() {
            return deadname;
        }

        public void setDeadname(String deadname) {
            this.deadname = deadname;
        }

        public Object getRelation() {
            return relation;
        }

        public void setRelation(Object relation) {
            this.relation = relation;
        }

        public String getFetename() {
            return fetename;
        }

        public void setFetename(String fetename) {
            this.fetename = fetename;
        }

        public String getImgestr() {
            return imgestr;
        }

        public void setImgestr(String imgestr) {
            this.imgestr = imgestr;
        }

        public int getWishesnum() {
            return wishesnum;
        }

        public void setWishesnum(int wishesnum) {
            this.wishesnum = wishesnum;
        }

        public int getLooknum() {
            return looknum;
        }

        public void setLooknum(int looknum) {
            this.looknum = looknum;
        }

        public int getSacrificenum() {
            return sacrificenum;
        }

        public void setSacrificenum(int sacrificenum) {
            this.sacrificenum = sacrificenum;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }
        public String getMusicname() {
            return musicname;
        }

        public void setMusicname(String musicname) {
            this.musicname = musicname;
        }
    }
}
