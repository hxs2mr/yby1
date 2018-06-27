package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\16 0016.
 */

public class CFChildBean {

    /**
     * lunbo : [{"logo":"1736BCC7-FC86-4B19-9019-2A7543FA2CD2.png","name":"143213","aid":1},{"logo":"1DAA6EE0-D8F6-42C4-906D-B0415E012469.png","name":"总价家政","aid":2},{"logo":"6DCD2C0C-D232-46C3-9A42-8803CB7FA33C.jpg","name":"445566...","aid":3},{"logo":"B6BB72A2-F234-4FD7-BCC2-16EAEF85D29F.jpeg","name":"勾昌俊","aid":4},{"logo":"DD363D61-AAAE-45D0-9F2B-FA2D5F021CC8.png","name":"天津小家政","aid":5}]
     * pageBean : {"list":[{"reliable":null,"istop":1,"logo":"BB9405E6-E020-4618-B0D1-2148FE8EE7D7.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=14","status":1,"nosense":null,"publisher":"棒棒哒","id":14,"commentnum":0,"title":"宝宝打人不可怕，可怕都是错误的管教方法","createdtime":1526439574000,"looknum":0},{"reliable":",53,41,38,39","istop":1,"logo":"C98E2E5C-4C8D-4666-90E6-80354A3CC436.jpg","pointnum":6,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=8","status":1,"nosense":"51,52","publisher":"小宝贝","id":8,"commentnum":0,"title":"宝宝打人不可怕，可怕的是错误的管教方法","createdtime":1523264065000,"looknum":0},{"reliable":null,"istop":1,"logo":"00F33C74-E5EF-4949-A251-B9584D0D509A.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=7","status":1,"nosense":null,"publisher":"健康达人","id":7,"commentnum":0,"title":"网上发的营养常识都是错的？","createdtime":1523263619000,"looknum":0},{"reliable":null,"istop":1,"logo":"A92243EF-8632-496E-90E2-6C2D700ED09E.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=6","status":1,"nosense":null,"publisher":"小宝贝","id":6,"commentnum":0,"title":"宝宝婴儿穿着袜子睡觉好不好？","createdtime":1523263545000,"looknum":0},{"reliable":null,"istop":1,"logo":"30205A5D-BCEE-426F-8799-6F2F9886477A.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=5","status":1,"nosense":null,"publisher":"小宝贝","id":5,"commentnum":0,"title":"谢谢你，愿意做我的孩子","createdtime":1523263507000,"looknum":0}],"allRow":5,"totalPage":1,"currentPage":1,"pageSize":10,"pageIndex":{"startindex":1,"endindex":1}}
     */

    private PageBeanBean pageBean;
    private List<LunboBean> lunbo;

    public PageBeanBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBeanBean pageBean) {
        this.pageBean = pageBean;
    }

    public List<LunboBean> getLunbo() {
        return lunbo;
    }

    public void setLunbo(List<LunboBean> lunbo) {
        this.lunbo = lunbo;
    }

    public static class PageBeanBean {
        /**
         * list : [{"reliable":null,"istop":1,"logo":"BB9405E6-E020-4618-B0D1-2148FE8EE7D7.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=14","status":1,"nosense":null,"publisher":"棒棒哒","id":14,"commentnum":0,"title":"宝宝打人不可怕，可怕都是错误的管教方法","createdtime":1526439574000,"looknum":0},{"reliable":",53,41,38,39","istop":1,"logo":"C98E2E5C-4C8D-4666-90E6-80354A3CC436.jpg","pointnum":6,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=8","status":1,"nosense":"51,52","publisher":"小宝贝","id":8,"commentnum":0,"title":"宝宝打人不可怕，可怕的是错误的管教方法","createdtime":1523264065000,"looknum":0},{"reliable":null,"istop":1,"logo":"00F33C74-E5EF-4949-A251-B9584D0D509A.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=7","status":1,"nosense":null,"publisher":"健康达人","id":7,"commentnum":0,"title":"网上发的营养常识都是错的？","createdtime":1523263619000,"looknum":0},{"reliable":null,"istop":1,"logo":"A92243EF-8632-496E-90E2-6C2D700ED09E.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=6","status":1,"nosense":null,"publisher":"小宝贝","id":6,"commentnum":0,"title":"宝宝婴儿穿着袜子睡觉好不好？","createdtime":1523263545000,"looknum":0},{"reliable":null,"istop":1,"logo":"30205A5D-BCEE-426F-8799-6F2F9886477A.jpg","pointnum":0,"flat_info_url":"http://htm.yobangyo.com/yby/htm/info.html?id=5","status":1,"nosense":null,"publisher":"小宝贝","id":5,"commentnum":0,"title":"谢谢你，愿意做我的孩子","createdtime":1523263507000,"looknum":0}]
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
             * reliable : null
             * istop : 1
             * logo : BB9405E6-E020-4618-B0D1-2148FE8EE7D7.jpg
             * pointnum : 0
             * flat_info_url : http://htm.yobangyo.com/yby/htm/info.html?id=14
             * status : 1
             * nosense : null
             * publisher : 棒棒哒
             * id : 14
             * commentnum : 0
             * title : 宝宝打人不可怕，可怕都是错误的管教方法
             * createdtime : 1526439574000
             * looknum : 0
             */

            private Object reliable;
            private int istop;
            private String logo;
            private int pointnum;
            private String flat_info_url;
            private int status;
            private Object nosense;
            private String publisher;
            private int id;
            private int commentnum;
            private String title;
            private long createdtime;
            private int looknum;

            public Object getReliable() {
                return reliable;
            }

            public void setReliable(Object reliable) {
                this.reliable = reliable;
            }

            public int getIstop() {
                return istop;
            }

            public void setIstop(int istop) {
                this.istop = istop;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getPointnum() {
                return pointnum;
            }

            public void setPointnum(int pointnum) {
                this.pointnum = pointnum;
            }

            public String getFlat_info_url() {
                return flat_info_url;
            }

            public void setFlat_info_url(String flat_info_url) {
                this.flat_info_url = flat_info_url;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getNosense() {
                return nosense;
            }

            public void setNosense(Object nosense) {
                this.nosense = nosense;
            }

            public String getPublisher() {
                return publisher;
            }

            public void setPublisher(String publisher) {
                this.publisher = publisher;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCommentnum() {
                return commentnum;
            }

            public void setCommentnum(int commentnum) {
                this.commentnum = commentnum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getCreatedtime() {
                return createdtime;
            }

            public void setCreatedtime(long createdtime) {
                this.createdtime = createdtime;
            }

            public int getLooknum() {
                return looknum;
            }

            public void setLooknum(int looknum) {
                this.looknum = looknum;
            }
        }
    }

    public static class LunboBean {
        /**
         * logo : 1736BCC7-FC86-4B19-9019-2A7543FA2CD2.png
         * name : 143213
         * aid : 1
         */

        private String logo;
        private String name;
        private int aid;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }
    }
}
