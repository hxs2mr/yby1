package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/6.17:34
 * path:com.itislevel.lyl.mvp.model.bean.FunsharingMyBean
 **/
public class FunsharingMyBean {


    /**
     * list : [{"id":180,"userid":3,"username":"itisi1","nickname":"itisi","addressid":null,
     * "content":"大哥你家乡有400斤鸭吗","imge":"\"null\"","video":null,"status":"1",
     * "createdtime":1513057868000,"useridfabulous":null,"fabulousnumber":0,"islike":false,
     * "parentid":null,"parentname":null,"comment":null,"comments":null},{"id":179,"userid":3,
     * "username":"itisi1","nickname":"itisi","addressid":null,"content":"f萨斯菲大幅度",
     * "imge":"\"null\"","video":null,"status":"1","createdtime":1513057699000,
     * "useridfabulous":null,"fabulousnumber":0,"islike":false,"parentid":null,"parentname":null,
     * "comment":null,"comments":null}]
     * allRow : 14
     * totalPage : 2
     * currentPage : 1
     * pageSize : 10
     * pageIndex : {"startindex":1,"endindex":2}
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
         * endindex : 2
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
         * id : 180
         * userid : 3
         * username : itisi1
         * nickname : itisi
         * addressid : null
         * content : 大哥你家乡有400斤鸭吗
         * imge : "null"
         * video : null
         * status : 1
         * createdtime : 1513057868000
         * useridfabulous : null
         * fabulousnumber : 0
         * islike : false
         * parentid : null
         * parentname : null
         * comment : null
         * comments : null
         * imgurl
         */

        private int id;
        private int userid;
        private String username;
        private String nickname;
        private Object addressid;
        private String content;
        private String imge;
        private Object video;
        private String status;
        private long createdtime;
        private Object useridfabulous;
        private int fabulousnumber;
        private boolean islike;
        private Object parentid;
        private Object parentname;
        private Object comment;
        private Object comments;

        private String imgurl;
        private List<String> nicknameFabulous;

        public List<String> getNicknameFabulous() {
            return nicknameFabulous;
        }

        public void setNicknameFabulous(List<String> nicknameFabulous) {
            this.nicknameFabulous = nicknameFabulous;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getAddressid() {
            return addressid;
        }

        public void setAddressid(Object addressid) {
            this.addressid = addressid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImge() {
            return imge;
        }

        public void setImge(String imge) {
            this.imge = imge;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public Object getUseridfabulous() {
            return useridfabulous;
        }

        public void setUseridfabulous(Object useridfabulous) {
            this.useridfabulous = useridfabulous;
        }

        public int getFabulousnumber() {
            return fabulousnumber;
        }

        public void setFabulousnumber(int fabulousnumber) {
            this.fabulousnumber = fabulousnumber;
        }

        public boolean isIslike() {
            return islike;
        }

        public void setIslike(boolean islike) {
            this.islike = islike;
        }

        public Object getParentid() {
            return parentid;
        }

        public void setParentid(Object parentid) {
            this.parentid = parentid;
        }

        public Object getParentname() {
            return parentname;
        }

        public void setParentname(Object parentname) {
            this.parentname = parentname;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public Object getComments() {
            return comments;
        }

        public void setComments(Object comments) {
            this.comments = comments;
        }
    }
}
