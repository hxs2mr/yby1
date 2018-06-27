package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:礼物列表
 * user:itisi
 * date:2017/12/28.12:02
 * path:com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean
 **/
public class FamilyGiftListBean {

    private List<ListBean> list;
    private int ishiden;

    public int getIshiden() {
        return ishiden;
    }

    public void setIshiden(int ishiden) {
        this.ishiden = ishiden;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * sacrifid : 8
         * sacrifrem : 测试
         * sellprice : 520
         * imgurl : 534067FC-8D2A-46FB-A8A0-CB6A68B094C7.png
         * sacrifname : 祭品测试1
         */

        private int sacrifid;
        private String sacrifrem;
        private String sellprice;
        private String imgurl;
        private String sacrifname;
        private String firstcateid;

        public String getFirstcateid() {
            return firstcateid;
        }

        public void setFirstcateid(String firstcateid) {
            this.firstcateid = firstcateid;
        }

        public int getSacrifid() {
            return sacrifid;
        }

        public void setSacrifid(int sacrifid) {
            this.sacrifid = sacrifid;
        }

        public String getSacrifrem() {
            return sacrifrem;
        }

        public void setSacrifrem(String sacrifrem) {
            this.sacrifrem = sacrifrem;
        }

        public String getSellprice() {
            return sellprice;
        }

        public void setSellprice(String sellprice) {
            this.sellprice = sellprice;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getSacrifname() {
            return sacrifname;
        }

        public void setSacrifname(String sacrifname) {
            this.sacrifname = sacrifname;
        }
    }
}
