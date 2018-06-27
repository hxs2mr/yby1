package com.itislevel.lyl.base;

/**
 * **********************
 * 功 能:Presenter基本接口
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:38
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:38
 * 修改内容:itisi
 * *********************
 */

public interface BasePresenter<T extends BaseView> {
    /**
     * 为presenter设置view
     * @param view
     */
    void attachView(T view);

    /**
     * 移除view
     */
    void detachView();

}
