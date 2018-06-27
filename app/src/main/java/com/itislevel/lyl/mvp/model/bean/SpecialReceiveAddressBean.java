package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-20.10:21
 * path:com.itislevel.lyl.mvp.model.bean.SpecialTypeBean
 **/
public class SpecialReceiveAddressBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * username : 周易
         * phone : 15533333333
         * countname : 乌当区
         * provname : 贵州省
         * cityname : 贵阳市
         * provid : 520000
         * detailaddress : 详细地址
         * countid : 520112
         * cityid : 520100
         */

        private String username;
        private String phone;
        private String countname;
        private String provname;
        private String cityname;
        private int provid;
        private String detailaddress;
        private int countid;
        private int cityid;

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

        public String getCountname() {
            return countname;
        }

        public void setCountname(String countname) {
            this.countname = countname;
        }

        public String getProvname() {
            return provname;
        }

        public void setProvname(String provname) {
            this.provname = provname;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public int getProvid() {
            return provid;
        }

        public void setProvid(int provid) {
            this.provid = provid;
        }

        public String getDetailaddress() {
            return detailaddress;
        }

        public void setDetailaddress(String detailaddress) {
            this.detailaddress = detailaddress;
        }

        public int getCountid() {
            return countid;
        }

        public void setCountid(int countid) {
            this.countid = countid;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }
    }
}
