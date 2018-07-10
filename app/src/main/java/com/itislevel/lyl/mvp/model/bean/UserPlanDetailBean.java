package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\7 0007 12:00
 */
public class UserPlanDetailBean {

    /**
     * count : 2
     * list : [{"periods":6,"status":"1","cashbacklastdate":"2018-07-16","periodid":116,"perperiodlimit":"42.67","cashbackdate":"2018-07-06"},{"periods":6,"status":"0","cashbacklastdate":"2018-08-16","periodid":117,"perperiodlimit":"42.67","cashbackdate":"2018-08-06"},{"periods":6,"status":"0","cashbacklastdate":"2018-09-16","periodid":118,"perperiodlimit":"42.67","cashbackdate":"2018-09-06"},{"periods":6,"status":"0","cashbacklastdate":"2018-10-16","periodid":119,"perperiodlimit":"42.67","cashbackdate":"2018-10-06"},{"periods":6,"status":"2","cashbacklastdate":"2018-05-16","periodid":120,"perperiodlimit":"42.67","cashbackdate":"2018-05-06"},{"periods":6,"status":"3","cashbacklastdate":"2018-06-16","periodid":121,"perperiodlimit":"42.67","cashbackdate":"2018-06-06"}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * periods : 6
         * status : 1
         * cashbacklastdate : 2018-07-16
         * periodid : 116
         * perperiodlimit : 42.67
         * cashbackdate : 2018-07-06
         */

        private int periods;
        private String status;
        private String cashbacklastdate;
        private int periodid;
        private String perperiodlimit;
        private String cashbackdate;

        public int getPeriods() {
            return periods;
        }

        public void setPeriods(int periods) {
            this.periods = periods;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCashbacklastdate() {
            return cashbacklastdate;
        }

        public void setCashbacklastdate(String cashbacklastdate) {
            this.cashbacklastdate = cashbacklastdate;
        }

        public int getPeriodid() {
            return periodid;
        }

        public void setPeriodid(int periodid) {
            this.periodid = periodid;
        }

        public String getPerperiodlimit() {
            return perperiodlimit;
        }

        public void setPerperiodlimit(String perperiodlimit) {
            this.perperiodlimit = perperiodlimit;
        }

        public String getCashbackdate() {
            return cashbackdate;
        }

        public void setCashbackdate(String cashbackdate) {
            this.cashbackdate = cashbackdate;
        }
    }
}
