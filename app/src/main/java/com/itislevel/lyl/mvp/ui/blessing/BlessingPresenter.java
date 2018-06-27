package com.itislevel.lyl.mvp.ui.blessing;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddLikeBean;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessCommentBean;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;
import com.itislevel.lyl.mvp.model.bean.BlessSendGiftBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
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
public class BlessingPresenter extends RxPresenter<BlessingContract.View> implements
        BlessingContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public BlessingPresenter(DataManager dataManager) {
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
    public void happAdd(String action) {
        mDataManager.happAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.happAdd(message);
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
    public void happyListMy(String action) {
        mDataManager.happyListMy(action)
                .compose(RxUtil.<LYLResponse<BlessListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessListBean>() {
                    @Override
                    public void onNext(@NonNull BlessListBean blessListBean) {
                        mView.happyListMy(blessListBean);
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
    public void happyViewCount(String action) {
        mDataManager.happyViewCount(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.happyViewCount(message);
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
    public void happyDel(String action) {
        mDataManager.happyDel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.happyDel(message);
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
    public void happyList(String action) {
        mDataManager.happyList(action)
                .compose(RxUtil.<LYLResponse<BlessListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessListBean>() {
                    @Override
                    public void onNext(@NonNull BlessListBean blessListBean) {
                        mView.happyList(blessListBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
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
    public void happyComment(String action) {
        mDataManager.happyComment(action)
                .compose(RxUtil.<LYLResponse<BlessCommentBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessCommentBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessCommentBean>() {
                    @Override
                    public void onNext(@NonNull BlessCommentBean object) {
                        mView.happyComment(object);
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
    public void happyCommentDel(String action) {
        mDataManager.happyCommentDel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.happyCommentDel(message);
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
    public void happyLike(String action) {
        mDataManager.happyLike(action)
                .compose(RxUtil.<LYLResponse<BlessAddLikeBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessAddLikeBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessAddLikeBean>() {
                    @Override
                    public void onNext(@NonNull BlessAddLikeBean message) {
                        mView.happyLike(message);
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
    public void happyBlessAdd(String action) {
        mDataManager.happyBlessAdd(action)
                .compose(RxUtil.<LYLResponse<BlessAddBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessAddBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessAddBean>() {
                    @Override
                    public void onNext(@NonNull BlessAddBean object) {
                        mView.happyBlessAdd(object);
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
    public void happyBlessReceiveList(String action) {
        mDataManager.happyBlessReceiveList(action)
                .compose(RxUtil.<LYLResponse<BlessReceiveYuBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessReceiveYuBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessReceiveYuBean>() {
                    @Override
                    public void onNext(@NonNull BlessReceiveYuBean blessReceiveYuBean) {
                        mView.happyBlessReceiveList(blessReceiveYuBean);
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
    public void happyUsualLanguage(String action) {
        mDataManager.happyUsualLanguage(action)
                .compose(RxUtil
                        .<LYLResponse<HappyBlessUsualLanguageBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<HappyBlessUsualLanguageBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<HappyBlessUsualLanguageBean>() {
                    @Override
                    public void onNext(@NonNull HappyBlessUsualLanguageBean
                                               blessUsualLanguageBean) {
                        mView.happyUsualLanguage(blessUsualLanguageBean);
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
    public void happyGiftList(String action) {
        mDataManager.happyGiftList(action)
                .compose(RxUtil.<LYLResponse<BlessGiftBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessGiftBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessGiftBean>() {
                    @Override
                    public void onNext(@NonNull BlessGiftBean blessGiftBean) {
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
    public void happyGiftReceiveListMy(String action) {
        mDataManager.happyGiftReceiveListMy(action)
                .compose(RxUtil.<LYLResponse<BlessReceiveGiftBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessReceiveGiftBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessReceiveGiftBean>() {
                    @Override
                    public void onNext(@NonNull BlessReceiveGiftBean giftReceivedBean) {
                        mView.happyGiftReceiveListMy(giftReceivedBean);
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
    public void happyGiftSendListMy(String action) {
        mDataManager.happyGiftSendListMy(action)
                .compose(RxUtil.<LYLResponse<BlessSendGiftBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessSendGiftBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessSendGiftBean>() {
                    @Override
                    public void onNext(@NonNull BlessSendGiftBean blessSendGiftBean) {
                        mView.happyGiftSendListMy(blessSendGiftBean);
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
    public void happyOrderAdd(String action) {
        mDataManager.happyOrderAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessOrderBean) {
                        mView.happyOrderAdd(blessOrderBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.w("happyOrderAdd:" + e.getMessage());
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
    public void happySearch(String action) {
        mDataManager.happySearch(action)
                .compose(RxUtil.<LYLResponse<BlessListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessListBean>() {
                    @Override
                    public void onNext(@NonNull BlessListBean blessListBean) {
                        mView.happySearch(blessListBean);
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
    public void happyDetailsGiftList(String action) {
        mDataManager.happyDetailsGiftList(action)
                .compose(RxUtil.<LYLResponse<BlessDetailGiftListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<BlessDetailGiftListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<BlessDetailGiftListBean>() {
                    @Override
                    public void onNext(@NonNull BlessDetailGiftListBean detailGiftListBean) {
                        mView.happyDetailsGiftList(detailGiftListBean);
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

    @Override
    public void alipay(String action) {
        mDataManager.alipayTest(action)
                .compose(RxUtil.<LYLResponse<AliPayBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<AliPayBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<AliPayBean>() {
                    @Override
                    public void onNext(@NonNull AliPayBean fileUploadBean) {
                        mView.alipay(fileUploadBean);
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
