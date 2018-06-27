package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/25.11:09
 * path:com.itislevel.lyl.mvp.model.bean.SelectImgBean
 **/
public class SelectImgBean {
    private Object imgUrl;

    private boolean isSelectItem;



    public boolean isSelectItem() {
        return isSelectItem;
    }

    public void setSelectItem(boolean selectItem) {
        isSelectItem = selectItem;
    }


    public Object getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Object imgUrl) {
        this.imgUrl = imgUrl;
    }
}
