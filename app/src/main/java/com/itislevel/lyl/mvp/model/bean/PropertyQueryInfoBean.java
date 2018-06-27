package com.itislevel.lyl.mvp.model.bean;

import java.util.Date;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-11.13:53
 * path:com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean
 **/
public class PropertyQueryInfoBean {

    /**
     * estateinfo : {"userid":12,"provinceid":520000,"cityid":520100,"countyid":520101,
     * "estatearea":"物业超管","headurl":"DDBE737B-DF16-4C9C-8F95-5BCEFC5EDEB9.jpg","ishouse":1,
     * "housepaytype":3,"houseareas":"100.0","houseunitfee":"2.0","housetotalfee":"200.00",
     * "housepaylimit":"50.00","iscar":1,"carfee":"20.0","carpaylimit":"5.00","carpaytype":3,
     * "liveaddress":"Z栋Z单元","username":"刘一","phone":"13600000000",
     * "payBilllist":[{"ordernum":"20180111010230010395223","userid":12,"usernum":"6C68454373B5",
     * "username":"刘一","phone":"13600000000","status":0,"type":1,"paytype":3,"payfee":"50.00",
     * "estatearea":"物业超管","payunit":"贵州友邦有网络科技","payfeebegintime":"2018-01-11",
     * "payfeefinishtime":"2018-04-11"},{"ordernum":"20180111010410010395241","userid":12,
     * "usernum":"6C68454373B5","username":"刘一","phone":"13600000000","status":0,"type":2,
     * "paytype":3,"payfee":"5.00","estatearea":"物业超管","payunit":"贵州友邦有网络科技",
     * "payfeebegintime":"2018-01-11","payfeefinishtime":"2018-04-11"}]}
     */

    private EstateinfoBean estateinfo;

    public EstateinfoBean getEstateinfo() {
        return estateinfo;
    }

    public void setEstateinfo(EstateinfoBean estateinfo) {
        this.estateinfo = estateinfo;
    }

    public static class EstateinfoBean {
        /**
         * userid : 12
         * provinceid : 520000
         * cityid : 520100
         * countyid : 520101
         * estatearea : 物业超管
         * headurl : DDBE737B-DF16-4C9C-8F95-5BCEFC5EDEB9.jpg
         * ishouse : 1
         * housepaytype : 3
         * houseareas : 100.0
         * houseunitfee : 2.0
         * housetotalfee : 200.00
         * housepaylimit : 50.00
         * iscar : 1
         * carfee : 20.0
         * carpaylimit : 5.00
         * carpaytype : 3
         * liveaddress : Z栋Z单元
         * username : 刘一
         * phone : 13600000000
         * payBilllist : [{"ordernum":"20180111010230010395223","userid":12,
         * "usernum":"6C68454373B5","username":"刘一","phone":"13600000000","status":0,"type":1,
         * "paytype":3,"payfee":"50.00","estatearea":"物业超管","payunit":"贵州友邦有网络科技",
         * "payfeebegintime":"2018-01-11","payfeefinishtime":"2018-04-11"},
         * {"ordernum":"20180111010410010395241","userid":12,"usernum":"6C68454373B5",
         * "username":"刘一","phone":"13600000000","status":0,"type":2,"paytype":3,"payfee":"5.00",
         * "estatearea":"物业超管","payunit":"贵州友邦有网络科技","payfeebegintime":"2018-01-11",
         * "payfeefinishtime":"2018-04-11"}]
         */

        private int userid;
        private int provinceid;
        private int cityid;
        private int countyid;
        private String estatearea;
        private String headurl;
        private int ishouse;
        private int housepaytype;
        private String houseareas;
        private String houseunitfee;
        private String housetotalfee;
        private String housepaylimit;
        private int iscar;
        private String carfee;
        private String carpaylimit;
        private int carpaytype;
        private String liveaddress;
        private String username;
        private String phone;
        private String estatecompany;

        private List<PayBilllistBean> payBilllist;

        public String getEstatecompany() {
            return estatecompany;
        }

        public void setEstatecompany(String estatecompany) {
            this.estatecompany = estatecompany;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public String getEstatearea() {
            return estatearea;
        }

        public void setEstatearea(String estatearea) {
            this.estatearea = estatearea;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public int getIshouse() {
            return ishouse;
        }

        public void setIshouse(int ishouse) {
            this.ishouse = ishouse;
        }

        public int getHousepaytype() {
            return housepaytype;
        }

        public void setHousepaytype(int housepaytype) {
            this.housepaytype = housepaytype;
        }

        public String getHouseareas() {
            return houseareas;
        }

        public void setHouseareas(String houseareas) {
            this.houseareas = houseareas;
        }

        public String getHouseunitfee() {
            return houseunitfee;
        }

        public void setHouseunitfee(String houseunitfee) {
            this.houseunitfee = houseunitfee;
        }

        public String getHousetotalfee() {
            return housetotalfee;
        }

        public void setHousetotalfee(String housetotalfee) {
            this.housetotalfee = housetotalfee;
        }

        public String getHousepaylimit() {
            return housepaylimit;
        }

        public void setHousepaylimit(String housepaylimit) {
            this.housepaylimit = housepaylimit;
        }

        public int getIscar() {
            return iscar;
        }

        public void setIscar(int iscar) {
            this.iscar = iscar;
        }

        public String getCarfee() {
            return carfee;
        }

        public void setCarfee(String carfee) {
            this.carfee = carfee;
        }

        public String getCarpaylimit() {
            return carpaylimit;
        }

        public void setCarpaylimit(String carpaylimit) {
            this.carpaylimit = carpaylimit;
        }

        public int getCarpaytype() {
            return carpaytype;
        }

        public void setCarpaytype(int carpaytype) {
            this.carpaytype = carpaytype;
        }

        public String getLiveaddress() {
            return liveaddress;
        }

        public void setLiveaddress(String liveaddress) {
            this.liveaddress = liveaddress;
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

        public List<PayBilllistBean> getPayBilllist() {
            return payBilllist;
        }

        public void setPayBilllist(List<PayBilllistBean> payBilllist) {
            this.payBilllist = payBilllist;
        }

        public static class PayBilllistBean {
            /**
             * ordernum : 20180111010230010395223
             * userid : 12
             * usernum : 6C68454373B5
             * username : 刘一
             * phone : 13600000000
             * status : 0
             * type : 1
             * paytype : 3
             * payfee : 50.00
             * estatearea : 物业超管
             * payunit : 贵州友邦有网络科技
             * payfeebegintime : 2018-01-11
             * payfeefinishtime : 2018-04-11
             */

            private boolean isSelected;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            private String ordernum;
            private int userid;
            private String usernum;
            private String username;
            private String phone;
            private int status;
            private int type;
            private int paytype;
            private String payfee;
            private String estatearea;
            private String payunit;
            private Date payfeebegintime;
            private Date payfeefinishtime;

            public String getOrdernum() {
                return ordernum;
            }

            public void setOrdernum(String ordernum) {
                this.ordernum = ordernum;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getUsernum() {
                return usernum;
            }

            public void setUsernum(String usernum) {
                this.usernum = usernum;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getPaytype() {
                return paytype;
            }

            public void setPaytype(int paytype) {
                this.paytype = paytype;
            }

            public String getPayfee() {
                return payfee;
            }

            public void setPayfee(String payfee) {
                this.payfee = payfee;
            }

            public String getEstatearea() {
                return estatearea;
            }

            public void setEstatearea(String estatearea) {
                this.estatearea = estatearea;
            }

            public String getPayunit() {
                return payunit;
            }

            public void setPayunit(String payunit) {
                this.payunit = payunit;
            }

            public Date getPayfeebegintime() {
                return payfeebegintime;
            }

            public void setPayfeebegintime(Date payfeebegintime) {
                this.payfeebegintime = payfeebegintime;
            }

            public Date getPayfeefinishtime() {
                return payfeefinishtime;
            }

            public void setPayfeefinishtime(Date payfeefinishtime) {
                this.payfeefinishtime = payfeefinishtime;
            }
        }
    }
}
