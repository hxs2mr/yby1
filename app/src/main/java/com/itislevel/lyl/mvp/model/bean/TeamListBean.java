package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.17:19
 * path:com.itislevel.lyl.mvp.model.bean.TeamListBean
 **/
public class TeamListBean {


    /**
     * list : [{"id":1,"adviserid":6,"name":"舒畅","sex":"0",
     * "photo":"61A5B4E5-9FE9-4519-8DEE-7841B82B535B.jpg","idcard":"522321198710300031",
     * "phone":"15585963111","firstcateid":"1","personalcv":"是还好还好哈",
     * "certificate":"DF89A3A8-CD2F-449F-A35F-F8AAD13200B3.jpg","status":"1","ischeck":"1",
     * "createdtime":1517445119000,"cityid":522300,"provid":520000,"provname":"贵州省",
     * "cityname":"黔西南自治州","seeknum":0,"checktime":1517449601000},{"id":2,"adviserid":10,
     * "name":"中国","sex":"0","photo":"6BFBA48C-7E16-4272-87E8-43B389A81D90.jpg",
     * "idcard":"522228","phone":"13355555555","firstcateid":"1","personalcv":"没有经历",
     * "certificate":"EAA5FAEB-9FDF-45A3-8A6C-75728B3F235B.jpg","status":"1","ischeck":"1",
     * "createdtime":1517453482000,"cityid":520100,"provid":520000,"provname":"贵州省",
     * "cityname":"贵阳市","seeknum":0,"checktime":1517453561000}]
     * allRow : 2
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
         * id : 1
         * adviserid : 6
         * name : 舒畅
         * sex : 0
         * photo : 61A5B4E5-9FE9-4519-8DEE-7841B82B535B.jpg
         * idcard : 522321198710300031
         * phone : 15585963111
         * firstcateid : 1
         * personalcv : 是还好还好哈
         * certificate : DF89A3A8-CD2F-449F-A35F-F8AAD13200B3.jpg
         * status : 1
         * ischeck : 1
         * createdtime : 1517445119000
         * cityid : 522300
         * provid : 520000
         * provname : 贵州省
         * cityname : 黔西南自治州
         * seeknum : 0
         * checktime : 1517449601000
         */

        private int id;
        private int adviserid;
        private String name;
        private String sex;
        private String photo;
        private String idcard;
        private String phone;
        private String firstcateid;
        private String personalcv;
        private String certificate;
        private String status;
        private String ischeck;
        private long createdtime;
        private int cityid;
        private int provid;
        private String provname;
        private String cityname;
        private int seeknum;
        private long checktime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdviserid() {
            return adviserid;
        }

        public void setAdviserid(int adviserid) {
            this.adviserid = adviserid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFirstcateid() {
            return firstcateid;
        }

        public void setFirstcateid(String firstcateid) {
            this.firstcateid = firstcateid;
        }

        public String getPersonalcv() {
            return personalcv;
        }

        public void setPersonalcv(String personalcv) {
            this.personalcv = personalcv;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public int getProvid() {
            return provid;
        }

        public void setProvid(int provid) {
            this.provid = provid;
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

        public int getSeeknum() {
            return seeknum;
        }

        public void setSeeknum(int seeknum) {
            this.seeknum = seeknum;
        }

        public long getChecktime() {
            return checktime;
        }

        public void setChecktime(long checktime) {
            this.checktime = checktime;
        }
    }
}
