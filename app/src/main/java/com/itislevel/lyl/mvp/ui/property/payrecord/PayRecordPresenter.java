package com.itislevel.lyl.mvp.ui.property.payrecord;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.mvp.ui.pay.PayInfoContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\5\31 0031.
 */

public class PayRecordPresenter extends RxPresenter<PayRecordContract.View>implements PayRecordContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public PayRecordPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void estatesPayList(String action) {
        mDataManager.estatesPayList(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<PayLuBean>>() {
                    @Override
                    public void onNext(List<PayLuBean> payLuBeans) {
                        mView.estatesPayList(payLuBeans);
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
    public void findLiveaddress(String list) {
        mDataManager.findLiveaddress(list)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<LiveAddressBean>>() {
                    @Override
                    public void onNext(List<LiveAddressBean> liveAddressBeans) {
                        mView.findLiveaddress(liveAddressBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
