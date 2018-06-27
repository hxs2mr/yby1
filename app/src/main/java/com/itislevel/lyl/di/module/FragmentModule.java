package com.itislevel.lyl.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.itislevel.lyl.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * **********************
 * 功 能:为Fragment提供Module
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:19
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:19
 * 修改内容:itisi
 * *********************
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }
    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return mFragment.getActivity();
    }
}
