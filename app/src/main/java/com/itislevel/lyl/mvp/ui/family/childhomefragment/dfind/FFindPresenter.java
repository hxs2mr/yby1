package com.itislevel.lyl.mvp.ui.family.childhomefragment.dfind;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPersonListBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class FFindPresenter extends RxPresenter<FFindContract.View> implements FFindContract.Presenter{

    private DataManager mDataManager ;
    @Inject
    public FFindPresenter(DataManager dataManager)
    {
        mDataManager = dataManager;
    }
    @Override
    public void firstPage(String msg) {
        mDataManager.familyList(msg)
                .compose(RxUtil.<LYLResponse<FamilyListBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FamilyListBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FamilyListBean>() {
                    @Override
                    public void onNext(@NonNull FamilyListBean familyListBean) {
                        mView.firstPage(familyListBean);
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
    public void findmylist(String msg) {
            mDataManager.findMyFeteList(msg)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<FamilyPersonListBean>() {
                        @Override
                        public void onNext(@NonNull FamilyPersonListBean familyListBean) {
                            mView.findmylist(familyListBean);
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
    public void delFete(String msg) {
        mDataManager.familyDelete(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        mView.delFete(s);
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
}
