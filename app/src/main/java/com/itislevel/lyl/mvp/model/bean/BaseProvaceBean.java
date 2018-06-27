package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class BaseProvaceBean {

    /**
     * citylist : [{"id":110100,"s_name":"北京","arealist":[{"id":110101,"s_name":"东城"},{"id":110102,"s_name":"西城"},{"id":110105,"s_name":"朝阳"},{"id":110106,"s_name":"丰台"},{"id":110107,"s_name":"石景山"},{"id":110108,"s_name":"海淀"},{"id":110109,"s_name":"门头沟"},{"id":110111,"s_name":"房山"},{"id":110112,"s_name":"通州"},{"id":110113,"s_name":"顺义"},{"id":110114,"s_name":"昌平"},{"id":110115,"s_name":"大兴"},{"id":110116,"s_name":"怀柔"},{"id":110117,"s_name":"平谷"},{"id":110228,"s_name":"密云"},{"id":110229,"s_name":"延庆"}]}]
     * id : 110000
     * s_name : 北京
     * e_name : B
     */

    private int id;
    private String s_name;
    private String e_name;
    private List<CitylistBean> citylist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public List<CitylistBean> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CitylistBean> citylist) {
        this.citylist = citylist;
    }

    public static class CitylistBean {
        /**
         * id : 110100
         * s_name : 北京
         * arealist : [{"id":110101,"s_name":"东城"},{"id":110102,"s_name":"西城"},{"id":110105,"s_name":"朝阳"},{"id":110106,"s_name":"丰台"},{"id":110107,"s_name":"石景山"},{"id":110108,"s_name":"海淀"},{"id":110109,"s_name":"门头沟"},{"id":110111,"s_name":"房山"},{"id":110112,"s_name":"通州"},{"id":110113,"s_name":"顺义"},{"id":110114,"s_name":"昌平"},{"id":110115,"s_name":"大兴"},{"id":110116,"s_name":"怀柔"},{"id":110117,"s_name":"平谷"},{"id":110228,"s_name":"密云"},{"id":110229,"s_name":"延庆"}]
         */

        private int id;
        private String s_name;
        private List<ArealistBean> arealist;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public List<ArealistBean> getArealist() {
            return arealist;
        }

        public void setArealist(List<ArealistBean> arealist) {
            this.arealist = arealist;
        }

        public static class ArealistBean {
            /**
             * id : 110101
             * s_name : 东城
             */

            private int id;
            private String s_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getS_name() {
                return s_name;
            }

            public void setS_name(String s_name) {
                this.s_name = s_name;
            }

            public ArealistBean(int id, String s_name) {
                this.id = id;
                this.s_name = s_name;
            }
        }
    }
}
