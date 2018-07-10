package com.itislevel.lyl.mvp.ui.userfan;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.UserFanBean;
import com.itislevel.lyl.mvp.model.bean.UserHistoryBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanDetailBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\5 0005 14:20
 */
public class UserFanPresenter extends RxPresenter<UserFanContract.View>implements UserFanContract.Presenter{
    private DataManager mDataManager;

    @Inject
    public   UserFanPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void cashbackPage(String msg) {
        mDataManager.cashbackPage(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<UserFanBean>() {
                    @Override
                    public void onNext(UserFanBean beans) {
                        mView.cashbackPage(beans);
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
    public void cashbackstages(String msg) {
        mDataManager.cashbackstages(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<UserPlanBean>() {
                    @Override
                    public void onNext(UserPlanBean bean) {
                            mView.cashbackstages(bean);
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
    public void cashbackstagesDetails(String msg) {
        mDataManager.cashbackstagesDetails(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<UserPlanDetailBean>() {
                    @Override
                    public void onNext(UserPlanDetailBean userPlanDetailBeans) {
                            mView.cashbackstagesDetails(userPlanDetailBeans);
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
    public void cashbackRecord(String msg) {
        mDataManager.cashbackRecord(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<UserHistoryBean>() {
                    @Override
                    public void onNext(UserHistoryBean bean) {
                        mView.cashbackRecord(bean);
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
    public void clickreceive(String msg) {
        mDataManager.clickreceive(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                            mView.clickreceive(s);
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
