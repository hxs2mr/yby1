package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\23 0023.
 */

public class PropertLoginBean {


    /**
     * getImgUrl : http://readimg.yobangyo.com/yby/img_server/
     * phone : 18385655626
     * token : 33B6953FB8296650696C8143D9F8D3313E2DAC4B018A23B99DD998058F0F1A0011236BDAD3430DC6DBB716F15988A6A792B085565D32566F5E7CE53E9CD6B8FA
     * saveImgUrl : http://saveimg.yobangyo.com/img-server/uploadImgs?token=1522809067997
     * list : [{"userid":0,"managerid":0,"vid":null,"provinceid":0,"cityid":0,"countyid":0,"provname":null,"cityname":null,"cuntyname":null,"villagename":null,"headurl":null,"usernum":null,"idcard":null,"username":"匿名","phone":"18385655626","status":1,"houseispay":0,"ishouse":1,"housepaytype":0,"houseareas":"0.00","houseunitfee":"0.00","housetotalfee":"0.00","housepaylimit":"0.00","carispay":0,"iscar":1,"carfee":"0.00","carpaytype":0,"carpaylimit":"0.00","latefee":"0.00","liveaddress":null,"remark":null,"hbilltime":null,"cbilltime":null,"createdtime":1527748002911}]
     */

    private String getImgUrl;
    private String phone;
    private String token;
    private String saveImgUrl;
    private List<ListBean> list;

    public String getGetImgUrl() {
        return getImgUrl;
    }

    public void setGetImgUrl(String getImgUrl) {
        this.getImgUrl = getImgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSaveImgUrl() {
        return saveImgUrl;
    }

    public void setSaveImgUrl(String saveImgUrl) {
        this.saveImgUrl = saveImgUrl;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * userid : 0
         * managerid : 0
         * vid : null
         * provinceid : 0
         * cityid : 0
         * countyid : 0
         * provname : null
         * cityname : null
         * cuntyname : null
         * villagename : null
         * headurl : null
         * usernum : null
         * idcard : null
         * username : 匿名
         * phone : 18385655626
         * status : 1
         * houseispay : 0
         * ishouse : 1
         * housepaytype : 0
         * houseareas : 0.00
         * houseunitfee : 0.00
         * housetotalfee : 0.00
         * housepaylimit : 0.00
         * carispay : 0
         * iscar : 1
         * carfee : 0.00
         * carpaytype : 0
         * carpaylimit : 0.00
         * latefee : 0.00
         * liveaddress : null
         * remark : null
         * hbilltime : null
         * cbilltime : null
         * createdtime : 1527748002911
         */

        private int userid;
        private int managerid;
        private Object vid;
        private int provinceid;
        private int cityid;
        private int countyid;
        private Object provname;
        private Object cityname;
        private Object cuntyname;
        private Object villagename;
        private Object headurl;
        private Object usernum;
        private Object idcard;
        private String username;
        private String phone;
        private int status;
        private int houseispay;
        private int ishouse;
        private int housepaytype;
        private String houseareas;
        private String houseunitfee;
        private String housetotalfee;
        private String housepaylimit;
        private int carispay;
        private int iscar;
        private String carfee;
        private int carpaytype;
        private String carpaylimit;
        private String latefee;
        private Object liveaddress;
        private Object remark;
        private Object hbilltime;
        private Object cbilltime;
        private long createdtime;

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

        public Object getVid() {
            return vid;
        }

        public void setVid(Object vid) {
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

        public Object getProvname() {
            return provname;
        }

        public void setProvname(Object provname) {
            this.provname = provname;
        }

        public Object getCityname() {
            return cityname;
        }

        public void setCityname(Object cityname) {
            this.cityname = cityname;
        }

        public Object getCuntyname() {
            return cuntyname;
        }

        public void setCuntyname(Object cuntyname) {
            this.cuntyname = cuntyname;
        }

        public Object getVillagename() {
            return villagename;
        }

        public void setVillagename(Object villagename) {
            this.villagename = villagename;
        }

        public Object getHeadurl() {
            return headurl;
        }

        public void setHeadurl(Object headurl) {
            this.headurl = headurl;
        }

        public Object getUsernum() {
            return usernum;
        }

        public void setUsernum(Object usernum) {
            this.usernum = usernum;
        }

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard = idcard;
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

        public int getHouseispay() {
            return houseispay;
        }

        public void setHouseispay(int houseispay) {
            this.houseispay = houseispay;
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

        public int getCarispay() {
            return carispay;
        }

        public void setCarispay(int carispay) {
            this.carispay = carispay;
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

        public int getCarpaytype() {
            return carpaytype;
        }

        public void setCarpaytype(int carpaytype) {
            this.carpaytype = carpaytype;
        }

        public String getCarpaylimit() {
            return carpaylimit;
        }

        public void setCarpaylimit(String carpaylimit) {
            this.carpaylimit = carpaylimit;
        }

        public String getLatefee() {
            return latefee;
        }

        public void setLatefee(String latefee) {
            this.latefee = latefee;
        }

        public Object getLiveaddress() {
            return liveaddress;
        }

        public void setLiveaddress(Object liveaddress) {
            this.liveaddress = liveaddress;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getHbilltime() {
            return hbilltime;
        }

        public void setHbilltime(Object hbilltime) {
            this.hbilltime = hbilltime;
        }

        public Object getCbilltime() {
            return cbilltime;
        }

        public void setCbilltime(Object cbilltime) {
            this.cbilltime = cbilltime;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }
    }
}
