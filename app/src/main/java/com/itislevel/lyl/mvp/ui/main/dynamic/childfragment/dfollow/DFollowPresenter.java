package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.DynamicCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.DynimacLinkBean;
import com.itislevel.lyl.mvp.model.bean.FindistBean;
import com.itislevel.lyl.mvp.model.bean.FollowListBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class DFollowPresenter extends RxPresenter<DFollowContract.View> implements DFollowContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public  DFollowPresenter(DataManager dataManager)
    {
        mDataManager = dataManager;
    }
    @Override
    public void firstPage(String msg) {
        mDataManager.dynamic_find(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.<FindistBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FindistBean>() {
                    @Override
                    public void onNext(FindistBean r) {
                        mView.firstPage(r);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void dynamicdianzan(String msg) {
        mDataManager.dynamicdianzan(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.<DynimacLinkBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<DynimacLinkBean>() {
                    @Override
                    public void onNext(DynimacLinkBean r) {
                        mView.dynamicdianzan(r);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addDynamicComment(String action) {
        mDataManager.addDynamicComment(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.<DynamicCommnetAddBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<DynamicCommnetAddBean>() {
                    @Override
                    public void onNext(DynamicCommnetAddBean r) {
                        mView.addDynamicComment(r);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void delDynamicComment(String msg) {
        mDataManager.delDynamicComment(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String r) {
                        mView.delDynamicComment(r);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void happyGiftList(String action) {
        mDataManager.happyGiftList1(action)
                .compose(RxUtil.<LYLResponse<List<GiftBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<GiftBean>>handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<GiftBean>>() {
                    @Override
                    public void onNext(@NonNull List<GiftBean> blessGiftBean) {
                        mView.happyGiftList(blessGiftBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.w("happyList:" + e.getMessage());
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

    @Override
    public void immediateOrder(String action) {
        mDataManager.immediateOrder(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessOrderBean) {
                        mView.immediateOrder(blessOrderBean);
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
