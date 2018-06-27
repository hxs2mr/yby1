package com.itislevel.lyl.mvp.ui.team;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyGiftBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;
import com.itislevel.lyl.mvp.model.bean.TeamAddProblemBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleMyListBean;
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
public class TeamPresenter extends RxPresenter<TeamContract.View> implements TeamContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public TeamPresenter(DataManager dataManager) {
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
    public void teamRegister(String action) {
        mDataManager.teamRegister(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.teamRegister(message);
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
    public void teamStatus(String action) {
        mDataManager.teamStatus(action)
                .compose(RxUtil.<LYLResponse<TeamStatusBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TeamStatusBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TeamStatusBean>() {
                    @Override
                    public void onNext(@NonNull TeamStatusBean teamStatusBean) {
                        mView.teamStatus(teamStatusBean);
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
    public void teamList(String action) {
        mDataManager.teamList(action)
                .compose(RxUtil.<LYLResponse<TeamListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TeamListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TeamListBean>() {
                    @Override
                    public void onNext(@NonNull TeamListBean teamListBean ) {
                        mView.teamList(teamListBean);
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
    public void teamViewCount(String action) {
        mDataManager.teamViewCount(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.teamViewCount(message);
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
    public void teamProblemAdd(String action) {
        // TODO: 2018-01-16 烦恼下单
//        mDataManager.teamProblemAdd(action)
//                .compose(RxUtil.<LYLResponse<BlessOrderBean>>rxSchedulerObservableHelper())
//                .compose(RxUtil.<BlessOrderBean>handlerLYLResult())
//                .subscribeWith(new ResourceObserver<BlessOrderBean>() {
//                    @Override
//                    public void onNext(@NonNull BlessOrderBean message ) {
//                        mView.teamProblemAdd(message);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Logger.e("错误信息:" + e.getMessage());
//                        mView.stateError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.i("complete");
//                    }
//                });
    }

    @Override
    public void teamProblemList(String action) {
        mDataManager.teamProblemList(action)
                .compose(RxUtil.<LYLResponse<MyTeamBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<MyTeamBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<MyTeamBean>() {
                    @Override
                    public void onNext(@NonNull MyTeamBean teamBean  ) {
                        mView.teamProblemList(teamBean);
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
    public void teamReplay(String action) {
        mDataManager.teamReplay(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String object  ) {
                        mView.teamReplay(object);
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
    public void teamMyProblemList(String action) {
        mDataManager.teamMyProblemList(action)
                .compose(RxUtil.<LYLResponse<TroubleAdviserMyBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TroubleAdviserMyBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TroubleAdviserMyBean>() {
                    @Override
                    public void onNext(@NonNull TroubleAdviserMyBean troubleAdviserMyBean  ) {
                        mView.teamMyProblemList(troubleAdviserMyBean);
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
    public void teamType(String action) {
        mDataManager.teamType(action)
                .compose(RxUtil.<LYLResponse<TeamTypeBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TeamTypeBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TeamTypeBean>() {
                    @Override
                    public void onNext(@NonNull TeamTypeBean teamTypeBean  ) {
                        mView.teamType(teamTypeBean);
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
