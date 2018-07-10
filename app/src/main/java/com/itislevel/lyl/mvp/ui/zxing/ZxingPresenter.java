package com.itislevel.lyl.mvp.ui.zxing;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\4 0004 10:48
 */
public class ZxingPresenter extends RxPresenter<ZxingContract.View>implements ZxingContract.Presenter{
    private DataManager mDataManagaer;
    @Inject
    public  ZxingPresenter(DataManager dataManager)
    {
        this.mDataManagaer = dataManager;
    }
}
