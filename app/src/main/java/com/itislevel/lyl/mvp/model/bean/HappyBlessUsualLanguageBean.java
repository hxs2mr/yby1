package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017-12-28.16:57
 * path:com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean
 **/
public class HappyBlessUsualLanguageBean {

    private List<ModelVoBean> modelVo;

    public List<ModelVoBean> getModelVo() {
        return modelVo;
    }

    public void setModelVo(List<ModelVoBean> modelVo) {
        this.modelVo = modelVo;
    }

    public static class ModelVoBean {
        /**
         * id : 06DD1F9A-117D-4346-A7BE-D09F4F8820F9
         * value : 天道酬勤，宁静致远
         */

        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
