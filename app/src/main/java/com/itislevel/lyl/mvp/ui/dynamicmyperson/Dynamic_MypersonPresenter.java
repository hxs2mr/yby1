package com.itislevel.lyl.mvp.ui.dynamicmyperson;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.PersonalCommunBean;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public class Dynamic_MypersonPresenter extends RxPresenter<Dynamic_MypersonContract.View> implements Dynamic_MypersonContract.Presenter{
    DataManager mDataManager;
    @Inject
    public Dynamic_MypersonPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }
    @Override
    public void dynamicfollow(String action) {
        mDataManager.dynamicfollow(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String r) {
                        mView.dynamicfollow(r);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
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
    public void frist_loader(String msg) {

    }

    @Override
    public void personalCommun(String msg) {
        mDataManager.personalCommun(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<PersonalCommunBean>() {
                    @Override
                    public void onNext(PersonalCommunBean bean) {
                        mView.personalCommun(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
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
    public void delefollow(String msg) {
        mDataManager.delefollow(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String bean) {
                        mView.delefollow(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
