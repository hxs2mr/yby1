package com.itislevel.lyl.base;

/**
 * **********************
 * 功 能:View基本接口
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:38
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:38
 * 修改内容:itisi
 * *********************
 */

public interface BaseView {
    /**
     * 显示错误信息
     * @param msg
     */
    void showErrorMsg(String msg);

    /**
     * 是否显示亮色主题
     * @param isNight
     */
    void useNightMode(boolean isNight);
//----------------加载数据时用到的状态-------------------------------
    /**
     * 状态----空数据
     */
    void stateEmpty();

    /**
     * 状态----加载中
     */
    void stateLoading();

    /**
     * 状态----错误
     */
    void stateError();

    void stateError(Throwable e);
    void stateError(Exception e);
    /**
     * 状态----成功
     */
    void stateSuccess();

}
