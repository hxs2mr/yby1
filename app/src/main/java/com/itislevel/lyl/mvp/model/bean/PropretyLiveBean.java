package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\23 0023.
 */

public class PropretyLiveBean {
    private List<AdvertlistBean> advertlist;
    public List<AdvertlistBean> getAdvertlist() {
        return advertlist;
    }
    public void setAdvertlist(List<AdvertlistBean> advertlist) {
        this.advertlist = advertlist;
    }

    public static class AdvertlistBean {
        /**
         * content : null
         * id : 1
         * logo : 89C9DDBA-0FE7-4FEF-94E1-1BD42767FC9B.jpg
         * createdtime : null
         * name : 广告测试
         * looknum : 0
         * isstart : 1
         */

        private Object content;
        private int id;
        private String logo;
        private Object createdtime;
        private String name;
        private int looknum;
        private String isstart;

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Object getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(Object createdtime) {
            this.createdtime = createdtime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLooknum() {
            return looknum;
        }

        public void setLooknum(int looknum) {
            this.looknum = looknum;
        }

        public String getIsstart() {
            return isstart;
        }

        public void setIsstart(String isstart) {
            this.isstart = isstart;
        }
    }
    /**
     * id : 1
     * name : 广告测试
     * logo : BEEA30C6-19FB-4B41-8F1D-3AB29DE38EC8.jpg
     * isstart : 1
     * looknum : 0
     * content : null
     * createdtime : null
     */

}

