package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/25.16:33
 * path:com.itislevel.lyl.mvp.model.bean.TroubleMyListBean
 **/
public class TroubleMyListBean {

    /**
     * list : [{"questionid":19,"userid":1,"nickname":"itisi","content":"付费咨询烦恼","imge":"",
     * "video":null,"status":"1","firstcateid":"2","createdtime":1514188890000,"provid":140000,
     * "cityid":140400,"provincename":"山西省","cityname":"长治市","adviserid":3,
     * "imgurl":"F0A25A68-FF31-402E-B2CF-98FD02BB9B54.jpg","parentid":null,"comments":[{"id":6,
     * "questionid":19,"userid":3,"parentid":"19","comment":"回复","createdtime":1514190843000,
     * "fabulous":"0","adviserid":3,"name":"chenhgg",
     * "photo":"DEA184B8-B4A5-4004-89E5-F1ED10C39A8E.jpeg"}]}]
     * allRow : 10
     * totalPage : 10
     * currentPage : 1
     * pageSize : 1
     * pageIndex : {"startindex":1,"endindex":10}
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
         * endindex : 10
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
         * questionid : 19
         * userid : 1
         * nickname : itisi
         * content : 付费咨询烦恼
         * imge :
         * video : null
         * status : 1
         * firstcateid : 2
         * createdtime : 1514188890000
         * provid : 140000
         * cityid : 140400
         * provincename : 山西省
         * cityname : 长治市
         * adviserid : 3
         * imgurl : F0A25A68-FF31-402E-B2CF-98FD02BB9B54.jpg
         * parentid : null
         * comments : [{"id":6,"questionid":19,"userid":3,"parentid":"19","comment":"回复",
         * "createdtime":1514190843000,"fabulous":"0","adviserid":3,"name":"chenhgg",
         * "photo":"DEA184B8-B4A5-4004-89E5-F1ED10C39A8E.jpeg"}]
         */

        private int questionid;
        private int userid;
        private String nickname;
        private String content;
        private String imge;
        private Object video;
        private String status;
        private String firstcateid;
        private long createdtime;
        private int provid;
        private int cityid;
        private String provincename;
        private String cityname;
        private int adviserid;
        private String imgurl;
        private Object parentid;
        private List<CommentsBean> comments;

        public int getQuestionid() {
            return questionid;
        }

        public void setQuestionid(int questionid) {
            this.questionid = questionid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getFirstcateid() {
            return firstcateid;
        }

        public void setFirstcateid(String firstcateid) {
            this.firstcateid = firstcateid;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
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

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public int getAdviserid() {
            return adviserid;
        }

        public void setAdviserid(int adviserid) {
            this.adviserid = adviserid;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public Object getParentid() {
            return parentid;
        }

        public void setParentid(Object parentid) {
            this.parentid = parentid;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * id : 6
             * questionid : 19
             * userid : 3
             * parentid : 19
             * comment : 回复
             * createdtime : 1514190843000
             * fabulous : 0
             * adviserid : 3
             * name : chenhgg
             * photo : DEA184B8-B4A5-4004-89E5-F1ED10C39A8E.jpeg
             */

            private int id;
            private int questionid;
            private int userid;
            private String parentid;
            private String comment;
            private long createdtime;
            private String fabulous;
            private int adviserid;
            private String name;
            private String photo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getQuestionid() {
                return questionid;
            }

            public void setQuestionid(int questionid) {
                this.questionid = questionid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public long getCreatedtime() {
                return createdtime;
            }

            public void setCreatedtime(long createdtime) {
                this.createdtime = createdtime;
            }

            public String getFabulous() {
                return fabulous;
            }

            public void setFabulous(String fabulous) {
                this.fabulous = fabulous;
            }

            public int getAdviserid() {
                return adviserid;
            }

            public void setAdviserid(int adviserid) {
                this.adviserid = adviserid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }
    }
}
