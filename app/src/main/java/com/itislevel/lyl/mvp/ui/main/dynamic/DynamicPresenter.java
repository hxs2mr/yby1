package com.itislevel.lyl.mvp.ui.main.dynamic;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class DynamicPresenter extends RxPresenter<DynamicContract.View> implements DynamicContract.Presenter  {
    private DataManager  mDataManager;
    @Inject
    public DynamicPresenter(DataManager dataManager)
    {
        mDataManager = dataManager;
    }
    @Override
    public void firstPage(String msg) {

    }
}
