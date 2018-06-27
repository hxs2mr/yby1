package com.itislevel.lyl.mvp.ui.main.childfragment;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\5\16 0016.
 */

public class ChildPresenter  extends RxPresenter<ChildContract.View>implements ChildContract.Presenter{
    private DataManager mDataManager;

    @Inject
    public ChildPresenter(DataManager dataManager)
    {
        mDataManager = dataManager;
    }

    @Override
    public void fristload(String msg) {
        mDataManager.cfchilflist(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<CFChildBean>() {
                    @Override
                    public void onNext(CFChildBean bean) {
                        mView.fristload(bean);
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
    public void updatepointnum(String msg) {
        mDataManager.updatepointnum(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult_Two())
                .subscribeWith(new ResourceObserver<Integer>() {
                    @Override
                    public void onNext(Integer s) {
                        mView.updatepointnum(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView!=null){
                            mView.stateError();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void looknumFlatcount(String data) {
        mDataManager.looknumFlatcount(data)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        mView.looknumFlatcount(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView!=null)
                        {
                            mView.stateError();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addFlatComment(String action) {
        mDataManager.addFlatComment(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult_PIN())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        mView.addFlatComment(s);
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
    public void cfcommentlist(String bean) {
        mDataManager.cfcommentlist(bean)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<CFPinBean>() {
                    @Override
                    public void onNext(CFPinBean bean) {
                        mView.cfcommentlist(bean);
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
    public void cf_addzan(String data) {
            mDataManager.cf_addzan(data)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<String>() {
                        @Override
                        public void onNext(String s) {
                                mView.cf_addzan(s);
                        }
                        @Override
                        public void onError(Throwable e) {
                                if(mView==null)
                                {
                                mView.stateError();
                                }
                        }
                        @Override
                        public void onComplete() {

                        }
                    });
    }

    @Override
    public void delFlatComment(String action) {
            mDataManager.delFlatComment(action)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<String>() {
                        @Override
                        public void onNext(String s) {
                            mView.delFlatComment(s);
                        }

                        @Override
                        public void onError(Throwable e) {
                            if(mView!=null)
                            {
                                mView.stateError();
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }
}
