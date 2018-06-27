package com.itislevel.lyl.mvp.ui.family.writer_letter;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;
import retrofit2.http.PUT;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class LetterWriterPresenter extends RxPresenter<LetterWriterContract.View>implements LetterWriterContract.Presenter {
    DataManager mDatamanager;


    @Inject
    public LetterWriterPresenter(DataManager dataManager)
    {
        mDatamanager = dataManager;
    }
    @Override
    public void save(String msg) {//保存的网络接口
         mDatamanager.addletter(msg)
                 .compose(RxUtil.rxSchedulerObservableHelper())
                 .compose(RxUtil.handlerLYLResult())
                 .subscribeWith(new ResourceObserver<String>() {
                     @Override
                     public void onNext(String s) {
                        mView.save(s);
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
    public void looknumLetter(String msg) {
        mDatamanager.looknumLetter(msg)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                    mView.looknumLetter(s);
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
