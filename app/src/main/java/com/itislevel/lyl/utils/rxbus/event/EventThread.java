package com.itislevel.lyl.utils.rxbus.event;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * **********************
 * 功 能:事件线程类型枚举
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 14:41
 * 修改人:itisi
 * 修改时间: 2017/7/6 14:41
 * 修改内容:itisi
 * *********************
 */

public enum  EventThread {
    /**
     * 主线程
     */
    MAIN_THREAD,
    /**
     * 开启新线程
     */
    NEW_THREAD,
    /**
     * io线程
     */
    IO,
    /**
     * 计算线程
     */
    COMPUTATION,
    /**
     * TRAMPOLINE
     */
    TRAMPOLINE;

    public static Scheduler getScheuler(EventThread eventThread){
        Scheduler scheduler;
        switch (eventThread){
            case MAIN_THREAD:
                scheduler= AndroidSchedulers.mainThread();
                break;
            case NEW_THREAD:
                scheduler= Schedulers.newThread();
                break;
            case IO:
                scheduler=Schedulers.io();
                break;
            case COMPUTATION:
                scheduler= Schedulers.io();
                break;
            case TRAMPOLINE:
                scheduler=Schedulers.trampoline();
                break;
            default:
                scheduler= AndroidSchedulers.mainThread();
        }
        return scheduler;
    }


}
