package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-04.14:45
 * path:com.itislevel.lyl.mvp.model.bean.BlessSearchBean
 **/
public class BlessSearchBean {

    /**
     * list : [{"happynum":0,"nickname":"itisi","status":"1","userid":1,"provid":140000,
     * "cityid":140400,"id":11,"content":"图文喜事","username":"itisi",
     * "imge":"A1FEEBC7-A58D-4E14-9500-51998D5D70C7.jpeg, 7CB2B0EB-FB54-4B92-A70E-F8D32047754C
     * .jpg, D7745641-E6F5-49C2-9C0C-9CF73E701967.jpg, 80D7B765-DC53-4914-9DB8-DD92C1D88ACB.jpg",
     * "provincename":"山西省","looknum":167,"useridfabulous":"1","cityname":"长治市",
     * "fabulousnumber":1,"imgurl":"19AF3809-F618-4297-9599-DEE418DD46BB.jpg"},{"happynum":0,
     * "nickname":"itisi","status":"1","userid":1,"provid":140000,"cityid":140400,"id":9,
     * "content":"图文333","username":"itisi","imge":"636B2D0B-ACDC-4F04-A5D9-B40A4B1BB3DA.jpg,
     * 7CB2DB7F-C1F2-445C-B3F5-BA9B47291A11.jpeg","provincename":"山西省","looknum":0,
     * "useridfabulous":null,"cityname":"长治市","fabulousnumber":0,
     * "imgurl":"19AF3809-F618-4297-9599-DEE418DD46BB.jpg"},{"happynum":0,"nickname":"itisi",
     * "status":"1","userid":1,"provid":140000,"cityid":140400,"id":7,"content":"图文喜事",
     * "username":"itisi","imge":"F9393218-729A-4D39-9B1A-2A1F51B5B6FA.jpeg,
     * F0F8A3AF-C716-4EFB-9F6F-7E848239AC06.jpg, F614E48A-034E-465B-A3E1-C1608285AB60.jpeg",
     * "provincename":"山西省","looknum":0,"useridfabulous":null,"cityname":"长治市",
     * "fabulousnumber":0,"imgurl":"19AF3809-F618-4297-9599-DEE418DD46BB.jpg"},{"happynum":0,
     * "nickname":"itisi","status":"1","userid":1,"provid":140000,"cityid":140400,"id":2,
     * "content":"文本发布2","username":"itisi","imge":"","provincename":"山西省","looknum":0,
     * "useridfabulous":null,"cityname":"长治市","fabulousnumber":0,
     * "imgurl":"19AF3809-F618-4297-9599-DEE418DD46BB.jpg"},{"happynum":0,"nickname":"itisi",
     * "status":"1","userid":1,"provid":140000,"cityid":140400,"id":1,"content":"文本发布",
     * "username":"itisi","imge":"","provincename":"山西省","looknum":0,"useridfabulous":null,
     * "cityname":"长治市","fabulousnumber":0,"imgurl":"19AF3809-F618-4297-9599-DEE418DD46BB.jpg"}]
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
         * happynum : 0
         * nickname : itisi
         * status : 1
         * userid : 1
         * provid : 140000
         * cityid : 140400
         * id : 11
         * content : 图文喜事
         * username : itisi
         * imge : A1FEEBC7-A58D-4E14-9500-51998D5D70C7.jpeg, 7CB2B0EB-FB54-4B92-A70E-F8D32047754C
         * .jpg, D7745641-E6F5-49C2-9C0C-9CF73E701967.jpg, 80D7B765-DC53-4914-9DB8-DD92C1D88ACB.jpg
         * provincename : 山西省
         * looknum : 167
         * useridfabulous : 1
         * cityname : 长治市
         * fabulousnumber : 1
         * imgurl : 19AF3809-F618-4297-9599-DEE418DD46BB.jpg
         */

        private int happynum;
        private String nickname;
        private String status;
        private int userid;
        private int provid;
        private int cityid;
        private int id;
        private String content;
        private String username;
        private String imge;
        private String provincename;
        private int looknum;
        private String useridfabulous;
        private String cityname;
        private int fabulousnumber;
        private String imgurl;

        public int getHappynum() {
            return happynum;
        }

        public void setHappynum(int happynum) {
            this.happynum = happynum;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getImge() {
            return imge;
        }

        public void setImge(String imge) {
            this.imge = imge;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public int getLooknum() {
            return looknum;
        }

        public void setLooknum(int looknum) {
            this.looknum = looknum;
        }

        public String getUseridfabulous() {
            return useridfabulous;
        }

        public void setUseridfabulous(String useridfabulous) {
            this.useridfabulous = useridfabulous;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public int getFabulousnumber() {
            return fabulousnumber;
        }

        public void setFabulousnumber(int fabulousnumber) {
            this.fabulousnumber = fabulousnumber;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
