package com.itislevel.lyl.mvp.model.bean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/28.14:06
 * path:com.itislevel.lyl.mvp.model.bean.FamilyUsualLanguageBean
 **/
public class FamilyUsualLanguageBean {


    private List<ModelvolistBean> modelvolist;

    public List<ModelvolistBean> getModelvolist() {
        return modelvolist;
    }

    public void setModelvolist(List<ModelvolistBean> modelvolist) {
        this.modelvolist = modelvolist;
    }

    public static class ModelvolistBean {
        /**
         * id : 4A7FC061-D3B5-4F09-A906-D12DAD427E89
         * value : 逝者已矣，节哀顺变
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
