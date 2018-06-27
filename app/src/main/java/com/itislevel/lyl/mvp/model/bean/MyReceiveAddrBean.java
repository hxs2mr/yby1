package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.11:39
 * path:com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean
 **/
public class MyReceiveAddrBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * provname : 山西省
         * detailaddress : 详细地址是
         * phone : 13333333333
         * countname : 屯留县
         * cityname : 长治市
         * id : 2
         * cityid : 140400
         * countid : 140424
         * provid : 140000
         * username : 周末
         */

        private String provname;
        private String detailaddress;
        private String phone;
        private String countname;
        private String cityname;
        private int id;
        private int cityid;
        private int countid;
        private int provid;
        private String username;

        public String getProvname() {
            return provname;
        }

        public void setProvname(String provname) {
            this.provname = provname;
        }

        public String getDetailaddress() {
            return detailaddress;
        }

        public void setDetailaddress(String detailaddress) {
            this.detailaddress = detailaddress;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCountname() {
            return countname;
        }

        public void setCountname(String countname) {
            this.countname = countname;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public int getCountid() {
            return countid;
        }

        public void setCountid(int countid) {
            this.countid = countid;
        }

        public int getProvid() {
            return provid;
        }

        public void setProvid(int provid) {
            this.provid = provid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
