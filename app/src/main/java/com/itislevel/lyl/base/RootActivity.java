package com.itislevel.lyl.base;

import android.content.Context;

import qiu.niorgai.StatusBarCompat;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * **********************
 * 功 能: 统一处理等待对话框 加载错误 数据加载完成 无更多数据 等处理
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 15:55
 * 修改人:itisi
 * 修改时间: 2017/7/6 15:55
 * 修改内容:itisi
 * *********************
 */

public abstract class RootActivity<T extends BasePresenter> extends BaseActivity<T> {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
