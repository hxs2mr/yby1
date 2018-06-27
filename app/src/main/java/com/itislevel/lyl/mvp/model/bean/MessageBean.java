package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\15 0015.
 */

public class MessageBean {

    /**
     * list : [{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"68","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@55"},{"pushid":"67","receiveid":"67","moduleid":"50","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@哈哈哈"},{"pushid":"67","receiveid":"67","moduleid":"52","modelname":"dynamic_cmt","titleinfo":"您的动态有人评论!","sendmsg":"dynamic_cmt,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"0","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"头晕","time":"1秒前","dyimgorct":"0@绝对"},{"pushid":"67","receiveid":"67","moduleid":"52","modelname":"dynamic_point","titleinfo":"您的动态有人点赞!","sendmsg":"dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","iscmtorpnt":"1","relnickname":"APP","relheadimg":"0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg","comment":"点赞","time":"1秒前","dyimgorct":"0@绝对"}]
     * allRow : 164
     * totalPage : 17
     * currentPage : 1
     * pageSize : 10
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
         * pushid : 67
         * receiveid : 67
         * moduleid : 68
         * modelname : dynamic_point
         * titleinfo : 您的动态有人点赞!
         * sendmsg : dynamic_point,0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg
         * iscmtorpnt : 1
         * relnickname : APP
         * relheadimg : 0AD3A279-34E3-45A7-9B8F-11B72183D17C.jpg
         * comment : 点赞
         * time : 1秒前
         * dyimgorct : 0@55
         */

        private String pushid;
        private String receiveid;
        private String moduleid;
        private String modelname;
        private String titleinfo;
        private String sendmsg;
        private String iscmtorpnt;
        private String relnickname;
        private String relheadimg;
        private String comment;
        private String time;
        private String dyimgorct;

        public String getPushid() {
            return pushid;
        }

        public void setPushid(String pushid) {
            this.pushid = pushid;
        }

        public String getReceiveid() {
            return receiveid;
        }

        public void setReceiveid(String receiveid) {
            this.receiveid = receiveid;
        }

        public String getModuleid() {
            return moduleid;
        }

        public void setModuleid(String moduleid) {
            this.moduleid = moduleid;
        }

        public String getModelname() {
            return modelname;
        }

        public void setModelname(String modelname) {
            this.modelname = modelname;
        }

        public String getTitleinfo() {
            return titleinfo;
        }

        public void setTitleinfo(String titleinfo) {
            this.titleinfo = titleinfo;
        }

        public String getSendmsg() {
            return sendmsg;
        }

        public void setSendmsg(String sendmsg) {
            this.sendmsg = sendmsg;
        }

        public String getIscmtorpnt() {
            return iscmtorpnt;
        }

        public void setIscmtorpnt(String iscmtorpnt) {
            this.iscmtorpnt = iscmtorpnt;
        }

        public String getRelnickname() {
            return relnickname;
        }

        public void setRelnickname(String relnickname) {
            this.relnickname = relnickname;
        }

        public String getRelheadimg() {
            return relheadimg;
        }

        public void setRelheadimg(String relheadimg) {
            this.relheadimg = relheadimg;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDyimgorct() {
            return dyimgorct;
        }

        public void setDyimgorct(String dyimgorct) {
            this.dyimgorct = dyimgorct;
        }
    }
}
