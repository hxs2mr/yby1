package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class DynamicPersonBean implements MultiItemEntity {

    /**
     * list : [{"shmlist":[],"nickname":"pmg123456","status":"1","userid":39,"img_h":"621","provid":520000,"cityid":520100,"id":1,"content":"哈哈哈有点","giftnum":0,"username":"pmg123456","imge":"EA73422C-2EDE-4E0D-8FB2-AC1DDF8C595E.jpeg","provincename":"贵州","createdtime":1524810659000,"looknum":0,"isfollow":"0","cityname":"贵阳","useridfabulous":null,"ispoint":"0","nmpointlist":null,"img_w":"828","fabulousnumber":0,"imgurl":null}]
     * allRow : 0
     * totalPage : 0
     * currentPage : 1
     * pageSize : 10
     * pageIndex : {"startindex":1,"endindex":0}
     */
    private int allRow;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    private FollowListBean.PageIndexBean pageIndex;
    private List<ListPersonBean> list;
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

    public FollowListBean.PageIndexBean getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(FollowListBean.PageIndexBean pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<ListPersonBean> getList() {
        return list;
    }

    public void setList(List<ListPersonBean> list) {
        this.list = list;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public static class PageIndexBean {
        /**
         * startindex : 1
         * endindex : 0
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
