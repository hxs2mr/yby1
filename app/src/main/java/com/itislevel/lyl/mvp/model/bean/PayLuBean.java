package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class PayLuBean {

    /**
     * month : 7月
     * money : 666.70
     * list : [{"ordernum":"20180605010860014126786","usernum":"5093004201187","userid":65,"managerid":6,"vid":"5092862967456","provinceid":520000,"cityid":520100,"countyid":520102,"username":"潘毛","phone":"15285987378","status":1,"types":"1","hpayfee":"0.02","cpayfee":"0.00","paytotalfee":"0.02","paymethod":"1","trademode":"2","payfeetime":1530771309000,"createdtime":1528179309000,"liveaddress":"D栋三单元","month":null},{"ordernum":"20180605010860016569286","usernum":"5093004201187","userid":65,"managerid":6,"vid":"5092862967456","provinceid":520000,"cityid":520100,"countyid":520102,"username":"潘毛","phone":"15285987378","status":1,"types":"2","hpayfee":"0.00","cpayfee":"333.33","paytotalfee":"333.33","paymethod":"1","trademode":"2","payfeetime":1530771309000,"createdtime":1528189174000,"liveaddress":"D栋三单元","month":null}]
     */

    private String month;
    private String money;
    private List<ListBean> list;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * ordernum : 20180605010860014126786
         * usernum : 5093004201187
         * userid : 65
         * managerid : 6
         * vid : 5092862967456
         * provinceid : 520000
         * cityid : 520100
         * countyid : 520102
         * username : 潘毛
         * phone : 15285987378
         * status : 1
         * types : 1
         * hpayfee : 0.02
         * cpayfee : 0.00
         * paytotalfee : 0.02
         * paymethod : 1
         * trademode : 2
         * payfeetime : 1530771309000
         * createdtime : 1528179309000
         * liveaddress : D栋三单元
         * month : null
         */

        private String ordernum;
        private String usernum;
        private int userid;
        private int managerid;
        private String vid;
        private int provinceid;
        private int cityid;
        private int countyid;
        private String username;
        private String phone;
        private int status;
        private String types;
        private String hpayfee;
        private String cpayfee;
        private String paytotalfee;
        private String paymethod;
        private String trademode;
        private long payfeetime;
        private long createdtime;
        private String liveaddress;
        private Object month;

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getUsernum() {
            return usernum;
        }

        public void setUsernum(String usernum) {
            this.usernum = usernum;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public int getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(int provinceid) {
            this.provinceid = provinceid;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getHpayfee() {
            return hpayfee;
        }

        public void setHpayfee(String hpayfee) {
            this.hpayfee = hpayfee;
        }

        public String getCpayfee() {
            return cpayfee;
        }

        public void setCpayfee(String cpayfee) {
            this.cpayfee = cpayfee;
        }

        public String getPaytotalfee() {
            return paytotalfee;
        }

        public void setPaytotalfee(String paytotalfee) {
            this.paytotalfee = paytotalfee;
        }

        public String getPaymethod() {
            return paymethod;
        }

        public void setPaymethod(String paymethod) {
            this.paymethod = paymethod;
        }

        public String getTrademode() {
            return trademode;
        }

        public void setTrademode(String trademode) {
            this.trademode = trademode;
        }

        public long getPayfeetime() {
            return payfeetime;
        }

        public void setPayfeetime(long payfeetime) {
            this.payfeetime = payfeetime;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public String getLiveaddress() {
            return liveaddress;
        }

        public void setLiveaddress(String liveaddress) {
            this.liveaddress = liveaddress;
        }

        public Object getMonth() {
            return month;
        }

        public void setMonth(Object month) {
            this.month = month;
        }
    }
}
