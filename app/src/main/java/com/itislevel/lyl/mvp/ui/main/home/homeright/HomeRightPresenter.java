package com.itislevel.lyl.mvp.ui.main.home.homeright;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class HomeRightPresenter extends RxPresenter<HomeRightContract.View>implements HomeRightContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public  HomeRightPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }
}
