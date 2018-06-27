package com.itislevel.lyl.mvp.ui.family;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean;
import com.itislevel.lyl.mvp.model.bean.FamilyQueryFramBackBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.mvp.model.bean.FamilyUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.LetterBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
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
public class FamilyPresenter extends RxPresenter<FamilyContract.View> implements FamilyContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public FamilyPresenter(DataManager dataManager) {
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
    public void familyAdd(String action) {
        mDataManager.familyAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.familyAdd(message);
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
    public void familyList(String action) {
        mDataManager.familyList(action)
                .compose(RxUtil.<LYLResponse<FamilyListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyListBean>() {
                    @Override
                    public void onNext(@NonNull FamilyListBean familyListBean) {
                        mView.familyList(familyListBean);
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
    public void familyListMy(String action) {
        mDataManager.familyListMy(action)
                .compose(RxUtil.<LYLResponse<FamilyListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyListBean>() {
                    @Override
                    public void onNext(@NonNull FamilyListBean familyListBean) {
                        mView.familyListMy(familyListBean);
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
    public void familyBlessList(String action) {
        mDataManager.familyBlessList(action)
                .compose(RxUtil.<LYLResponse<FamilyBlessListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyBlessListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyBlessListBean>() {
                    @Override
                    public void onNext(@NonNull FamilyBlessListBean familyBlessListBean) {
                        mView.familyBlessList(familyBlessListBean);
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
    public void familyReceiveGift(String action) {
        mDataManager.familyReceiveGift(action)
                .compose(RxUtil.<LYLResponse<FamilyReceiveGiftBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyReceiveGiftBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyReceiveGiftBean>() {
                    @Override
                    public void onNext(@NonNull FamilyReceiveGiftBean familyReceiveGiftBean) {
                        mView.familyReceiveSacrifice(familyReceiveGiftBean);
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
    public void familySendGift(String action) {
        mDataManager.familySendGift(action)
                .compose(RxUtil.<LYLResponse<FamilySendGiftRecordBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilySendGiftRecordBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilySendGiftRecordBean>() {
                    @Override
                    public void onNext(@NonNull FamilySendGiftRecordBean familySendGiftRecordBean) {
                        mView.familySendGift(familySendGiftRecordBean);
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
    public void familyReceiveBless(String action) {
        mDataManager.familyReceiveBless(action)
                .compose(RxUtil.<LYLResponse<FamilyBlessListRecevieBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyBlessListRecevieBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyBlessListRecevieBean>() {
                    @Override
                    public void onNext(@NonNull FamilyBlessListRecevieBean familyBlessListRecevieBean) {
                        mView.familyReceiveBless(familyBlessListRecevieBean);
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
    public void familyViewCount(String action) {
        mDataManager.familyViewCount(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.familyViewCount(message);
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
    public void familyCate(String action) {
        mDataManager.familyCate(action)
                .compose(RxUtil.<LYLResponse<FamilySacrificeTypeBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilySacrificeTypeBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilySacrificeTypeBean>() {
                    @Override
                    public void onNext(@NonNull FamilySacrificeTypeBean familySacrificeTypeBean) {
                        mView.familyCate(familySacrificeTypeBean);
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
    public void familyUsualLanguage(String action) {
        mDataManager.familyUsualLanguage(action)
                .compose(RxUtil.<LYLResponse<FamilyUsualLanguageBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyUsualLanguageBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyUsualLanguageBean>() {
                    @Override
                    public void onNext(@NonNull FamilyUsualLanguageBean familySacrificeTypeBean) {
                        mView.familyUsualLanguage(familySacrificeTypeBean);
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
    public void familyPhotoFrame(String action) {
        mDataManager.familyPhotoFrame(action)
                .compose(RxUtil.<LYLResponse<FamilyPhotoFrameBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyPhotoFrameBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyPhotoFrameBean>() {
                    @Override
                    public void onNext(@NonNull FamilyPhotoFrameBean familySacrificeTypeBean) {
                        mView.familyPhotoFrame(familySacrificeTypeBean);
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
    public void familyPhotoBack(String action) {
        mDataManager.familyPhotoFrame(action)
                .compose(RxUtil.<LYLResponse<FamilyPhotoFrameBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyPhotoFrameBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyPhotoFrameBean>() {
                    @Override
                    public void onNext(@NonNull FamilyPhotoFrameBean familySacrificeTypeBean) {
                        mView.familyPhotoBack(familySacrificeTypeBean);
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
    public void familyBlessAdd(String action) {
        mDataManager.familyBlessAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.familyBlessAdd(message);
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
    public void familySearch(String action) {
        mDataManager.familySearch(action)
                .compose(RxUtil.<LYLResponse<FamilyListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyListBean>() {
                    @Override
                    public void onNext(@NonNull FamilyListBean familyListBean) {
                        mView.familySearch(familyListBean);
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
    public void familyReceiveGiftById(String action) {
        mDataManager.familyReceiveGiftById(action)
                .compose(RxUtil.<LYLResponse<FamilyReceiveGiftBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyReceiveGiftBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyReceiveGiftBean>() {
                    @Override
                    public void onNext(@NonNull FamilyReceiveGiftBean familyReceiveGiftBean) {
                        mView.familyReceiveGiftById(familyReceiveGiftBean);
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
    public void familySaveFPhotoFrameAndBack(String action) {
        mDataManager.familySaveFPhotoFrameAndBack(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessOrderBean) {
                        mView.familySaveFPhotoFrameAndBack(blessOrderBean);
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
    public void familyQueryFrameAndBack(String action) {
        mDataManager.familyQueryFrameAndBack(action)
                .compose(RxUtil.<LYLResponse<FamilyQueryFramBackBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyQueryFramBackBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyQueryFramBackBean>() {
                    @Override
                    public void onNext(@NonNull FamilyQueryFramBackBean familyQueryFramBackBean) {
                        mView.familyQueryFrameAndBack(familyQueryFramBackBean);
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
    public void familyDel(String action) {
        mDataManager.familyDelete(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessOrderBean) {
                        mView.familyDel(blessOrderBean);
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
    public void generatorOrder(String action) {
        mDataManager.happyOrderAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessOrderBean) {
                        mView.generatorOrder(blessOrderBean);
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

    @Override
    public void familyCartAdd(String action) {
        mDataManager.happyCartAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessCartAddBean) {
                        mView.familyCartAdd(blessCartAddBean);
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
    public void familyCartUpdate(String action) {
        mDataManager.happyCartUpdate(action)
                .compose(RxUtil.<LYLResponse<CartUpdateBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<CartUpdateBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<CartUpdateBean>() {
                    @Override
                    public void onNext(@NonNull CartUpdateBean msg) {
                        mView.familyCartUpdate(msg);
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
    public void familyCartDel(String action) {
        mDataManager.happyCartDel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String msg) {
                        mView.familyCartDel(msg);
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
    public void selectletter(String action) {
        mDataManager.selectletter(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<LetterBean>() {
                    @Override
                    public void onNext(LetterBean letterBean) {
                    mView.selectletter(letterBean);
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
