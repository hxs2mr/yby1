package com.itislevel.lyl.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * **********************
 * 功 能:与Fragment同生命周期的
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:16
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:16
 * 修改内容:itisi
 * *********************
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
