package com.itislevel.lyl.mvp.ui.troublesolution;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;
import com.itislevel.lyl.mvp.model.bean.TeamAddProblemBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleMyListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
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
public class TroublesolutionPresenter extends RxPresenter<TroublesolutionContract.View>
        implements TroublesolutionContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public TroublesolutionPresenter(DataManager dataManager) {
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
//                        mView.showContent("itisi:" + meiZiBeen.get(0).getCreatedAt());
                        mView.showDataList(meiZiBeen);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void troubleAdd(String action) {
        mDataManager.troubleAdd(action)
                .compose(RxUtil.<LYLResponse<TroubleAddBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TroubleAddBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TroubleAddBean>() {
                    @Override
                    public void onNext(@NonNull TroubleAddBean troubleAddBean) {
                        mView.troubleAdd(troubleAddBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void troubleList(String action) {
        mDataManager.troubleList(action)
                .compose(RxUtil.<LYLResponse<TroubleListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TroubleListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TroubleListBean>() {
                    @Override
                    public void onNext(@NonNull TroubleListBean troubleListBean) {
                        mView.troubleList(troubleListBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void troubleListMy(String action) {
        mDataManager.troubleMyList(action)
                .compose(RxUtil.<LYLResponse<TroubleListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TroubleListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TroubleListBean>() {
                    @Override
                    public void onNext(@NonNull TroubleListBean troubleListBean) {
                        mView.troubleListMy(troubleListBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void troubleDel(String action) {
        mDataManager.troubleDel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message) {
                        mView.troubleDel(message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void troubleCommentReplay(String action) {
        mDataManager.troubleCommentReplay(action)
                .compose(RxUtil.<LYLResponse<TroubleCommentAdd>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TroubleCommentAdd>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TroubleCommentAdd>() {
                    @Override
                    public void onNext(@NonNull TroubleCommentAdd troubleCommentAdd) {
                        mView.troubleCommentReplay(troubleCommentAdd);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void troubleCommentDel(String action) {
        mDataManager.troubleCommentDel(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String msg) {
                        mView.troubleCommentDel(msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void troubleType(String action) {
        mDataManager.troubleType(action)
                .compose(RxUtil.<LYLResponse<TroubleTypeBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<TroubleTypeBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<TroubleTypeBean>() {
                    @Override
                    public void onNext(@NonNull TroubleTypeBean  troubleTypeBean ) {
                        mView.troubleType(troubleTypeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        if (mView!=null){
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
        mDataManager.teamProblemAdd(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String blessOrderBean ) {
                        mView.teamProblemAdd(blessOrderBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        if (mView!=null){
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
    public void teamProblemList(String action) {
        mDataManager.teamProblemList(action)
                .compose(RxUtil.<LYLResponse<MyTeamBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<MyTeamBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<MyTeamBean>() {
                    @Override
                    public void onNext(@NonNull MyTeamBean myTeamBean  ) {
                        mView.teamProblemList(myTeamBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        if (mView!=null){
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
                        if (mView!=null){
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
                        if (mView!=null){
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
                    public void onNext(@NonNull String message  ) {
                        mView.teamViewCount(message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        if (mView!=null){
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
                        if (mView!=null){
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
