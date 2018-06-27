package com.itislevel.lyl.mvp.ui.funsharing;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingLikeBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
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
public class FunsharingPresenter extends RxPresenter<FunsharingContract.View> implements
        FunsharingContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public FunsharingPresenter(DataManager dataManager) {
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
                        mView.showContent("itisi:" + meiZiBeen.get(0).getUrl());
                        mView.showData(meiZiBeen);
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
    public void loadDataMul(int num, int page) {
        mDataManager.getMeiZiListMul(num, page)
                .compose(RxUtil.<GankResponse<List<MeiZiMultipleBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<MeiZiMultipleBean>>handlerGankResult())
                .subscribeWith(new ResourceObserver<List<MeiZiMultipleBean>>() {
                    @Override
                    public void onNext(@NonNull List<MeiZiMultipleBean> meiZiBeen) {
                        mView.showDataMultiple(meiZiBeen);
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
    public void addDynamic(String action) {
        mDataManager.addDynamic(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String funsharingAddBean) {
                        mView.addDynamic(funsharingAddBean);
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
    public void shareList(String action) {
        mDataManager.shareList(action)
                .compose(RxUtil.<LYLResponse<FunsharingListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FunsharingListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FunsharingListBean>() {
                    @Override
                    public void onNext(@NonNull FunsharingListBean funsharingListBean) {
                        mView.shareList(funsharingListBean);
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
    public void shareListMy(String action) {
        mDataManager.shareListMy(action)
                .compose(RxUtil.<LYLResponse<FunsharingMyBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FunsharingMyBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FunsharingMyBean>() {
                    @Override
                    public void onNext(@NonNull FunsharingMyBean funsharingListBean) {
                        mView.shareListMy(funsharingListBean);
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
    public void shareDel(String action) {
        mDataManager.shareDelect(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.shareDel("删除成功");
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
    public void commentShareAdd(String action) {
        mDataManager.commentShareAdd(action)
                .compose(RxUtil
                .<LYLResponse<FunsharingCommnetAddBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FunsharingCommnetAddBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FunsharingCommnetAddBean>() {
                    @Override
                    public void onNext(@NonNull FunsharingCommnetAddBean funsharingCommnetAddBean) {
                        mView.commentShareAdd(funsharingCommnetAddBean);
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
    public void commentShareDel(String action) {
        mDataManager.commentShareDel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.commentShareDel(message);
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
    public void shareLikeOrCancel(String action) {
        mDataManager.shareLikeOrCancel(action)
                .compose(RxUtil.<LYLResponse<FunsharingLikeBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FunsharingLikeBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FunsharingLikeBean>() {
                    @Override
                    public void onNext(@NonNull FunsharingLikeBean funsharingLikeBean) {
                        mView.shareLikeOrCancel(funsharingLikeBean);
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
