package com.itislevel.lyl.mvp.ui.property.childfragment.historicalbill;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.HistoricalBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\6\20 0020.
 */

public class HistoricalPresenter extends RxPresenter<HistoricalbillContract.View>implements HistoricalbillContract.Presenter{
    private DataManager mDataManager;

    @Inject
    public  HistoricalPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }


    @Override
    public void querybillrecord(String bean) {
        mDataManager.querybillrecord(bean)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<HistoricalBean>() {
                    @Override
                    public void onNext(HistoricalBean bean) {
                            mView.querybillrecord(bean);
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
