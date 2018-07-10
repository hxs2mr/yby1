package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\10 0010 11:08
 */
public class MonkeySettingPresenter extends RxPresenter<MonkeySettingContract.View>implements MonkeySettingContract.Presenter {
    private DataManager mDataManager;
    @Inject
    public  MonkeySettingPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

}
