package com.itislevel.lyl.di.module;

import android.app.Activity;


import com.itislevel.lyl.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * **********************
 * 功 能:为Activity提供Module
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:19
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:19
 * 修改内容:itisi
 * *********************
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return mActivity;
    }
}
