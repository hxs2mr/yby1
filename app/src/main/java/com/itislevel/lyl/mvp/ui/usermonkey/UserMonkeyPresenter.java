package com.itislevel.lyl.mvp.ui.usermonkey;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\7 0007 10:47
 */
public class UserMonkeyPresenter extends RxPresenter<UserMonkeyContract.View> implements UserMonkeyContract.Presenter {
    private DataManager mDataManager;
    @Inject
    public  UserMonkeyPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

}
