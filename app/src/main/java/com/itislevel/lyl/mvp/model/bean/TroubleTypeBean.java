package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/22.9:31
 * path:com.itislevel.lyl.mvp.model.bean.TroubleTypeBean
 **/
public class TroubleTypeBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * icon : A7299AD6-06FC-4821-90D6-F8C65314ED7B.png
         * firstid : 2
         * secondid : 2
         * catename : 官司咨询
         */

        private String icon;
        private int firstid;
        private int secondid;
        private String catename;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getFirstid() {
            return firstid;
        }

        public void setFirstid(int firstid) {
            this.firstid = firstid;
        }

        public int getSecondid() {
            return secondid;
        }

        public void setSecondid(int secondid) {
            this.secondid = secondid;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }
    }
}
