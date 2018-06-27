package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\23 0023.
 */

public class PropretyNoticeBean {
    /**
     * list : [{"id":1,"managerid":0,"vid":"3634247983126","provid":0,"cityid":520100,"countyid":0,"looknum":0,"titile":"花果园m区停水通知","villagename":"花果园小区","headurl":"D5177259-0402-40DC-B844-A480FEC44736.jpg","estatecompany":"深圳龙城","content":"叼毛们，要停水了，自己备好水桶，马桶备用。 时间2018-02 -9:00到2018-03-16：00","createdtime":1526960330000},{"id":2,"managerid":0,"vid":"3634247983126","provid":0,"cityid":520100,"countyid":0,"looknum":0,"titile":"大世界小区整改道路通知","villagename":"花果园小区","headurl":"D5177259-0402-40DC-B844-A480FEC44736.jpg","estatecompany":"深圳龙城","content":"各位有车的怂包们：\n    由于车位需要，道路需要整个，施工完成时间另行通知，如果打扰到大家请谅解，叼毛们就请飞过去，后果自负。谢谢合作。","createdtime":1526960575000},{"id":3,"managerid":0,"vid":"3634247983126","provid":0,"cityid":520100,"countyid":0,"looknum":0,"titile":"123","villagename":"花果园小区","headurl":"D5177259-0402-40DC-B844-A480FEC44736.jpg","estatecompany":"深圳龙城","content":"   123","createdtime":1526973435000}]
     * allRow : 3
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
         * managerid : 0
         * vid : 3634247983126
         * provid : 0
         * cityid : 520100
         * countyid : 0
         * looknum : 0
         * titile : 花果园m区停水通知
         * villagename : 花果园小区
         * headurl : D5177259-0402-40DC-B844-A480FEC44736.jpg
         * estatecompany : 深圳龙城
         * content : 叼毛们，要停水了，自己备好水桶，马桶备用。 时间2018-02 -9:00到2018-03-16：00
         * createdtime : 1526960330000
         */

        private int id;
        private int managerid;
        private String vid;
        private int provid;
        private int cityid;
        private int countyid;
        private int looknum;
        private String titile;
        private String villagename;
        private String headurl;
        private String estatecompany;
        private String content;
        private long createdtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getManagerid() {
            return managerid;
        }

        public void setManagerid(int managerid) {
            this.managerid = managerid;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
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

        public int getLooknum() {
            return looknum;
        }

        public void setLooknum(int looknum) {
            this.looknum = looknum;
        }

        public String getTitile() {
            return titile;
        }

        public void setTitile(String titile) {
            this.titile = titile;
        }

        public String getVillagename() {
            return villagename;
        }

        public void setVillagename(String villagename) {
            this.villagename = villagename;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public String getEstatecompany() {
            return estatecompany;
        }

        public void setEstatecompany(String estatecompany) {
            this.estatecompany = estatecompany;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }
    }
}
