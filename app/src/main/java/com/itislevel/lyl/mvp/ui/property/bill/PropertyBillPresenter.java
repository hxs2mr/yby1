package com.itislevel.lyl.mvp.ui.property.bill;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BillsBean;
import com.itislevel.lyl.mvp.model.bean.PropertyBillBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\5\28 0028.
 */

public class PropertyBillPresenter extends RxPresenter<PropertyBillContract.View>implements PropertyBillContract.Presenter{
    private DataManager mDataManager;

    @Inject
    public  PropertyBillPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void propertyBill(String action) {
        mDataManager.propertyBill(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<PropertyBillBean>>() {
                    @Override
                    public void onNext(List<PropertyBillBean> billBeans) {
                        mView.propertyBill(billBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView!=null)
                        {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void propertyadd(String action) {
        mDataManager.propertyaddOwn(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        mView.propertyadd(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView!=null)
                        {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void findBillsMonth(String action) {
        mDataManager.findBillsMonth(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult_PIN())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                              mView.findBillsMonth(s);
                    }
                    @Override
                    public void onError(Throwable e) {
                        if(mView!=null)
                        {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
