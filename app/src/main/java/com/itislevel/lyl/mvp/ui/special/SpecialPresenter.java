package com.itislevel.lyl.mvp.ui.special;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftByIdBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderCompleteBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReceiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanUpdateBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;
import okhttp3.MultipartBody;

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
public class SpecialPresenter extends RxPresenter<SpecialContract.View> implements
        SpecialContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SpecialPresenter(DataManager dataManager) {
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
                        Logger.i("错误信息:" + e.getMessage());
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
    public void specialType(String action) {
        mDataManager.specialType(action)
                .compose(RxUtil.<LYLResponse<SpecialTypeBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialTypeBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialTypeBean>() {
                    @Override
                    public void onNext(SpecialTypeBean msg) {
                        mView.specialType(msg);
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
    public void specialList(String action) {
        mDataManager.specialList(action)
                .compose(RxUtil.<LYLResponse<SpecialGiftListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialGiftListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialGiftListBean>() {
                    @Override
                    public void onNext(SpecialGiftListBean msg) {
                        mView.specialList(msg);
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
    public void specialById(String action) {
        mDataManager.specialById(action)
                .compose(RxUtil.<LYLResponse<SpecialGiftByIdBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialGiftByIdBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialGiftByIdBean>() {
                    @Override
                    public void onNext(SpecialGiftByIdBean msg) {
                        mView.specialById(msg);
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
    public void specialImmediatelyOrder(String action) {
        mDataManager.specialImmediatelyOrder(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialImmediatelyOrder(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialShopOrder(String action) {
        mDataManager.specialShopOrder(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialShopOrder(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialReceiveAddress(String action) {
        mDataManager.specialReceiveAddress(action)
                .compose(RxUtil
                        .<LYLResponse<SpecialReceiveAddressBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialReceiveAddressBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialReceiveAddressBean>() {
                    @Override
                    public void onNext(SpecialReceiveAddressBean msg) {
                        mView.specialReceiveAddress(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialOrderDetail(String action) {
        mDataManager.specialOrderDetail(action)
                .compose(RxUtil.<LYLResponse<SpecialOrderDetailBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialOrderDetailBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialOrderDetailBean>() {
                    @Override
                    public void onNext(SpecialOrderDetailBean msg) {
                        mView.specialOrderDetail(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialTuiKuanDetail(String action) {
        mDataManager.specialTuiKuanDetail(action)
                .compose(RxUtil
                        .<LYLResponse<SpecialTuiKuanDetailBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialTuiKuanDetailBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialTuiKuanDetailBean>() {
                    @Override
                    public void onNext(SpecialTuiKuanDetailBean msg) {
                        mView.specialTuiKuanDetail(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialTuiKuan(String action) {
        mDataManager.specialTuiKuan(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialTuiKuan(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialTuiKuanUpdate(String action) {
        mDataManager.specialTuiKuanUpdate(action)
                .compose(RxUtil
                        .<LYLResponse<SpecialTuiKuanUpdateBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialTuiKuanUpdateBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialTuiKuanUpdateBean>() {
                    @Override
                    public void onNext(SpecialTuiKuanUpdateBean msg) {
                        mView.specialTuiKuanUpdate(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialTuiKuanUpdate2(String action) {
        mDataManager.specialTuiKuanUpdate2(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialTuiKuanUpdate2(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialOrderComplete(String action) {
        mDataManager.specialOrderComplete(action)
                .compose(RxUtil
                        .<LYLResponse<SpecialOrderCompleteBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialOrderCompleteBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialOrderCompleteBean>() {
                    @Override
                    public void onNext(SpecialOrderCompleteBean msg) {
                        mView.specialOrderComplete(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialOrderGoPay(String action) {
        mDataManager.specialOrderGoPay(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialOrderGoPay(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialOrderCancel(String action) {
        mDataManager.specialOrderCancel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialOrderCancel(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialShenQingTuiKuan(String action) {
        mDataManager.specialShenQingTuiKuan(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialShenQingTuiKuan(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialOrderConfirm(String action) {
        mDataManager.specialOrderConfirm(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.specialOrderConfirm(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void specialReturnList(String action) {
        mDataManager.specialReturnList(action)
                .compose(RxUtil.<LYLResponse<SpecialReturnBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<SpecialReturnBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<SpecialReturnBean>() {
                    @Override
                    public void onNext(SpecialReturnBean msg) {
                        mView.specialReturnList(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void uploadHeader(MultipartBody.Part file) {
        mDataManager.uploadHeader(file)
                .compose(RxUtil.<LYLResponse<FileUploadBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FileUploadBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FileUploadBean>() {
                    @Override
                    public void onNext(@NonNull FileUploadBean fileUploadBean) {
                        mView.uploadHeader(fileUploadBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }


    @Override
    public void happyCartAdd(String action) {
        mDataManager.happyCartAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessCartAddBean) {
                        mView.happyCartAdd(blessCartAddBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.w("happyList:" + e.getMessage());
                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

    @Override
    public void happyCartList(String action) {
        mDataManager.happyCartList(action)
                .compose(RxUtil.<LYLResponse<BlessCartListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessCartListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessCartListBean>() {
                    @Override
                    public void onNext(@NonNull BlessCartListBean blessCartListBean) {
                        mView.happyCartList(blessCartListBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.w("happyList:" + e.getMessage());
                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

    @Override
    public void happyCartUpdate(String action) {
        mDataManager.happyCartUpdate(action)
                .compose(RxUtil.<LYLResponse<CartUpdateBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<CartUpdateBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<CartUpdateBean>() {
                    @Override
                    public void onNext(@NonNull CartUpdateBean msg) {
                        mView.happyCartUpdate(msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.w("happyList:" + e.getMessage());
                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

    @Override
    public void happyCartDel(String action) {
        mDataManager.happyCartDel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String msg) {
                        mView.happyCartDel(msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.w("happyList:" + e.getMessage());
                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }
}
