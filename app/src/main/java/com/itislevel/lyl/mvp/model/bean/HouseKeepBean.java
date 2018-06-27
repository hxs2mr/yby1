package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/7.16:24
 * path:com.itislevel.lyl.mvp.model.bean.HouseKeepBean
 **/
public class HouseKeepBean {

    /**
     * list : [{"id":2,"companyname":"贵州某家政公司2","companyrem":"防守打法",
     * "companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg","contactname":"莫大勇",
     * "contactphone":"18302562527","address":"防守打法","status":"1","createdtime":1512554608000,
     * "provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":3,"companyname":"贵州某家政公司3",
     * "companyrem":"防守打法","companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg",
     * "contactname":"莫大勇","contactphone":"18302562527","address":"fasfa","status":"1",
     * "createdtime":1512554608000,"provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":4,
     * "companyname":"贵州某家政公司4","companyrem":"防守打法",
     * "companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg","contactname":"莫大勇",
     * "contactphone":"18302562527","address":"weqe","status":"1","createdtime":1512554608000,
     * "provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":5,"companyname":"贵州某家政公司5",
     * "companyrem":"防守打法","companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg",
     * "contactname":"莫大勇","contactphone":"18302562527","address":"2hkhk","status":"1",
     * "createdtime":1512554608000,"provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":6,
     * "companyname":"贵州某家政公司6","companyrem":"防守打法",
     * "companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg","contactname":"莫大勇",
     * "contactphone":"18302562527","address":"hfg","status":"1","createdtime":1512554608000,
     * "provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":7,"companyname":"贵州某家政公司7",
     * "companyrem":"防守打法","companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg",
     * "contactname":"莫大勇","contactphone":"18302562527","address":"防守打法","status":"1",
     * "createdtime":1512554608000,"provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":8,
     * "companyname":"贵州某家政公司8","companyrem":"防守打法",
     * "companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg","contactname":"莫大勇",
     * "contactphone":"18302562527","address":"fasfa","status":"1","createdtime":1512554608000,
     * "provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":9,"companyname":"贵州某家政公司9",
     * "companyrem":"防守打法","companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg",
     * "contactname":"莫大勇","contactphone":"18302562527","address":"weqe","status":"1",
     * "createdtime":1512554608000,"provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":10,
     * "companyname":"贵州某家政公司10","companyrem":"防守打法",
     * "companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg","contactname":"莫大勇",
     * "contactphone":"18302562527","address":"2hkhk","status":"1","createdtime":1512554608000,
     * "provname":"贵州省","cityname":"遵义市","countname":"遵义县"},{"id":11,"companyname":"贵州某家政公司11",
     * "companyrem":"防守打法","companyimge":"03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg",
     * "contactname":"莫大勇","contactphone":"18302562527","address":"hfg","status":"1",
     * "createdtime":1512554608000,"provname":"贵州省","cityname":"遵义市","countname":"遵义县"}]
     * allRow : 12
     * totalPage : 2
     * currentPage : 1
     * pageSize : 10
     * pageIndex : {"startindex":1,"endindex":2}
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
         * endindex : 2
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
         * id : 2
         * companyname : 贵州某家政公司2
         * companyrem : 防守打法
         * companyimge : 03C3D9EC-F349-4510-8CE5-5F65428D9D17.jpg
         * contactname : 莫大勇
         * contactphone : 18302562527
         * address : 防守打法
         * status : 1
         * createdtime : 1512554608000
         * provname : 贵州省
         * cityname : 遵义市
         * countname : 遵义县
         * companylogo
         */

        private int id;
        private String companyname;
        private String companyrem;
        private String companyimge;
        private String contactname;
        private String contactphone;
        private String address;
        private String status;
        private long createdtime;
        private String provname;
        private String cityname;
        private String countname;
        private String companylogo;

        public String getCompanylogo() {

            return companylogo;
        }

        public void setCompanylogo(String companylogo) {
            this.companylogo = companylogo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getCompanyrem() {
            return companyrem;
        }

        public void setCompanyrem(String companyrem) {
            this.companyrem = companyrem;
        }

        public String getCompanyimge() {
            return companyimge;
        }

        public void setCompanyimge(String companyimge) {
            this.companyimge = companyimge;
        }

        public String getContactname() {
            return contactname;
        }

        public void setContactname(String contactname) {
            this.contactname = contactname;
        }

        public String getContactphone() {
            return contactphone;
        }

        public void setContactphone(String contactphone) {
            this.contactphone = contactphone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getProvname() {
            return provname;
        }

        public void setProvname(String provname) {
            this.provname = provname;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getCountname() {
            return countname;
        }

        public void setCountname(String countname) {
            this.countname = countname;
        }
    }
}
