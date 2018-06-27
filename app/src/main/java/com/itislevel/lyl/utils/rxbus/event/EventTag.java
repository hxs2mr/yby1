package com.itislevel.lyl.utils.rxbus.event;

/**
 * **********************
 * 功 能:事件-标记
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 14:41
 * 修改人:itisi
 * 修改时间: 2017/7/6 14:41
 * 修改内容:itisi
 * *********************
 */

public enum  EventTag {
    /**
     * 默认
     */
    DEFAULT(0),
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAILED(-300),
    /**
     * 更新
     */
    UPDATE(301),
    /**
     * 失败
     */
    ERROR(-404)
    ;

    private int value;
    EventTag(int value) {
        this.value=value;
    }

    /**
     * 获取事件标记
     * @param tag
     * @return
     */
    public static int getTag(EventTag tag){
        int i=0;
        switch (tag){
            case DEFAULT:
                i=0;
                break;
            case SUCCESS:
                i=200;
                break;
            case FAILED:
                i=-300;
                break;
            case UPDATE:
                i=301;
                break;
            case ERROR:
                i=404;
                break;
        }

        return i;
    }

}
