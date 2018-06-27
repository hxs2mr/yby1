package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/18.15:26
 * path:com.itislevel.lyl.mvp.model.bean.TroubleListBean
 **/
public class TroubleListBean {

    /**
     * list : [{"id":9,"userid":3,"username":"itisi1","nickname":"itisi","content":"普通解答走起",
     * "imge":"\"null\"","video":null,"status":"1","type":"1","createdtime":null,
     * "provid":"520000","cityid":"520100","provincename":"贵州省","cityname":"贵阳市",
     * "parentname":null,"parentid":null,"comments":[{"id":2,"annotabceid":9,"userid":1,
     * "touserid":0,"tonickname":null,"nickname":"1","parentid":"9","parentname":"das",
     * "comment":"你才不好","status":"1","createdtime":1513578718000,"fabulous":"0",
     * "comments1":[{"id":3,"annotabceid":9,"userid":3,"touserid":1,"tonickname":"1",
     * "nickname":"itisi","parentid":"2","parentname":null,"comment":"你才不好的回复","status":"1",
     * "createdtime":1513578718000,"fabulous":"1","comments1":null}]},{"id":1,"annotabceid":9,
     * "userid":3,"touserid":0,"tonickname":null,"nickname":"itisi","parentid":"9",
     * "parentname":"打算","comment":"你不好","status":"1","createdtime":1513578163000,"fabulous":"0",
     * "comments1":[]}]}]
     * allRow : 1
     * totalPage : 1
     * currentPage : 1
     * pageSize : 3
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
         * id : 9
         * userid : 3
         * username : itisi1
         * nickname : itisi
         * content : 普通解答走起
         * imge : "null"
         * video : null
         * status : 1
         * type : 1
         * createdtime : null
         * provid : 520000
         * cityid : 520100
         * provincename : 贵州省
         * cityname : 贵阳市
         * parentname : null
         * parentid : null
         * comments : [{"id":2,"annotabceid":9,"userid":1,"touserid":0,"tonickname":null,
         * "nickname":"1","parentid":"9","parentname":"das","comment":"你才不好","status":"1",
         * "createdtime":1513578718000,"fabulous":"0","comments1":[{"id":3,"annotabceid":9,
         * "userid":3,"touserid":1,"tonickname":"1","nickname":"itisi","parentid":"2",
         * "parentname":null,"comment":"你才不好的回复","status":"1","createdtime":1513578718000,
         * "fabulous":"1","comments1":null}]},{"id":1,"annotabceid":9,"userid":3,"touserid":0,
         * "tonickname":null,"nickname":"itisi","parentid":"9","parentname":"打算","comment":"你不好",
         * "status":"1","createdtime":1513578163000,"fabulous":"0","comments1":[]}]
         *  private int img_w;
         private int img_h;
         */

        private int id;
        private int userid;
        private String username;
        private String nickname;
        private String imgurl;
        private String content;
        private String imge;
        private Object video;
        private String status;
        private String secondcateid;
        private long createdtime;
        private String provid;
        private String cityid;
        private String provincename;
        private String cityname;
        private Object parentname;
        private Object parentid;
        private List<CommentsBean> comments;
        private int img_w;
        private int img_h;

        public int getImg_w() {
            return img_w;
        }

        public void setImg_w(int img_w) {
            this.img_w = img_w;
        }

        public int getImg_h() {
            return img_h;
        }

        public void setImg_h(int img_h) {
            this.img_h = img_h;
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

        public String getProvid() {
            return provid;
        }

        public void setProvid(String provid) {
            this.provid = provid;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getSecondcateid() {
            return secondcateid;
        }

        public void setSecondcateid(String secondcateid) {
            this.secondcateid = secondcateid;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
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
             * annotabceid : 9
             * userid : 1
             * touserid : 0
             * tonickname : null
             * nickname : 1
             * parentid : 9
             * parentname : das
             * comment : 你才不好
             * status : 1
             * createdtime : 1513578718000
             * fabulous : 0
             * comments1 : [{"id":3,"annotabceid":9,"userid":3,"touserid":1,"tonickname":"1",
             * "nickname":"itisi","parentid":"2","parentname":null,"comment":"你才不好的回复",
             * "status":"1","createdtime":1513578718000,"fabulous":"1","comments1":null}]
             */

            private int id;
            private int annotabceid;
            private int userid;
            private int touserid;
            private Object tonickname;
            private String nickname;
            private String parentid;
            private String parentname;
            private String comment;
            private String status;
            private long createdtime;
            private String fabulous;
            private String fabuzheid;//发布者id

            private List<Comments1Bean> comments1;

            public String getFabuzheid() {
                return fabuzheid;
            }

            public void setFabuzheid(String fabuzheid) {
                this.fabuzheid = fabuzheid;
            }

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

            public int getTouserid() {
                return touserid;
            }

            public void setTouserid(int touserid) {
                this.touserid = touserid;
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

            public List<Comments1Bean> getComments1() {
                return comments1;
            }

            public void setComments1(List<Comments1Bean> comments1) {
                this.comments1 = comments1;
            }

            public static class Comments1Bean {
                /**
                 * id : 3
                 * annotabceid : 9
                 * userid : 3
                 * touserid : 1
                 * tonickname : 1
                 * nickname : itisi
                 * parentid : 2
                 * parentname : null
                 * comment : 你才不好的回复
                 * status : 1
                 * createdtime : 1513578718000
                 * fabulous : 1
                 * comments1 : null
                 */

                private int id;
                private int annotabceid;
                private int userid;
                private int touserid;
                private String tonickname;
                private String nickname;
                private String parentid;
                private Object parentname;
                private String comment;
                private String status;
                private long createdtime;
                private String fabulous;
                private Object comments1;
                private String fabuzheid;//发布者id

                public String getFabuzheid() {
                    return fabuzheid;
                }

                public void setFabuzheid(String fabuzheid) {
                    this.fabuzheid = fabuzheid;
                }

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

                public int getTouserid() {
                    return touserid;
                }

                public void setTouserid(int touserid) {
                    this.touserid = touserid;
                }

                public String getTonickname() {
                    return tonickname;
                }

                public void setTonickname(String tonickname) {
                    this.tonickname = tonickname;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getParentid() {
                    return parentid;
                }

                public void setParentid(String parentid) {
                    this.parentid = parentid;
                }

                public Object getParentname() {
                    return parentname;
                }

                public void setParentname(Object parentname) {
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

                public Object getComments1() {
                    return comments1;
                }

                public void setComments1(Object comments1) {
                    this.comments1 = comments1;
                }
            }
        }
    }
}
