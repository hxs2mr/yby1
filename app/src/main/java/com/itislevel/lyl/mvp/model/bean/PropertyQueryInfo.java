package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-03.10:56
 * path:com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo
 **/
public class PropertyQueryInfo {

    private List<OwnerplacelistBean> ownerplacelist;

    public List<OwnerplacelistBean> getOwnerplacelist() {
        return ownerplacelist;
    }

    public void setOwnerplacelist(List<OwnerplacelistBean> ownerplacelist) {
        this.ownerplacelist = ownerplacelist;
    }

    public static class OwnerplacelistBean {
        /**
         * userid : 1
         * liveaddress : [友帮友科技]A栋二单元
         */

        private int userid;
        private String liveaddress;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getLiveaddress() {
            return liveaddress;
        }

        public void setLiveaddress(String liveaddress) {
            this.liveaddress = liveaddress;
        }
    }
}
