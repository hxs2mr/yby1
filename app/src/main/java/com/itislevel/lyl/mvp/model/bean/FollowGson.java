package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\4\24 0024.
 */

public class FollowGson {

    /**
     * status : 0
     * msg : 乐趣添加评论成功
     * data : {"id":228,"status":"1","createdtime":1524562724000,"userid":41,"touserid":0,"shareid":91,"answerer":"哈哈哈","parentid":"","comment":"发个好好玩","observer":"哈哈哈","fabulous":0}
     */
    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 228
         * status : 1
         * createdtime : 1524562724000
         * userid : 41
         * touserid : 0
         * shareid : 91
         * answerer : 哈哈哈
         * parentid :
         * comment : 发个好好玩
         * observer : 哈哈哈
         * fabulous : 0
         */

        private int id;
        private String status;
        private long createdtime;
        private int userid;
        private int touserid;
        private int shareid;
        private String answerer;
        private String parentid;
        private String comment;
        private String observer;
        private int fabulous;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getShareid() {
            return shareid;
        }

        public void setShareid(int shareid) {
            this.shareid = shareid;
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

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getObserver() {
            return observer;
        }

        public void setObserver(String observer) {
            this.observer = observer;
        }

        public int getFabulous() {
            return fabulous;
        }

        public void setFabulous(int fabulous) {
            this.fabulous = fabulous;
        }
    }
}
