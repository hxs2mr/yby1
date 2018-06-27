package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017-12-28.17:23
 * path:com.itislevel.lyl.mvp.model.bean.BlessAddBean
 **/
public class BlessAddBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * wishtime : 1516326389000
         * wishes : 年年今日，岁岁今朝。
         * wishid : 6
         * userid : 1
         * happyid : 6
         */

        private long wishtime;
        private String wishes;
        private int wishid;
        private int userid;
        private int happyid;

        public long getWishtime() {
            return wishtime;
        }

        public void setWishtime(long wishtime) {
            this.wishtime = wishtime;
        }

        public String getWishes() {
            return wishes;
        }

        public void setWishes(String wishes) {
            this.wishes = wishes;
        }

        public int getWishid() {
            return wishid;
        }

        public void setWishid(int wishid) {
            this.wishid = wishid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getHappyid() {
            return happyid;
        }

        public void setHappyid(int happyid) {
            this.happyid = happyid;
        }
    }
}
