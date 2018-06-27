package com.itislevel.lyl.mvp.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;


import com.itislevel.lyl.app.App;

import javax.inject.Inject;

/**
 * **********************
 * 功 能:主题帮助类实现类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:33
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:33
 * 修改内容:itisi
 * *********************
 */

public class PreferencesImpl implements PreferencesHelper {

    private static final String SHAREDPREFERENCES_NAME = "guizhou";
    private final SharedPreferences mSPrefs;

    @Inject
    public PreferencesImpl() {
        mSPrefs =  App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);;
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean state) {

    }
}
