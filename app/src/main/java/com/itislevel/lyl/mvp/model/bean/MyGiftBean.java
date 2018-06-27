package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.14:14
 * path:com.itislevel.lyl.mvp.model.bean.MyGiftBean
 **/
public class MyGiftBean {

    private List<ListputBean> listput;

    public List<ListputBean> getListput() {
        return listput;
    }

    public void setListput(List<ListputBean> listput) {
        this.listput = listput;
    }

    public static class ListputBean {
        /**
         * icon : ADFBA71F-540E-47F6-B39D-329B746F73C2.png
         * count : 2
         * money : 10
         * ticketid : 10yuan
         */

        private String icon;
        private int count;
        private String money;
        private String ticketid;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTicketid() {
            return ticketid;
        }

        public void setTicketid(String ticketid) {
            this.ticketid = ticketid;
        }
    }
}
