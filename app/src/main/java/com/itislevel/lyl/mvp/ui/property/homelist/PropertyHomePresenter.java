package com.itislevel.lyl.mvp.ui.property.homelist;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.HomeDetailBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\5\25 0025.
 */

public class PropertyHomePresenter extends RxPresenter<PropertyHomeContract.View>implements PropertyHomeContract.Presenter {
    private DataManager mDataManager;
    @Inject
    public PropertyHomePresenter(DataManager dataManager)
    {
        this.mDataManager =dataManager;
    }

    @Override
    public void findVillagename(String action) {
        mDataManager.findVillagename(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<VillageNameBean>>() {
                    @Override
                    public void onNext(List<VillageNameBean> villageNameBeans) {
                            mView.findVillagename(villageNameBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void findLiveaddress(String action) {
            mDataManager.findLiveaddress(action)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<List<LiveAddressBean>>() {
                        @Override
                        public void onNext(List<LiveAddressBean> liveAddressBeans) {
                                mView.findLiveaddress(liveAddressBeans);
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
    public void personalNews(String action) {
        mDataManager.personalNews(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<HomeDetailBean>>() {
                    @Override
                    public void onNext(List<HomeDetailBean> bean) {
                        mView.personalNews(bean);
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
