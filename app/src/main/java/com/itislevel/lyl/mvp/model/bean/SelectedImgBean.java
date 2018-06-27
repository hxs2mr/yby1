package com.itislevel.lyl.mvp.model.bean;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/8/1 16:11
 * 修改人:itisi
 * 修改时间: 2017/8/1 16:11
 * 修改内容:itisi
 * *********************
 */

public class SelectedImgBean {
    private String originalPath;
    private String thumbPath;
    private int height; //自定义的属性 图片的高度

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
