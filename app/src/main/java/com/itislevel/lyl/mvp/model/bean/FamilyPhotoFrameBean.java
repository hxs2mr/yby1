package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/28.14:11
 * path:com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean
 **/
public class FamilyPhotoFrameBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * sales : 0
         * sacrifid : 11
         * createdtime : 1514020781000
         * firstcateid : 20
         * sacrifrem : 祭品测试4
         * sellprice : 22
         * freeprice : 0.00
         * sacrifname : 祭品测试4
         * imgurl : 6EE26E43-BBF3-4DB4-A8B5-A375DD1231E1.png
         */

        private int sales;
        private int sacrifid;
        private long createdtime;
        private int firstcateid;
        private String sacrifrem;
        private String sellprice;
        private String freeprice;
        private String sacrifname;
        private String imgurl;

        private boolean ischeck; //是否选中

        public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getSacrifid() {
            return sacrifid;
        }

        public void setSacrifid(int sacrifid) {
            this.sacrifid = sacrifid;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public int getFirstcateid() {
            return firstcateid;
        }

        public void setFirstcateid(int firstcateid) {
            this.firstcateid = firstcateid;
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

        public String getFreeprice() {
            return freeprice;
        }

        public void setFreeprice(String freeprice) {
            this.freeprice = freeprice;
        }

        public String getSacrifname() {
            return sacrifname;
        }

        public void setSacrifname(String sacrifname) {
            this.sacrifname = sacrifname;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
