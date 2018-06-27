package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class RepairListBean {

    /**
     * list : [{"rwid":1,"managerid":5,"realname":"刘小飞","mobilephone":"13625495284","sex":1,"age":30,"score":0,"provid":520000,"cityid":520100,"countyid":520151,"provname":"贵州","cityname":"贵阳","cuntyname":"观山湖","servicearea":"观山湖","looknum":0,"recateids":"@1@,@3@,@5@,@7@,@8@","headurl":"99597BE6-A96B-4EAE-BEFF-0815DD933AD8.1","enabled":"1","comefrom":"1","ischeck":"2","checkcause":"请输入备注","workrem":"从事维修工作多年，熟悉各种家居维修，工作经验丰富，工作认真负责，服务态度好","address":"贵州省贵阳市","createdtime":1527840844000,"catelist":[{"id":1,"catename":"综合维修工","sign":null,"createdtime":null},{"id":3,"catename":"电工 ","sign":null,"createdtime":null},{"id":5,"catename":"空调工 ","sign":null,"createdtime":null},{"id":7,"catename":"电梯工 ","sign":null,"createdtime":null},{"id":8,"catename":"万能工","sign":null,"createdtime":null}]}]
     * allRow : 1
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
         * rwid : 1
         * managerid : 5
         * realname : 刘小飞
         * mobilephone : 13625495284
         * sex : 1
         * age : 30
         * score : 0
         * provid : 520000
         * cityid : 520100
         * countyid : 520151
         * provname : 贵州
         * cityname : 贵阳
         * cuntyname : 观山湖
         * servicearea : 观山湖
         * looknum : 0
         * recateids : @1@,@3@,@5@,@7@,@8@
         * headurl : 99597BE6-A96B-4EAE-BEFF-0815DD933AD8.1
         * enabled : 1
         * comefrom : 1
         * ischeck : 2
         * checkcause : 请输入备注
         * workrem : 从事维修工作多年，熟悉各种家居维修，工作经验丰富，工作认真负责，服务态度好
         * address : 贵州省贵阳市
         * createdtime : 1527840844000
         * catelist : [{"id":1,"catename":"综合维修工","sign":null,"createdtime":null},{"id":3,"catename":"电工 ","sign":null,"createdtime":null},{"id":5,"catename":"空调工 ","sign":null,"createdtime":null},{"id":7,"catename":"电梯工 ","sign":null,"createdtime":null},{"id":8,"catename":"万能工","sign":null,"createdtime":null}]
         */

        private int rwid;
        private int managerid;
        private String realname;
        private String mobilephone;
        private int sex;
        private int age;
        private int score;
        private int provid;
        private int cityid;
        private int countyid;
        private String provname;
        private String cityname;
        private String cuntyname;
        private String servicearea;
        private int looknum;
        private String recateids;
        private String headurl;
        private String enabled;
        private String comefrom;
        private String ischeck;
        private String checkcause;
        private String workrem;
        private String address;
        private long createdtime;
        private List<CatelistBean> catelist;
        /**
         * scoreAverage : 9.0
         * noStoreUp : 0
         */
        private String scoreAverage;
        private String noStoreUp;
        private String repair_share_url;

        public String getRepair_share_url() {
            return repair_share_url;
        }

        public void setRepair_share_url(String repair_share_url) {
            this.repair_share_url = repair_share_url;
        }

        public int getRwid() {
            return rwid;
        }

        public void setRwid(int rwid) {
            this.rwid = rwid;
        }

        public int getManagerid() {
            return managerid;
        }

        public void setManagerid(int managerid) {
            this.managerid = managerid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getProvid() {
            return provid;
        }

        public void setProvid(int provid) {
            this.provid = provid;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public int getCountyid() {
            return countyid;
        }

        public void setCountyid(int countyid) {
            this.countyid = countyid;
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

        public String getCuntyname() {
            return cuntyname;
        }

        public void setCuntyname(String cuntyname) {
            this.cuntyname = cuntyname;
        }

        public String getServicearea() {
            return servicearea;
        }

        public void setServicearea(String servicearea) {
            this.servicearea = servicearea;
        }

        public int getLooknum() {
            return looknum;
        }

        public void setLooknum(int looknum) {
            this.looknum = looknum;
        }

        public String getRecateids() {
            return recateids;
        }

        public void setRecateids(String recateids) {
            this.recateids = recateids;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getComefrom() {
            return comefrom;
        }

        public void setComefrom(String comefrom) {
            this.comefrom = comefrom;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCheckcause() {
            return checkcause;
        }

        public void setCheckcause(String checkcause) {
            this.checkcause = checkcause;
        }

        public String getWorkrem() {
            return workrem;
        }

        public void setWorkrem(String workrem) {
            this.workrem = workrem;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public List<CatelistBean> getCatelist() {
            return catelist;
        }

        public void setCatelist(List<CatelistBean> catelist) {
            this.catelist = catelist;
        }

        public String getScoreAverage() {
            return scoreAverage;
        }

        public void setScoreAverage(String scoreAverage) {
            this.scoreAverage = scoreAverage;
        }

        public String getNoStoreUp() {
            return noStoreUp;
        }

        public void setNoStoreUp(String noStoreUp) {
            this.noStoreUp = noStoreUp;
        }

    }
}
