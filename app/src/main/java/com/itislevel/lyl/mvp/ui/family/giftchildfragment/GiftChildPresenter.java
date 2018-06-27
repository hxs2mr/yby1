package com.itislevel.lyl.mvp.ui.family.giftchildfragment;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\5\21 0021.
 */

public class GiftChildPresenter extends RxPresenter<GiftChildContract.View>implements GiftChildContract.Presenter{
    private DataManager mDataManager;

    @Inject
    public GiftChildPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;

    }
    @Override
    public void familyListGift(String action) {
        mDataManager.familyListGift(action)
                .compose(RxUtil.<LYLResponse<FamilyGiftListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyGiftListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyGiftListBean>() {
                    @Override
                    public void onNext(@NonNull FamilyGiftListBean familyGiftListBean) {
                        mView.familyListGift(familyGiftListBean);
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
    public void familyCartList(String action) {
        mDataManager.happyCartList(action)
                .compose(RxUtil.<LYLResponse<BlessCartListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessCartListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessCartListBean>() {
                    @Override
                    public void onNext(@NonNull BlessCartListBean blessCartListBean) {
                        mView.familyCartList(blessCartListBean);
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
    public void immediateOrder(String blessOrderBean) {
        mDataManager.immediateOrder(blessOrderBean)
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
