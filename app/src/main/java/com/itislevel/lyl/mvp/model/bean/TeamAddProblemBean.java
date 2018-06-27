package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.17:24
 * path:com.itislevel.lyl.mvp.model.bean.TeamAddProblemBean
 **/
public class TeamAddProblemBean {


    /**
     * list : [{"id":3,"userid":3,"nickname":"itisi","content":"cdsa","imge":"ds.jsp",
     * "video":null,"status":"1","type":"1","createdtime":1513165250000,"provid":2100,
     * "cityid":2111,"provincename":"dsa","cityname":"ss","regstid":1,
     * "imgurl":"C5476E72-38DE-445F-95B9-AC2510FBC7AE.jpg","parentname":null,"parentid":null,
     * "comments":[{"id":2,"annotabceid":3,"userid":3,"parentid":"0","parentname":"菜鸟",
     * "comment":"dasdqw","status":"1","createdtime":null,"fabulous":"0","name":"tomcat1",
     * "photo":"75E79EA2-C07E-44EC-86C9-1AADB5482FFF.jpg"}]}]
     * allRow : 3
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
         * id : 3
         * userid : 3
         * nickname : itisi
         * content : cdsa
         * imge : ds.jsp
         * video : null
         * status : 1
         * type : 1
         * createdtime : 1513165250000
         * provid : 2100
         * cityid : 2111
         * provincename : dsa
         * cityname : ss
         * regstid : 1
         * imgurl : C5476E72-38DE-445F-95B9-AC2510FBC7AE.jpg
         * parentname : null
         * parentid : null
         * comments : [{"id":2,"annotabceid":3,"userid":3,"parentid":"0","parentname":"菜鸟",
         * "comment":"dasdqw","status":"1","createdtime":null,"fabulous":"0","name":"tomcat1",
         * "photo":"75E79EA2-C07E-44EC-86C9-1AADB5482FFF.jpg"}]
         */

        private int id;
        private int userid;
        private String nickname;
        private String content;
        private String imge;
        private Object video;
        private String status;
        private String type;
        private long createdtime;
        private int provid;
        private int cityid;
        private String provincename;
        private String cityname;
        private int regstid;
        private String imgurl;
        private Object parentname;
        private Object parentid;
        private List<CommentsBean> comments;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public int getRegstid() {
            return regstid;
        }

        public void setRegstid(int regstid) {
            this.regstid = regstid;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public Object getParentname() {
            return parentname;
        }

        public void setParentname(Object parentname) {
            this.parentname = parentname;
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
             * id : 2
             * annotabceid : 3
             * userid : 3
             * parentid : 0
             * parentname : 菜鸟
             * comment : dasdqw
             * status : 1
             * createdtime : null
             * fabulous : 0
             * name : tomcat1
             * photo : 75E79EA2-C07E-44EC-86C9-1AADB5482FFF.jpg
             */

            private int id;
            private int annotabceid;
            private int userid;
            private String parentid;
            private String parentname;
            private String comment;
            private String status;
            private Object createdtime;
            private String fabulous;
            private String name;
            private String photo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAnnotabceid() {
                return annotabceid;
            }

            public void setAnnotabceid(int annotabceid) {
                this.annotabceid = annotabceid;
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

            public String getParentname() {
                return parentname;
            }

            public void setParentname(String parentname) {
                this.parentname = parentname;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getCreatedtime() {
                return createdtime;
            }

            public void setCreatedtime(Object createdtime) {
                this.createdtime = createdtime;
            }

            public String getFabulous() {
                return fabulous;
            }

            public void setFabulous(String fabulous) {
                this.fabulous = fabulous;
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
