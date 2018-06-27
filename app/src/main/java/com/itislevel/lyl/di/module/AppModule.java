package com.itislevel.lyl.di.module;



import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.db.DBHelper;
import com.itislevel.lyl.mvp.model.db.RealmHelper;
import com.itislevel.lyl.mvp.model.http.HttpHelper;
import com.itislevel.lyl.mvp.model.http.RetrofitHelper;
import com.itislevel.lyl.mvp.model.prefs.PreferencesHelper;
import com.itislevel.lyl.mvp.model.prefs.PreferencesImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * **********************
 * 功 能:全局Module 单列 与app生命周期共存的Module
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:19
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:19
 * 修改内容:itisi
 * *********************
 */
@Module
public class AppModule {
    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

   @Provides
   @Singleton
    App provideApp(){
        return mApp;
    }
    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper){
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesImpl preferencesHelper){
        return preferencesHelper;
    }
    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper){
        return new DataManager(httpHelper,dbHelper,preferencesHelper);
    }
}
