package com.itislevel.lyl.mvp.ui.usermonkey.putrecord;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\9 0009 14:59
 */
public class PutRecordPresenter extends RxPresenter<PutRecordContract.View>implements PutRecordContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public  PutRecordPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }
}
