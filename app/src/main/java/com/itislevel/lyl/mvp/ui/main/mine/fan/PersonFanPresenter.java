package com.itislevel.lyl.mvp.ui.main.mine.fan;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;
import retrofit2.http.PUT;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\2 0002 09:03
 */
public class PersonFanPresenter extends RxPresenter<PersonFanContract.View>implements PersonFanContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public  PersonFanPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void merchantmainpage(String msg) {
        mDataManager.merchantmainpage(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<ShanHomeBean>() {
                    @Override
                    public void onNext(ShanHomeBean bean) {
                            mView.merchantmainpage(bean);
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
    public void rechargeRecord(String msg) {
            mDataManager.rechargeRecord(msg)
                        .compose(RxUtil.rxSchedulerObservableHelper())
                        .compose(RxUtil.handlerLYLResult())
                        .subscribeWith(new ResourceObserver<FanRecodeBean>() {
                            @Override
                            public void onNext(FanRecodeBean bean) {
                                    mView.rechargeRecord(bean);
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
    public void cashbackist(String msg) {
            mDataManager.cashbackist(msg)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<FanXianBean>() {
                        @Override
                        public void onNext(FanXianBean bean) {
                                mView.cashbackist(bean);
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
    public void onlinerecharge(String msg) {
        mDataManager.onlinerecharge(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                            mView.onlinerecharge(s);
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
