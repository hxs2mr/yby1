package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/9.9:54
 * path:com.itislevel.lyl.mvp.model.bean.FunsharingListBean
 **/
public class FunsharingListBean implements MultiItemEntity {
    /**
     * list : [{"id":12,"userid":3,"username":"itisi1","nickname":"itisi","addressid":null,
     * "content":"我要分享","imge":"\"null\"","video":null,"status":"1","createdtime":1513233691000,
     * "useridfabulous":null,"fabulousnumber":2,"islike":false,"parentid":null,"parentname":null,
     * "comment":null,"comments":[{"id":124,"shareid":12,"userid":3,"touserid":0,
     * "username":"itisi1","nickname":"itisi","tonickname":null,"parentid":"","parentname":"",
     * "comment":"明年","fabulous":"0","status":"1","createdtime":null,"comments1":[]},{"id":109,
     * "shareid":12,"userid":4,"touserid":0,"username":"Joke","nickname":"joke",
     * "tonickname":null,"parentid":"","parentname":"","comment":"陌陌","fabulous":"0",
     * "status":"1","createdtime":null,"comments1":[{"id":112,"shareid":12,"userid":0,
     * "touserid":0,"username":null,"nickname":"joke","tonickname":null,"parentid":null,
     * "parentname":null,"comment":"陌陌一","fabulous":"1","status":"1","createdtime":1513255933000,
     * "comments1":null},{"id":123,"shareid":12,"userid":0,"touserid":0,"username":null,
     * "nickname":"joke","tonickname":null,"parentid":null,"parentname":null,"comment":"陌陌二",
     * "fabulous":"1","status":"1","createdtime":1513258715000,"comments1":null}]},{"id":104,
     * "shareid":12,"userid":3,"touserid":0,"username":"itisi1","nickname":"itisi",
     * "tonickname":null,"parentid":"","parentname":"","comment":"喝两口","fabulous":"0",
     * "status":"1","createdtime":null,"comments1":[]},{"id":1,"shareid":12,"userid":3,
     * "touserid":0,"username":"itisi1","nickname":"itisi","tonickname":null,"parentid":"",
     * "parentname":"","comment":"HelloKitty","fabulous":"0","status":"1","createdtime":null,
     * "comments1":[{"id":126,"shareid":5,"userid":0,"touserid":0,"username":null,
     * "nickname":"itisi","tonickname":null,"parentid":null,"parentname":null,"comment":"给您你就是",
     * "fabulous":"1","status":"1","createdtime":1513261517000,"comments1":null},{"id":127,
     * "shareid":5,"userid":0,"touserid":0,"username":null,"nickname":"itisi","tonickname":null,
     * "parentid":null,"parentname":null,"comment":"考虑考虑","fabulous":"1","status":"1",
     * "createdtime":1513261530000,"comments1":null}]}]}]
     * allRow : 12
     * totalPage : 12
     * currentPage : 1
     * pageSize : 1
     * pageIndex : {"startindex":1,"endindex":10}
     */

    private int allRow;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    private PageIndexBean pageIndex;
    private List<FunshingItemBean> list;

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


    public List<FunshingItemBean> getList() {
        return list;
    }

    public void setList(List<FunshingItemBean> list){
        this.list = list;
    }

    @Override
    public int getItemType() {
        return 0;
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

}
