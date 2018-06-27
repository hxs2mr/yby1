package com.itislevel.lyl.utils.rxbus.annotation;

import android.support.annotation.NonNull;


import com.itislevel.lyl.utils.rxbus.event.EventThread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * **********************
 * 功 能: 注解在需要被事件监听的方法上
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 14:56
 * 修改人:itisi
 * 修改时间: 2017/7/6 14:56
 * 修改内容:itisi
 * *********************
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Subscribe {
    @NonNull int tag();
    EventThread thread() default EventThread.MAIN_THREAD;
}
