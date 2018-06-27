package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/22.17:45
 * path:com.itislevel.lyl.mvp.model.bean.BlessListBean
 **/
public class BlessListBean {

    /**
     * list : [{"id":1,"userid":1,"username":"itisi","nickname":"itisi",
     * "content":"Dfgdfgfgfgfdg","imge":",1A7ECE8B-82C8-4F3A-A299-1ED450CFDC88.jpeg,
     * 1FDE1E48-DABD-4146-AAAD-182C2F2B5FAF.jpeg,43337663-4897-4826-9416-5090FA8F80C6.jpeg",
     * "status":"1","createdtime":1513936204092,"provid":0,"provincename":null,"cityid":0,
     * "cityname":null,"useridfabulous":null,"fabulousnumber":null,"sacrificenum":null,
     * "looknum":20,"islike":false,"comments":[{"id":1,"happyid":1,"userid":1,"touserid":0,
     * "nickname":"itisi","tonickname":null,"parentid":"1","parentname":"mm","comment":"mm",
     * "fabulous":"0","status":"1","createdtime":1513935866000,"comments1":[{"id":2,"happyid":1,
     * "userid":2,"touserid":2,"nickname":"itisi2","tonickname":"itisi2","parentid":"1",
     * "parentname":null,"comment":"gg","fabulous":"1","status":"1","createdtime":1513935906000,
     * "comments1":null}]}]}]
     * allRow : 34
     * totalPage : 4
     * currentPage : 1
     * pageSize : 10
     * pageIndex : {"startindex":1,"endindex":4}
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
         * endindex : 4
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
         * userid : 1
         * username : itisi
         * nickname : itisi
         * content : Dfgdfgfgfgfdg
         * imge : ,1A7ECE8B-82C8-4F3A-A299-1ED450CFDC88.jpeg,1FDE1E48-DABD-4146-AAAD-182C2F2B5FAF
         * .jpeg,43337663-4897-4826-9416-5090FA8F80C6.jpeg
         * status : 1
         * createdtime : 1513936204092
         * provid : 0
         * provincename : null
         * cityid : 0
         * cityname : null
         * useridfabulous : null
         * fabulousnumber : null
         * sacrificenum : null
         * looknum : 20
         * islike : false
         * comments : [{"id":1,"happyid":1,"userid":1,"touserid":0,"nickname":"itisi",
         * "tonickname":null,"parentid":"1","parentname":"mm","comment":"mm","fabulous":"0",
         * "status":"1","createdtime":1513935866000,"comments1":[{"id":2,"happyid":1,"userid":2,
         * "touserid":2,"nickname":"itisi2","tonickname":"itisi2","parentid":"1",
         * "parentname":null,"comment":"gg","fabulous":"1","status":"1",
         * "createdtime":1513935906000,"comments1":null}]}]
         *  private int img_w;
         private int img_h;
         */

        private int id;
        private int userid;
        private String username;
        private String nickname;
        private String content;
        private String imge;
        private String status;
        private String imgurl;

        private long createdtime;
        private int provid;
        private Object provincename;
        private int cityid;
        private Object cityname;
        private Object useridfabulous;
        private String fabulousnumber;
        private String happynum;
        private int looknum;
        private String ispoint;
        private List<CommentsBean> shmlist;
        private List<String> nmpointlist;
        private String isfollow;

        private int img_w;
        private int img_h;

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

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

        public List<String> getNmpointlist() {
            return nmpointlist;
        }

        public void setNmpointlist(List<String> nmpointlist) {
            this.nmpointlist = nmpointlist;
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

        public int getProvid() {
            return provid;
        }

        public void setProvid(int provid) {
            this.provid = provid;
        }

        public Object getProvincename() {
            return provincename;
        }

        public void setProvincename(Object provincename) {
            this.provincename = provincename;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public Object getCityname() {
            return cityname;
        }

        public void setCityname(Object cityname) {
            this.cityname = cityname;
        }

        public Object getUseridfabulous() {
            return useridfabulous;
        }

        public void setUseridfabulous(Object useridfabulous) {
            this.useridfabulous = useridfabulous;
        }

        public String getFabulousnumber() {
            return fabulousnumber;
        }

        public void setFabulousnumber(String fabulousnumber) {
            this.fabulousnumber = fabulousnumber;
        }

        public String getHappynum() {
            return happynum;
        }

        public void setHappynum(String happynum) {
            this.happynum = happynum;
        }

        public int getLooknum() {
            return looknum;
        }

        public void setLooknum(int looknum) {
            this.looknum = looknum;
        }

        public String getIspoint() {
            return ispoint;
        }

        public void setIspoint(String ispoint) {
            this.ispoint = ispoint;
        }

        public List<CommentsBean> getShmlist() {
            return shmlist;
        }

        public void setShmlist(List<CommentsBean> shmlist) {
            this.shmlist = shmlist;
        }

        public static class CommentsBean {
            /**
             * id : 1
             * happyid : 1
             * userid : 1
             * touserid : 0
             * nickname : itisi
             * tonickname : null
             * parentid : 1
             * parentname : mm
             * comment : mm
             * fabulous : 0
             * status : 1
             * createdtime : 1513935866000
             * comments1 : [{"id":2,"happyid":1,"userid":2,"touserid":2,"nickname":"itisi2",
             * "tonickname":"itisi2","parentid":"1","parentname":null,"comment":"gg",
             * "fabulous":"1","status":"1","createdtime":1513935906000,"comments1":null}]
             */

            private int id;
            private int happyid;
            private int userid;
            private int touserid;
            private String observer;
            private Object answerer;
            private String parentid;
            private String parentname;
            private String comment;
            private String fabulous;
            private String status;
            private long createdtime;
            private List<Comments1Bean> comments1;
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

            public int getHappyid() {
                return happyid;
            }

            public void setHappyid(int happyid) {
                this.happyid = happyid;
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


            public String getObserver() {
                return observer;
            }

            public void setObserver(String observer) {
                this.observer = observer;
            }

            public Object getAnswerer() {
                return answerer;
            }

            public void setAnswerer(Object answerer) {
                this.answerer = answerer;
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

            public String getFabulous() {
                return fabulous;
            }

            public void setFabulous(String fabulous) {
                this.fabulous = fabulous;
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

            public List<Comments1Bean> getComments1() {
                return comments1;
            }

            public void setComments1(List<Comments1Bean> comments1) {
                this.comments1 = comments1;
            }

            public static class Comments1Bean {
                /**
                 * id : 2
                 * happyid : 1
                 * userid : 2
                 * touserid : 2
                 * nickname : itisi2
                 * tonickname : itisi2
                 * parentid : 1
                 * parentname : null
                 * comment : gg
                 * fabulous : 1
                 * status : 1
                 * createdtime : 1513935906000
                 * comments1 : null
                 *
                 */

                private int id;
                private int happyid;
                private int userid;
                private int touserid;
                private String observer;
                private String answerer;
                private String parentid;
                private Object parentname;
                private String comment;
                private String fabulous;
                private String status;
                private long createdtime;
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

                public int getHappyid() {
                    return happyid;
                }

                public void setHappyid(int happyid) {
                    this.happyid = happyid;
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

                public String getObserver() {
                    return observer;
                }

                public void setObserver(String observer) {
                    this.observer = observer;
                }

                public String getAnswerer() {
                    return answerer;
                }

                public void setAnswerer(String answerer) {
                    this.answerer = answerer;
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

                public String getFabulous() {
                    return fabulous;
                }

                public void setFabulous(String fabulous) {
                    this.fabulous = fabulous;
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

                public Object getComments1() {
                    return comments1;
                }

                public void setComments1(Object comments1) {
                    this.comments1 = comments1;
                }

                public Comments1Bean() {

                }

                public Comments1Bean(int id, int happyid, int userid, int touserid, String observer, String answerer, String parentid, String comment, String fabulous, long createdtime) {
                    this.id = id;
                    this.happyid = happyid;
                    this.userid = userid;
                    this.touserid = touserid;
                    this.observer = observer;
                    this.answerer = answerer;
                    this.parentid = parentid;
                    this.comment = comment;
                    this.fabulous = fabulous;
                    this.createdtime = createdtime;
                }
            }



        }
    }
}
