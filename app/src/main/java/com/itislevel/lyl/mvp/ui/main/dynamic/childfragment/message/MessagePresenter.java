package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.message;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.MessageBean;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\6\15 0015.
 */

public class MessagePresenter extends RxPresenter<MessageContract.View> implements MessageContract.Presenter {
    private DataManager mDataManager;
    @Inject
    public  MessagePresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void myDyPushList(String data) {
        mDataManager.myDyPushList(data)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<MessageBean>() {
                    @Override
                    public void onNext(MessageBean s) {
                        mView.myDyPushList(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void clearMyDyPushList(String data) {
        mDataManager.clearMyDyPushList(data)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                     mView.clearMyDyPushList(s);
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
