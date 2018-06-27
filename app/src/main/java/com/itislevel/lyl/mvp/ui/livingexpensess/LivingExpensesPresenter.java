package com.itislevel.lyl.mvp.ui.livingexpensess;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * **********************
 * 功 能:主页-主页
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 13:58
 * 修改人:itisi
 * 修改时间: 2017/7/7 13:58
 * 修改内容:itisi
 * *********************
 */
public class LivingExpensesPresenter extends RxPresenter<LivingExpensesContract.View> implements LivingExpensesContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public LivingExpensesPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void loadData(int num, int page) {
        mDataManager.getMeiZiList(num, page)
                .compose(RxUtil.<GankResponse<List<MeiZiBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<MeiZiBean>>handlerGankResult())
                .subscribeWith(new ResourceObserver<List<MeiZiBean>>() {
                    @Override
                    public void onNext(@NonNull List<MeiZiBean> meiZiBeen) {
                        mView.showContent("itisi:" + meiZiBeen.get(0).getCreatedAt());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:"+e.getMessage());
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
    public void propertyQuery(String action) {
        mDataManager.propertyQuery(action)
                .compose(RxUtil.<LYLResponse<PropertyQueryInfoBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<PropertyQueryInfoBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertyQueryInfoBean>() {
                    @Override
                    public void onNext(@NonNull PropertyQueryInfoBean propertyQueryInfoBean) {
                        mView.propertyQuery(propertyQueryInfoBean);
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

    @Override
    public void propertyQueryOrder(String action) {
        mDataManager.propertyQueryOrder(action)
                .compose(RxUtil.<LYLResponse<PropertyRecordBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<PropertyRecordBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertyRecordBean>() {
                    @Override
                    public void onNext(@NonNull PropertyRecordBean propertyRecordBean) {
                        mView.propertyQueryOrder(propertyRecordBean);
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

    @Override
    public void propertyUpdatePayType(String action) {
        mDataManager.propertyUpdatePayType(action)
                .compose(RxUtil.<LYLResponse<PropertyUpdateStatusBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<PropertyUpdateStatusBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertyUpdateStatusBean>() {
                    @Override
                    public void onNext(@NonNull PropertyUpdateStatusBean statusBean) {
                        mView.propertyUpdatePayType(statusBean);
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

    @Override
    public void propertyUpdateOrderState(String action) {
        mDataManager.propertyUpdateOrderState(action)
                .compose(RxUtil.<LYLResponse<PropertyUpdateStatusBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<PropertyUpdateStatusBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertyUpdateStatusBean>() {
                    @Override
                    public void onNext(@NonNull PropertyUpdateStatusBean statusBean) {
                        mView.propertyUpdateOrderState(statusBean);
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

    @Override
    public void propertyGenerateOrder(String action) {
//        mDataManager.propertyGenerateOrder(action)
//                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<String>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<String>() {
//                    @Override
//                    public void onNext(@NonNull String orderBean) {
//                        mView.propertyGenerateOrder(orderBean);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Logger.w("happyList:"+e.getMessage());
//                        mView.stateError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });

        mDataManager.happyOrderAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessOrderBean) {
                        mView.propertyGenerateOrder(blessOrderBean);
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

    @Override
    public void getSSMCode(String action) {
        mDataManager.getSSMCode(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.getSSMCode(msg);
                    }

                    @Override
                    public void onError(Throwable e) {
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
    public void propertyQueryByUserid(String action) {
        mDataManager.propertyQueryByUserid(action)
                .compose(RxUtil.<LYLResponse<PropertyQueryInfo>>rxSchedulerObservableHelper())
                .compose(RxUtil.<PropertyQueryInfo>handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertyQueryInfo>() {
                    @Override
                    public void onNext(PropertyQueryInfo queryInfo) {
                        mView.propertyQueryByUserid(queryInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
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
    public void propertyQueryList(String action) {
        mDataManager.propertyQueryList(action)
                .compose(RxUtil.<LYLResponse<PropertyQueryInfo>>rxSchedulerObservableHelper())
                .compose(RxUtil.<PropertyQueryInfo>handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertyQueryInfo>() {
                    @Override
                    public void onNext(@NonNull PropertyQueryInfo queryInfo) {
                        mView.propertyQueryList(queryInfo);
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

    @Override
    public void propertyQueryByUserid1(String action) {
        mDataManager.propertyQueryByUserid1(action)
                .compose(RxUtil.<LYLResponse<PropertyQueryInfoBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<PropertyQueryInfoBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertyQueryInfoBean>() {
                    @Override
                    public void onNext(@NonNull PropertyQueryInfoBean propertyQueryInfoBean) {
                        mView.propertyQueryByUserid1(propertyQueryInfoBean);
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

    @Override
    public void propertySetOwnerPayMonth(String action) {
        mDataManager.propertySetOwnerPayMonth(action)
                .compose(RxUtil.<LYLResponse<SetOwnerPayMonth>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SetOwnerPayMonth>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SetOwnerPayMonth>() {
                    @Override
                    public void onNext(@NonNull SetOwnerPayMonth propertyQueryInfoBean) {
                        mView.propertySetOwnerPayMonth(propertyQueryInfoBean);
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
