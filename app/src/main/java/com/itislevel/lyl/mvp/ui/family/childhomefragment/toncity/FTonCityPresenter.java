package com.itislevel.lyl.mvp.ui.family.childhomefragment.toncity;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FamilyTonListBean;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class FTonCityPresenter extends RxPresenter<FTonCityContract.View> implements FTonCityContract.Presenter{
     private DataManager mDataManager;
     @Inject
     public FTonCityPresenter(DataManager dataManager)
     {
         mDataManager = dataManager;
     }
    @Override
     public void firstPage(String msg) {
        mDataManager.familyList_ton(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyTonListBean>() {
                    @Override
                    public void onNext(@NonNull FamilyTonListBean familyListBean) {
                        mView.firstPage(familyListBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.w("happyList:"+e.getMessage());
                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

}
