package com.itislevel.lyl.utils.rxbus.pojo;

/**
 * **********************
 * 功 能:Rx消息对象
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 14:39
 * 修改人:itisi
 * 修改时间: 2017/7/6 14:39
 * 修改内容:itisi
 * *********************
 */
public class RxMessage {
    /**
     * 事件标记
     */
    public int tag;
    /**
     * 事件携带的消息内容
     */
    public Object object;

    public RxMessage(int tag, Object object) {
        this.tag = tag;
        this.object = object;
    }
}
