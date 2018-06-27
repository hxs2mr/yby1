package com.itislevel.lyl.di.component;



import com.itislevel.lyl.app.App;
import com.itislevel.lyl.di.module.AppModule;
import com.itislevel.lyl.di.module.HttpModule;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.db.RealmHelper;
import com.itislevel.lyl.mvp.model.http.RetrofitHelper;
import com.itislevel.lyl.mvp.model.prefs.PreferencesImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * **********************
 * 功 能:为App 注入对象
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:43
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:43
 * 修改内容:itisi
 * *********************
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    /**
     * 返回context
     * @return
     */
    App getContext();

    /**
     * 数据库操作
     * @return
     */
    DataManager getDataManager();

    /**
     * http 操作
     * @return
     */
    RetrofitHelper getRetrofitHelper();

    /**
     * 数据库操作
     * @return
     */
    RealmHelper getRealmHelper();

    /**
     * sp 操作
     * @return
     */
    PreferencesImpl getPreferenceHelper();

}
