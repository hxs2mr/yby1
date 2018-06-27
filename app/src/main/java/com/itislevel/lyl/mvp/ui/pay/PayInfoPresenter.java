package com.itislevel.lyl.mvp.ui.pay;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.WeiXinPayTestBean;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.itislevel.lyl.utils.ToastUtil;
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
public class PayInfoPresenter extends RxPresenter<PayInfoContract.View> implements PayInfoContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public PayInfoPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }


//    @Override
//    public void alipayTest(String action) {
//        mDataManager.alipayTest(action)
//                .compose(RxUtil.<LYLResponse<AliPayBean>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<AliPayBean>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<AliPayBean>() {
//                    @Override
//                    public void onNext(@NonNull AliPayBean fileUploadBean) {
//                        mView.alipayTest(fileUploadBean);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Logger.e("错误信息:" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });
//    }

//    @Override
//    public void happyOrderAdd(String action) {
//        mDataManager.happyOrderAdd(action)
//                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<String>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<String>() {
//                    @Override
//                    public void onNext(@NonNull String blessOrderBean) {
//                        mView.happyOrderAdd(blessOrderBean);
//                    }
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Logger.w("payinfo:"+e.getMessage());
//                        mView.stateError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });
//    }

//    @Override
//    public void immediateOrder(String action) {
//        mDataManager.immediateOrder(action)
//                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<String>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<String>() {
//                    @Override
//                    public void onNext(@NonNull String blessOrderBean) {
//                        mView.immediateOrder(blessOrderBean);
//                    }
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Logger.w("pauinfo:"+e.getMessage());
//                        mView.stateError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });
//    }

//    @Override
//    public void propertyOrder(String action) {
//        mDataManager.propertyGenerateOrder(action)
//                .compose(RxUtil.<LYLResponse<BlessOrderBean>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<BlessOrderBean>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<BlessOrderBean>() {
//                    @Override
//                    public void onNext(@NonNull BlessOrderBean blessOrderBean) {
//                        mView.propertyOrder(blessOrderBean);
//                    }
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                        mView.stateError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });
//    }
//
//    @Override
//    public void propertyOrderStatus(String action) {
//        mDataManager.propertyUpdateOrderState(action)
//                .compose(RxUtil.<LYLResponse<PropertyUpdateStatusBean>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<PropertyUpdateStatusBean>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<PropertyUpdateStatusBean>() {
//                    @Override
//                    public void onNext(@NonNull PropertyUpdateStatusBean statusBean) {
//                        mView.propertyOrderStatus(statusBean);
//                    }
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                        mView.stateError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });
//    }

    @Override
    public void weixinPayTest(String action) {
        mDataManager.weixinPayTest(action)
                .compose(RxUtil.<LYLResponse<WeiXinPayTestBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<WeiXinPayTestBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<WeiXinPayTestBean>() {
                    @Override
                    public void onNext(@NonNull WeiXinPayTestBean blessOrderBean) {
                        mView.weixinPayTest(blessOrderBean);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

    @Override
    public void alipayList(String action) {
        mDataManager.alipayList(action)
                .compose(RxUtil.<LYLResponse<BlessOrderBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessOrderBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessOrderBean>() {
                    @Override
                    public void onNext(@NonNull BlessOrderBean blessOrderBean) {
                        mView.alipayList(blessOrderBean);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
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

//    @Override
//    public void teamProblemAdd(String action) {
//        mDataManager.teamProblemAdd(action)
//                .compose(RxUtil.<LYLResponse<BlessOrderBean>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<BlessOrderBean>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<BlessOrderBean>() {
//                    @Override
//                    public void onNext(@NonNull BlessOrderBean blessOrderBean ) {
//                        mView.teamProblemAdd(blessOrderBean);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                        mView.stateError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });
//    }
}
